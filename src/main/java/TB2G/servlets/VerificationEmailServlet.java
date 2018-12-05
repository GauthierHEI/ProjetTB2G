package TB2G.servlets;

import TB2G.entities.Utilisateur;
import TB2G.managers.UtilisateurManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VerificationEmail")
public class VerificationEmailServlet extends AbstractWebServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");

            try {
                Utilisateur UtilisateurRetour = UtilisateurManager.getInstance().getUtilisateurByMail(email);
                if (UtilisateurRetour == null) {
                    resp.getWriter().print("OK");
                } else {
                    resp.getWriter().print("KO");
                }
            } catch (IllegalArgumentException e) {
                resp.getWriter().print("KO");
            }


    }
}
