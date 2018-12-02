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

@WebServlet("/Pulls")
public class PullsServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        List<Produit> ListOfPulls = new ArrayList<>();
        int connecte = VariableSessionConnecte(rsq);
        HttpSession session = rsq.getSession();

        //Get error and success messages
        String errAjout = (String) session.getAttribute("errAjout");
        session.removeAttribute("errAjout");
        String messageAjout = (String) session.getAttribute("messageAjout");
        session.removeAttribute("messageAjout");
        String errAddPanier = (String) session.getAttribute("errAddPanier");
        session.removeAttribute("errAddPanier");
        String messAddPanier = (String) session.getAttribute("messAddPanier");
        session.removeAttribute("messAddPanier");

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        ListOfPulls = ProduitStore.getInstance().listPull();
        context.setVariable("pull", ListOfPulls);
        context.setVariable("connecte",connecte);
        context.setVariable("chemin", PropertiesUtils.cheminPro());
        context.setVariable("errAjout", errAjout);
        context.setVariable("messageAjout", messageAjout);
        context.setVariable("errAddPanier", errAddPanier);
        context.setVariable("messAddPanier", messAddPanier);


        //process method
        String finalDocument = engine.process("Pulls", context);
        rsp.getWriter().write(finalDocument);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Utilisateur utilCo = (Utilisateur) session.getAttribute("utilisateurConnecte");

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

        if(quantite > disp) {
            req.getSession().setAttribute("errAddPanier", "Désolé ! On n'a pas assez de cet article en stock...");
        } else {
            Panier newProduit = new Panier(null, IdUtil, produit, taille, quantite,false);
            PanierManager.getInstance().addP2P(newProduit);
            req.getSession().setAttribute("messAddPanier", "On a bien ajouté l'item dans ton panier!!");
        }


        try {
            // REDIRECT TO DETAIL PRODUIT
            resp.sendRedirect("Pulls");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("produit-error-message", e.getMessage());
            resp.sendRedirect("Pulls");
        }
    }
}