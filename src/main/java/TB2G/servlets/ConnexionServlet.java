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
import java.sql.SQLException;
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
        String errEmail = (String) session.getAttribute("errEmail");
        session.removeAttribute("errEmail");
        String errEmailExist = (String) session.getAttribute("errEmailExist");
        session.removeAttribute("errEmailExist");
        String errChamp = (String) session.getAttribute("errChamp");
        session.removeAttribute("errChamp");

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());

        //process method
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
        if( utilisateurConnecte == null || "".equals(utilisateurConnecte.getNom())) {
            context.setVariable("errMDP", errMDP);
            context.setVariable("errEmail", errEmail);
            context.setVariable("errEmailExist", errEmailExist);
            context.setVariable("errChamp", errChamp);
            session.setAttribute("connecte", 0);
            String finalDocument = engine.process("authentification", context);
            rsp.getWriter().write(finalDocument);
        }
        else if (utilisateurConnecte.getAdmin()){
            session.setAttribute("connecte", 2);
            rsp.sendRedirect("managerproduit");
        }
        else {
            session.setAttribute("connecte", 1);
            rsp.sendRedirect("profil");
        }
    }

    @Override
    protected void doPost(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        HttpSession session = rsq.getSession();
        String choix = rsq.getParameter("choix");
        if(("creer").equals(rsq.getParameter("choix"))){

            String mail = rsq.getParameter("mail");
            if (UtilisateurSource.getInstance().getUtilisateurByMail(mail)==null) {
                String nom = rsq.getParameter("nom");

                String prenom = rsq.getParameter("prenom");

                String birthDateAsString = rsq.getParameter("birth");

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = null;
                try {
                    birthDate = LocalDate.parse(birthDateAsString, dateFormat);
                } catch (DateTimeParseException ignored) { }

                String password = rsq.getParameter("password");

                String motDePasseHash = genererMotDePasse(password);

                String adresse = rsq.getParameter("adresse") +", "+ rsq.getParameter("codepostal") +", "+ rsq.getParameter("ville");

                Utilisateur utilisateur = new Utilisateur(null, mail, prenom, nom,
                        birthDate, motDePasseHash, adresse, adresse, false);

                //Create task
                Utilisateur utilisateurRetour = UtilisateurSource.getInstance().addUtilisateur(utilisateur);
                if (utilisateurRetour.getPrenom()==null){
                    session.setAttribute("errChamp", "Champ mal rempli");
                    rsp.sendRedirect("authentification");
                }
                else {
                    session.setAttribute("utilisateurConnecte", utilisateur);
                    rsp.sendRedirect("authentification");
                }

            }
            else {
                session.setAttribute("errEmailExist", "Email deja utilise.");
                rsp.sendRedirect("authentification");
            }

        }
        else {
            String mail = rsq.getParameter("mail");
            Utilisateur utilisateur;
            utilisateur = UtilisateurSource.getInstance().getUtilisateurByMail(mail);
            if (utilisateur==null) {
                session.setAttribute("errEmail", "Email n'existe pas");
                rsp.sendRedirect("authentification");
            }
            else {
                String password = rsq.getParameter("password");
                String realPassword = utilisateur.getMotdepasse();

                if (validerMotDePasse(password, realPassword)) {
                    session.setAttribute("utilisateurConnecte", utilisateur);
                    rsp.sendRedirect("authentification");
                }
                else {
                    session.setAttribute("errMDP", "Mot de passe incorrect");
                    rsp.sendRedirect("authentification");
                }
            }

        }

    }
}