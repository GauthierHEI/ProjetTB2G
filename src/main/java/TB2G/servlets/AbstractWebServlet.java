package TB2G.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public  abstract class AbstractWebServlet extends HttpServlet {

    protected TemplateEngine CreateTemplateEngine(ServletContext context) {

        //Template RESOLVER
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);

        //Template ENGINE
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.addDialect(new Java8TimeDialect());

        return engine;
    }

    protected int VariableSessionConnecte(HttpServletRequest rsq) {

        HttpSession session = rsq.getSession();

        int connecte;

        if(session.getAttribute("connecte")==null) {
            session.setAttribute("connecte", 0);
            connecte = (int) session.getAttribute("connecte");
            return connecte;
        }
        else {
            connecte = (int) session.getAttribute("connecte");
            return connecte;
        }


    }
}