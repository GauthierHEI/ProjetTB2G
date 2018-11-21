package TB2G.servlets;
import TB2G.entities.Produit;
import TB2G.entities.Utilisateur;
import TB2G.managers.ProduitStore;
import TB2G.managers.UtilisateurSource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editAdministrateur")
public class EditAdminServlet extends AbstractWebServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String id = req.getParameter("id");
        System.out.println(id);
        Integer utilisateur_id = null;
        try {
            utilisateur_id = Integer.parseInt(id);
        } catch (NumberFormatException ignored) {
        }
        Boolean role=Boolean.parseBoolean(req.getParameter("admin"));

        try{
            UtilisateurSource.getInstance().editAdmin(utilisateur_id,role);
            resp.getWriter().print("OK");}

            catch(IllegalArgumentException e){
                resp.getWriter().print("KO");
            }

    }
}