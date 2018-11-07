package TB2G.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/panier")
public class PanierServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());

        //process method
        String finalDocument = engine.process("panier", context);
        rsp.getWriter().write(finalDocument);
    }
}