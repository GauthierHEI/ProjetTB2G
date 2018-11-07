package TB2G.servlets;

import TB2G.entities.produit;
import TB2G.managers.ProduitStore;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Tshirts")
public class TshirtsServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        List<produit> ListOfTshirts = new ArrayList<>();

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        ListOfTshirts = ProduitStore.getInstance().listTshirt();
        context.setVariable("tshirt", ListOfTshirts);

        //process method
        String finalDocument = engine.process("Tshirts", context);
        rsp.getWriter().write(finalDocument);
    }
}