package TB2G.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;


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
}