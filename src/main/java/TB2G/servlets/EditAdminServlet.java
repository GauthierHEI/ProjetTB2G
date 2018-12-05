package TB2G.servlets;
import TB2G.managers.UtilisateurSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editAdministrateur")
public class EditAdminServlet extends AbstractWebServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String id = req.getParameter("id");

        Integer utilisateur_id = null;
        try {
            utilisateur_id = Integer.parseInt(id);
        } catch (NumberFormatException ignored) {
        }
        Boolean role=Boolean.parseBoolean(req.getParameter("admin"));

        try{
            UtilisateurSource.getInstance().editAdmin(utilisateur_id,role);
            resp.getWriter().print("OK");
        }
            catch(IllegalArgumentException e){
                resp.getWriter().print("KO");
            }

    }
}