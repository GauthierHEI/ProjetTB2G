package TB2G.servlets;

import TB2G.entities.Produit;
import TB2G.managers.ProduitStore;
import TB2G.utils.PropertiesUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Chemises")
public class ChemisesServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        List<Produit> ListOfChemises = new ArrayList<>();

        int connecte = VariableSessionConnecte(rsq);

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        ListOfChemises = ProduitStore.getInstance().listChemise();
        context.setVariable("connecte", connecte);
        context.setVariable("chemise", ListOfChemises);
        context.setVariable("chemin", PropertiesUtils.cheminPro());


        //process method
        String finalDocument = engine.process("Chemises", context);
        rsp.getWriter().write(finalDocument);
    }
}