package TB2G.servlets;

import TB2G.managers.UtilisateurSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUtilisateur")
public class DeleteUtilisateurServlet extends AbstractWebServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Integer utilisateur_id = Integer.parseInt(req.getParameter("id"));

        try {
            UtilisateurSource.getInstance().deleteUtilisateur(utilisateur_id);
            resp.getWriter().print("l'utilisateur est delete");
        }catch(IllegalArgumentException e){
            resp.getWriter().print(e);
        }
        }catch(NumberFormatException e){
            resp.getWriter().print(e);
        }

    }
}
