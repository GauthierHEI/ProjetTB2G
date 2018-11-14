package TB2G.servlets;

import TB2G.entities.Utilisateur;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@WebServlet("/authentification")
public class ConnexionServlet extends AbstractWebServlet {

    @Override
    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());

        //process method
        String utilisateurConnecte = (String) rsq.getSession().getAttribute("utilisateurConnecte");

        if( utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            String finalDocument = engine.process("authentification", context);
            rsp.getWriter().write(finalDocument);
        }
        else {
            String finalDocument = engine.process("profil", context);
            rsp.getWriter().write(finalDocument);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        String username = rsq.getParameter("username");
        String password = rsq.getParameter("password");
        if(("Créer").equals(rsq.getParameter("choix"))){
            String nom = rsq.getParameter("nom");
            String prenom = rsq.getParameter("prenom");

            String birthDateAsString = rsq.getParameter("birth");

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = null;
            try {
                birthDate = LocalDate.parse(birthDateAsString, dateFormat);
            } catch (DateTimeParseException ignored) { }

            String mail = rsq.getParameter("mail");
            String adresse = rsq.getParameter("numeroderue") + rsq.getParameter("adresse")
                    + rsq.getParameter("codepostal") + rsq.getParameter("ville");
            Utilisateur utilisateur = new Utilisateur(null, mail, prenom, nom,
                    birthDate, password, adresse, adresse, false);

        }
        else {

        }

    }
}