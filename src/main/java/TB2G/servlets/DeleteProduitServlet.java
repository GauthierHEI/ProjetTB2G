package TB2G.servlets;

import TB2G.managers.ProduitManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduit")
public class DeleteProduitServlet extends AbstractWebServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /********************/
        /*    D E L E T E   */
        /********************/

        Integer produitId2;
        if ((req.getParameter("produitId") != null) && !("".equals(req.getParameter("produitId")))) {

            //Id not null nor empty

            produitId2 = Integer.parseInt(req.getParameter("produitId"));
            try {
                ProduitManager.getInstance().deleteProduit(produitId2);

                    resp.getWriter().print("Le produit a été supprimé.");

            } catch (IllegalArgumentException e) {
                resp.getWriter().print(e.getMessage());
            }

        }
        else {
            resp.getWriter().print("Impossible de récupérer l'Id produit.");

        }

    }
}