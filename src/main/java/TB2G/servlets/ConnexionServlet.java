package TB2G.servlets;

import TB2G.entities.Utilisateur;
import TB2G.managers.UtilisateurSource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static TB2G.utils.MotDePasseUtils.genererMotDePasse;
import static TB2G.utils.MotDePasseUtils.validerMotDePasse;

@WebServlet("/authentification")
public class ConnexionServlet extends AbstractWebServlet {

    @Override
    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        HttpSession session = rsq.getSession();

        String errMDP = (String) session.getAttribute("errMDP");
        session.removeAttribute("errMDP");
        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());

        //process method
        String utilisateurConnecte = (String) session.getAttribute("utilisateurConnecte");

        if( utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            context.setVariable("errMDP", errMDP);
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
        HttpSession session = rsq.getSession();
        String choix = rsq.getParameter("choix");
        if(("creer").equals(rsq.getParameter("choix"))){
            String nom = rsq.getParameter("nom");

            String prenom = rsq.getParameter("prenom");

            String birthDateAsString = rsq.getParameter("birth");

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = null;
            try {
                birthDate = LocalDate.parse(birthDateAsString, dateFormat);
            } catch (DateTimeParseException ignored) { }

            String mail = rsq.getParameter("mail");

            String password = rsq.getParameter("password");

            String motDePasseHash = genererMotDePasse(password);

            String adresse = rsq.getParameter("numeroderue") +" "+ rsq.getParameter("adresse")
                    +" "+ rsq.getParameter("codepostal") +" "+ rsq.getParameter("ville");

            Utilisateur utilisateur = new Utilisateur(null, mail, prenom, nom,
                    birthDate, motDePasseHash, adresse, adresse, false);

            //Create task
            UtilisateurSource.getInstance().addUtilisateur(utilisateur);
            rsp.sendRedirect("home");

        }
        else {
            String mail = rsq.getParameter("mail");
            Utilisateur utilisateur = UtilisateurSource.getInstance().getUtilisateurByMail(mail);
            String password = rsq.getParameter("password");
            String realPassword = utilisateur.getMotdepasse();
            if (validerMotDePasse(password, realPassword)) {
                session.setAttribute("utilisateurConnecte", utilisateur);
            }
            else {
                session.setAttribute("errMDP", "Mot de passe incorrect");
                rsp.sendRedirect("authentification");
            }
        }

    }
}