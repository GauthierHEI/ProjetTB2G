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
        String errModifProduit = (String) session.getAttribute("errModifProduit");


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
            context.setVariable("errModifProduit", errModifProduit);

            //process method
            String finalDocument = engine.process("managerproduit", context);
            rsp.getWriter().write(finalDocument);
        }
        else{
            rsp.sendRedirect("home");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String choix = req.getParameter("choix");

        //CREATION DE PRODUIT
        if("ajout".equals(choix)){
            String nameprod = req.getParameter("produit");
            Integer dispoS = null;
            try { dispoS = Integer.parseInt(req.getParameter("dispoS")); } catch (NumberFormatException ignored) { }
            Integer dispoM = null;
            try { dispoM = Integer.parseInt(req.getParameter("dispoM")); } catch (NumberFormatException ignored) { }
            Integer dispoL = null;
            try { dispoL = Integer.parseInt(req.getParameter("dispoL")); } catch (NumberFormatException ignored) { }
            Float prix = null;
            try { prix = Float.parseFloat(req.getParameter("prix")); } catch (NumberFormatException ignored) { }
            Integer cat = null;
            try { cat = Integer.parseInt(req.getParameter("cat")); } catch (NumberFormatException ignored) { }
            String couleur = req.getParameter("couleur");
            String hexcouleur = req.getParameter("hexcouleur");
            Produit newProduit = new Produit(null, nameprod, dispoS, dispoM, dispoL, prix, cat, couleur, hexcouleur);
            try {
                Produit createProd = ProduitStore.getInstance().addProduit(newProduit);
                resp.sendRedirect("managerproduit");
            } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("film-error-message", e.getMessage());
            resp.sendRedirect("managerproduit");
            }
        }
        else if("modif".equals(choix)){
            String nameprod = req.getParameter("produit");
            Integer id = null;
            try { id = Integer.parseInt(req.getParameter("id")); } catch (NumberFormatException ignored) { }
            Integer dispoS = null;
            try { dispoS = Integer.parseInt(req.getParameter("dispoS")); } catch (NumberFormatException ignored) { }
            Integer dispoM = null;
            try { dispoM = Integer.parseInt(req.getParameter("dispoM")); } catch (NumberFormatException ignored) { }
            Integer dispoL = null;
            try { dispoL = Integer.parseInt(req.getParameter("dispoL")); } catch (NumberFormatException ignored) { }
            Float prix = null;
            try { prix = Float.parseFloat(req.getParameter("prix")); } catch (NumberFormatException ignored) { }
            Integer cat = null;
            try { cat = Integer.parseInt(req.getParameter("cat")); } catch (NumberFormatException ignored) { }
            String couleur = req.getParameter("couleur");
            String hexcouleur = req.getParameter("hexcouleur");
            Produit newProduit = new Produit( id, nameprod, dispoS, dispoM, dispoL, prix, cat, couleur, hexcouleur);
            try{
                ProduitStore.getInstance().modifProduit(newProduit);

            }catch(IllegalArgumentException e){
                req.getSession().setAttribute("errModifProduit", e.getMessage());
                resp.sendRedirect("managerproduit");
            }
        }

    }
}