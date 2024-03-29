package TB2G.servlets;
import TB2G.entities.Utilisateur;
import TB2G.managers.UtilisateurManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/utilisateurs")
public class ListeUtilisateursServlet extends AbstractWebServlet{
    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        int connecte = VariableSessionConnecte(rsq);
        HttpSession session = rsq.getSession();
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");


        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());

        if(utilisateurConnecte == null) {
            rsp.sendRedirect("home");
        }
        else if(utilisateurConnecte.getAdmin()){
            List<Utilisateur> ListOfUtilisateurs = new ArrayList<>();
            ListOfUtilisateurs = UtilisateurManager.getInstance().listUtilisateur();
            context.setVariable("utilisateur", ListOfUtilisateurs);
            context.setVariable("connecte", connecte);
            context.setVariable("utilisateurCourant",utilisateurConnecte.getId());

            //process method
            String finalDocument = engine.process("utilisateurs", context);
            rsp.getWriter().write(finalDocument);
        }
        else{
            rsp.sendRedirect("home");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{



    }
}
