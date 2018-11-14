package TB2G.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deconnexion")
public class DeconnexionServlet extends AbstractWebServlet{

    @Override
    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {
        HttpSession session = rsq.getSession();
        session.removeAttribute("utilisateurConnecte");
        session.removeAttribute("connecte");
        rsp.sendRedirect("home");
    }
}
