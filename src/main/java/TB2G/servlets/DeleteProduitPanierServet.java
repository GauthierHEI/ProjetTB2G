package TB2G.servlets;

import TB2G.managers.PanierManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProdPanier")
public class DeleteProduitPanierServet extends AbstractWebServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer produitId2;
        if ((req.getParameter("produitId") != null) && !("".equals(req.getParameter("produitId")))) {

            //Id not null nor empty

            produitId2 = Integer.parseInt(req.getParameter("produitId"));
            try {
                PanierManager.getInstance().deleteProduitPanier(produitId2);

                resp.getWriter().print("Le produit a été supprimé.");

            } catch (IllegalArgumentException e) {
                resp.getWriter().print(e.getMessage());
            }

        } else {
            resp.getWriter().print("Impossible de récupérer l'Id produit.");

        }
    }

}
