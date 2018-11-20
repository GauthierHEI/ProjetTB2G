package TB2G.servlets;

import TB2G.entities.Produit;
import TB2G.entities.Utilisateur;
import TB2G.managers.ProduitStore;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/managerproduit")
public class ProductManagerServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        int connecte = VariableSessionConnecte(rsq);
        HttpSession session = rsq.getSession();
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
        String errAjout = (String) session.getAttribute("errAjout");
        session.removeAttribute("errAjout");
        String messageAjout = (String) session.getAttribute("messageAjout");
        session.removeAttribute("messageAjout");

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());

        if(utilisateurConnecte == null) {
            rsp.sendRedirect("home");
        }
        else if(utilisateurConnecte.getAdmin()){
            List<Produit> ListOfProduits = new ArrayList<>();
            ListOfProduits = ProduitStore.getInstance().listProduit();
            context.setVariable("produit", ListOfProduits);
            context.setVariable("connecte", connecte);
            context.setVariable("errAjout", errAjout);
            context.setVariable("messageAjout", messageAjout);

            //process method
            String finalDocument = engine.process("managerproduit", context);
            rsp.getWriter().write(finalDocument);
        }
        else{
            rsp.sendRedirect("home");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //AJOUT OU MODIF

        String action = req.getParameter("action");

        /******************/
        /*    A J O U T   */
        /******************/

        if("ajout".equals(action)) {
            String nameprod = req.getParameter("produit");
            Integer dispoS = null;
            try {
                dispoS = Integer.parseInt(req.getParameter("dispoS"));
            } catch (NumberFormatException ignored) {
            }
            Integer dispoM = null;
            try {
                dispoM = Integer.parseInt(req.getParameter("dispoM"));
            } catch (NumberFormatException ignored) {
            }
            Integer dispoL = null;
            try {
                dispoL = Integer.parseInt(req.getParameter("dispoL"));
            } catch (NumberFormatException ignored) {
            }
            Float prix = null;
            try {
                prix = Float.parseFloat(req.getParameter("prix"));
            } catch (NumberFormatException ignored) {
            }
            Integer cat = null;
            try {
                cat = Integer.parseInt(req.getParameter("cat"));
            } catch (NumberFormatException ignored) {
            }
            String couleur = req.getParameter("couleur");
            String hexcouleur = req.getParameter("hexcouleur");

            // CREATE PRODUIT
            Produit newProduit = new Produit(null, nameprod, dispoS, dispoM, dispoL, prix, cat, couleur, hexcouleur);
            try {

                Produit createProd = ProduitStore.getInstance().addProduit(newProduit);
                if(createProd==null) {
                    req.getSession().setAttribute("errAjout", "Le produit n'a pas pu être ajouté, vérifiez les champs.");
                }
                else {
                    req.getSession().setAttribute("messageAjout", "Le produit a été ajouté.");
                }

                // REDIRECT TO DETAIL PRODUIT
                resp.sendRedirect("managerproduit");
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("produit-error-message", e.getMessage());
                resp.sendRedirect("managerproduit");
            }
        }

        /******************/
        /*    M O D I F   */
        /******************/

        else {
            Integer produitId1 = null;
            if ((req.getParameter("produitId1") != null) && !("".equals(req.getParameter("produitId1")))) {

                //Id not null nor empty

                produitId1 = Integer.parseInt(req.getParameter("produitId1"));

                String nameprod1 = null;
                if (req.getParameter("produit1") != null && !"".equals(req.getParameter("produit1"))) {
                    nameprod1 = req.getParameter("produit1");
                }
                Integer dispoS1 = null;
                if (req.getParameter("dispoS1") != null && !"".equals(req.getParameter("dispoS1"))) {
                    dispoS1 = Integer.parseInt(req.getParameter("dispoS1"));
                }
                Integer dispoM1 = null;
                if (req.getParameter("dispoM1") != null && !"".equals(req.getParameter("dispoM1"))) {
                    dispoM1 = Integer.parseInt(req.getParameter("dispoM1"));
                }
                Integer dispoL1 = null;
                if (req.getParameter("dispoL1") != null && !"".equals(req.getParameter("dispoL1"))) {
                    dispoL1 = Integer.parseInt(req.getParameter("dispoL1"));
                }
                Float prix1 = null;
                if (req.getParameter("prix1") != null && !"".equals(req.getParameter("prix1"))) {
                    prix1 = Float.parseFloat(req.getParameter("prix1"));
                }
                Integer cat1 = null;
                if (req.getParameter("cat1") != null && !"".equals(req.getParameter("cat1"))) {
                    dispoS1 = Integer.parseInt(req.getParameter("cat1"));
                }
                String couleur1 = null;
                if (req.getParameter("couleur1") != null && !"".equals(req.getParameter("couleur1"))) {
                    couleur1 = req.getParameter("couleur1");
                }
                String hexcouleur1 = null;
                String test = req.getParameter("hexcouleur1");
                if ((test != null) && !("".equals(test)) && !("#000001".equals(test))) {
                    hexcouleur1 = req.getParameter("hexcouleur1");
                }

                // CREATE PRODUIT

                Produit newProduit1 = new Produit(produitId1, nameprod1, dispoS1, dispoM1, dispoL1, prix1, cat1, couleur1, hexcouleur1);

                //We change the null values by the values in the DB
                Produit produitExist = ProduitStore.getInstance().getProduitById(produitId1);

                if (newProduit1.getNameproduit() == null) {
                    newProduit1.setNameproduit(produitExist.getNameproduit());
                }
                if (newProduit1.getDispoS() == null) {
                    newProduit1.setDispoS(produitExist.getDispoS());
                }
                if (newProduit1.getDispoM() == null) {
                    newProduit1.setDispoM(produitExist.getDispoM());
                }
                if (newProduit1.getDispoL() == null) {
                    newProduit1.setDispoL(produitExist.getDispoL());
                }
                if (newProduit1.getPrix() == null) {
                    newProduit1.setPrix(produitExist.getPrix());
                }
                if (newProduit1.getCat() == null) {
                    newProduit1.setCat(produitExist.getCat());
                }
                if (newProduit1.getCouleur() == null) {
                    newProduit1.setCouleur(produitExist.getCouleur());
                }
                if (newProduit1.getHexcouleur() == null) {
                    newProduit1.setHexcouleur(produitExist.getHexcouleur());
                }

                // MODIFY PRODUIT
                try {

                    Produit createProd = ProduitStore.getInstance().modifProduit(newProduit1);
                    if (createProd == null) {
                        req.getSession().setAttribute("errModif", "Le produit n'a pas pu être modifié, vérifiez les champs.");
                    } else {
                        req.getSession().setAttribute("messageModif", "Le produit a été modifié.");
                    }

                    // REDIRECT TO DETAIL PRODUIT
                    resp.sendRedirect("managerproduit");
                } catch (IllegalArgumentException e) {
                    req.getSession().setAttribute("produit-error-message", e.getMessage());
                    resp.sendRedirect("managerproduit");
                }
            }

        }

    }
}