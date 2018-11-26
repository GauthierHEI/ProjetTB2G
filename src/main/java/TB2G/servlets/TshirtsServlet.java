package TB2G.servlets;

import TB2G.entities.Panier;
import TB2G.entities.Produit;
import TB2G.entities.Utilisateur;
import TB2G.managers.PanierManager;
import TB2G.managers.ProduitStore;
import TB2G.utils.PropertiesUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Tshirts")
public class TshirtsServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        List<Produit> ListOfTshirts = new ArrayList<>();

        int connecte = VariableSessionConnecte(rsq);

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        ListOfTshirts = ProduitStore.getInstance().listTshirt();
        context.setVariable("tshirt", ListOfTshirts);
        context.setVariable("connecte",connecte);
        context.setVariable("chemin", PropertiesUtils.cheminPro());

        //process method
        String finalDocument = engine.process("Tshirts", context);


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

        // CREATE PRODUIT
        Panier newProduit = new Panier(null, IdUtil, produit, taille, quantite,false);
        try {

            Panier createProd = PanierManager.getInstance().addP2P(newProduit);
            if(createProd==null) {
                req.getSession().setAttribute("errAjout", "Le produit n'a pas pu être ajouté, vérifiez les champs.");
            }
            else {
                req.getSession().setAttribute("messageAjout", "Le produit a été ajouté.");
            }

            // REDIRECT TO DETAIL PRODUIT
            resp.sendRedirect("Tshirts");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("produit-error-message", e.getMessage());
            resp.sendRedirect("Tshirts");
        }
    }

}