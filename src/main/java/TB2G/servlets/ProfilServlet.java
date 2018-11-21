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

        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        context.setVariable("errChampMdp",errMdp);
        context.setVariable("successMdp",SuccessMdp);
        context.setVariable("connecte", connecte);
        context.setVariable("utilisateurCourant", utilisateurConnecte);

        //process method
        String finalDocument = engine.process("profil", context);
        rsp.getWriter().write(finalDocument);
        
    }

    protected void doPost(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        HttpSession session = rsq.getSession();
        Utilisateur utilisateurconnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
        String newMdp = genererMotDePasse(rsq.getParameter("newmdp"));
        try{
            UtilisateurSource.getInstance().ModificationMdp(utilisateurconnecte, newMdp);
            session.setAttribute("successMdp","Le mot de passe est mis à jour");
            rsp.sendRedirect("profil");

        }catch(IllegalArgumentException e){
            session.setAttribute("errChampMdp",e.getMessage());
            rsp.sendRedirect("profil");
        }
    }
}
