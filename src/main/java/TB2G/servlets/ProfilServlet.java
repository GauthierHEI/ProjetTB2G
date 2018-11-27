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

import static TB2G.utils.MotDePasseUtils.genererMotDePasse;

@WebServlet("/profil")
public class ProfilServlet extends AbstractWebServlet {

    @Override
    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        int connecte = VariableSessionConnecte(rsq);
        HttpSession session = rsq.getSession();
        String errMdp= (String) session.getAttribute("errChampMdp");
        session.removeAttribute("errChampMdp");
        String SuccessMdp= (String) session.getAttribute("successMdp");
        session.removeAttribute("successMdp");
        String errEmail= (String) session.getAttribute("errChampEmail");
        session.removeAttribute("errChampEmail");
        String errEmailExist= (String) session.getAttribute("errEmailExist");
        session.removeAttribute("errEmailExist");
        String SuccessEmail= (String) session.getAttribute("successEmail");
        session.removeAttribute("successEmail");
        String errAdresse= (String) session.getAttribute("errChampAdresse");
        session.removeAttribute("errChampAdresse");
        String SuccessAdresse= (String) session.getAttribute("successAdresse");
        session.removeAttribute("successAdresse");

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        context.setVariable("errEmailExist", errEmailExist);
        context.setVariable("connecte", connecte);
        context.setVariable("errChampMdp",errMdp);
        context.setVariable("successMdp",SuccessMdp);
        context.setVariable("errChampEmail",errEmail);
        context.setVariable("successEmail",SuccessEmail);
        context.setVariable("errChampAdresse",errAdresse);
        context.setVariable("successAdresse",SuccessAdresse);
        context.setVariable("utilisateurCourant", utilisateurConnecte);

        //process method
        String finalDocument = engine.process("profil", context);
        rsp.getWriter().write(finalDocument);
        
    }

    protected void doPost(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        HttpSession session = rsq.getSession();
        Utilisateur utilisateurconnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");

        String choix = rsq.getParameter("choix");



        if("mdp".equals(choix)) {

            String newMdp = genererMotDePasse(rsq.getParameter("newmdp"));

            try {
                UtilisateurSource.getInstance().ModificationMdp(utilisateurconnecte, newMdp);
                session.setAttribute("successMdp", "Le mot de passe est mis a jour");
                rsp.sendRedirect("profil");

            } catch (IllegalArgumentException e) {
                session.setAttribute("errChampMdp", e.getMessage());
                rsp.sendRedirect("profil");
            }
        }
        else if("adresse".equals(choix)) {

            String newAdresse = rsq.getParameter("newadresse");

            try {
                UtilisateurSource.getInstance().ModificationAdresse(utilisateurconnecte, newAdresse);
                session.setAttribute("successAdresse", "L'adresse est mis a jour");
                rsp.sendRedirect("profil");

            } catch (IllegalArgumentException e) {
                session.setAttribute("errChampAdresse", e.getMessage());
                rsp.sendRedirect("profil");
            }
        }
        else if("email".equals(choix)) {

            String newEmail = rsq.getParameter("newemail");

            if (UtilisateurSource.getInstance().getUtilisateurByMail(newEmail)== null) {
                try {
                    UtilisateurSource.getInstance().ModificationEmail(utilisateurconnecte, newEmail);
                    session.setAttribute("successEmail", "L'email est mis a jour");
                    rsp.sendRedirect("profil");

                } catch (IllegalArgumentException e) {
                    session.setAttribute("errChampEmail", e.getMessage());
                    rsp.sendRedirect("profil");
                }
            }else{
                    session.setAttribute("errEmailExist","Un compte utilisant cette adresse email existe !");
                    rsp.sendRedirect("profil");
            }

        }
    }
}
