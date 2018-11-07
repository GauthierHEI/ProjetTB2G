package TB2G.servlets;

import TB2G.entities.produit;
import TB2G.managers.ProduitStore;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/managerproduit")
public class ProductManagerServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        List<produit> ListOfProduits = new ArrayList<>();
        ListOfProduits = ProduitStore.getInstance().listProduit();
        context.setVariable("produit", ListOfProduits);


        //process method
        String finalDocument = engine.process("managerproduit", context);
        rsp.getWriter().write(finalDocument);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
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

        String image = req.getParameter("adresse");

        // CREATE PRODUIT
        produit newProduit = new produit(null, nameprod, dispoS, dispoM, dispoL, prix, cat, couleur, image);
        try {

            produit createdProduit = ProduitStore.getInstance().addProduit(newProduit);

            // REDIRECT TO DETAIL PRODUIT
            resp.sendRedirect(String.format("produit?id=%d", createdProduit.getId()));
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("film-error-message", e.getMessage());
            resp.sendRedirect("newProduit");
        }

    }
}