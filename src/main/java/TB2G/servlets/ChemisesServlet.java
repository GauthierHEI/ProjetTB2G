package TB2G.servlets;

import TB2G.entities.Panier;
import TB2G.entities.Produit;
import TB2G.entities.Utilisateur;
import TB2G.managers.PanierManager;
import TB2G.managers.ProduitStore;
import TB2G.utils.PropertiesUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Chemises")
public class ChemisesServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        List<Produit> ListOfChemises = new ArrayList<>();

        int connecte = VariableSessionConnecte(rsq);
        HttpSession session = rsq.getSession();

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //Get error and success messages
        String errAchatConnexion = (String) session.getAttribute("errAchatConnexion");
        session.removeAttribute("errAchatConnexion");
        String errAddPanier = (String) session.getAttribute("errAddPanier");
        session.removeAttribute("errAddPanier");
        String messAddPanier = (String) session.getAttribute("messAddPanier");
        session.removeAttribute("messAddPanier");

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        ListOfChemises = ProduitStore.getInstance().listChemise();
        context.setVariable("connecte", connecte);
        context.setVariable("chemise", ListOfChemises);
        context.setVariable("errAchatConnexion", errAchatConnexion);
        context.setVariable("chemin", PropertiesUtils.cheminPro());
        context.setVariable("errAddPanier", errAddPanier);
        context.setVariable("messAddPanier", messAddPanier);


        //process method
        String finalDocument = engine.process("Chemises", context);
        rsp.getWriter().write(finalDocument);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Utilisateur utilCo = (Utilisateur) session.getAttribute("utilisateurConnecte");

        if (utilCo == null) {

            session.setAttribute("errAchatConnexion", "Impossible d'acheter si vous n'etes pas connecte");
            resp.sendRedirect("Tshirts");
        } else {

            Integer IdUtil = utilCo.getId();

            Produit produit = ProduitStore.getInstance().getProduit(Integer.parseInt(req.getParameter("idObj")));

            String taille = req.getParameter("taille");
            Integer quantite = null;
            try {
                quantite = Integer.parseInt(req.getParameter("quantite"));
            } catch (NumberFormatException ignored) {
            }


            Integer IdProduit = null;
            try {
                IdProduit = Integer.parseInt(req.getParameter("idObj"));
            } catch (NumberFormatException ignored) {
            }
            // CREATE PRODUIT
            Integer disp = 0;
            if (taille.equals("S")) {
                disp = ProduitStore.getInstance().getQuantiteDispoS(IdProduit);
            }
            if (taille.equals("M")) {
                disp = ProduitStore.getInstance().getQuantiteDispoM(IdProduit);
            }
            if (taille.equals("L")) {
                disp = ProduitStore.getInstance().getQuantiteDispoL(IdProduit);
            }

            if (quantite > disp) {
                req.getSession().setAttribute("errAddPanier", "Désolé ! On n'a pas assez de cet article en stock... ");
            } else {
                Panier newProduit = new Panier(null, IdUtil, produit, taille, quantite, false);
                PanierManager.getInstance().addP2P(newProduit);
                if (taille.equals("S")) {
                    ProduitStore.getInstance().updateDispoS(quantite, IdProduit);
                }
                if (taille.equals("M")) {
                    ProduitStore.getInstance().updateDispoM(quantite, IdProduit);
                }
                if (taille.equals("L")) {
                    ProduitStore.getInstance().updateDispoL(quantite, IdProduit);
                }
                req.getSession().setAttribute("messAddPanier", "On a bien ajouté l'item dans ton panier!!  ");
            }


            try {
                // REDIRECT TO DETAIL PRODUIT
                resp.sendRedirect("Chemises");
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("produit-error-message", e.getMessage());
                resp.sendRedirect("Chemises ");
            }
        }
    }

}