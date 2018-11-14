package TB2G.servlets;

import TB2G.entities.Produit;
import TB2G.managers.ProduitStore;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Pulls")
public class PullsServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        List<Produit> ListOfPulls = new ArrayList<>();
        int connecte = VariableSessionConnecte(rsq);


        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        ListOfPulls = ProduitStore.getInstance().listPull();
        context.setVariable("pull", ListOfPulls);
        context.setVariable("connecte",connecte);

        //process method
        String finalDocument = engine.process("Pulls", context);
        rsp.getWriter().write(finalDocument);
    }
}