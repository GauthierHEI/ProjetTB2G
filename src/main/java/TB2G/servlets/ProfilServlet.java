package TB2G.servlets;

import TB2G.entities.Utilisateur;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profil")
public class ProfilServlet extends AbstractWebServlet {

    @Override
    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        int connecte = VariableSessionConnecte(rsq);

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        HttpSession session = rsq.getSession();
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        context.setVariable("connecte", connecte);
        context.setVariable("utilisateurCourant", utilisateurConnecte);

        //process method
        String finalDocument = engine.process("profil", context);
        rsp.getWriter().write(finalDocument);
        
    }
}
