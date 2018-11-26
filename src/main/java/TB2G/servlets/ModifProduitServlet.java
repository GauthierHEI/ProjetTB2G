package TB2G.servlets;

import TB2G.entities.Produit;
import TB2G.managers.ProduitStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/modifProduit")
public class ModifProduitServlet extends AbstractWebServlet{

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /******************/
        /*    M O D I F   */
        /******************/


        Integer produitId1 = null;
        if ((req.getParameter("produitId") != null) && !("".equals(req.getParameter("produitId")))) {

            //Id not null nor empty

            produitId1 = Integer.parseInt(req.getParameter("produitId"));

            String nameprod1 = null;
            if (req.getParameter("produit") != null && !"".equals(req.getParameter("produit"))) {
                nameprod1 = req.getParameter("produit");
            }
            Integer dispoS1 = null;
            if (req.getParameter("dispoS") != null && !"".equals(req.getParameter("dispoS"))) {
                dispoS1 = Integer.parseInt(req.getParameter("dispoS"));
            }
            Integer dispoM1 = null;
            if (req.getParameter("dispoM") != null && !"".equals(req.getParameter("dispoM"))) {
                dispoM1 = Integer.parseInt(req.getParameter("dispoM"));
            }
            Integer dispoL1 = null;
            if (req.getParameter("dispoL") != null && !"".equals(req.getParameter("dispoL"))) {
                dispoL1 = Integer.parseInt(req.getParameter("dispoL"));
            }
            Float prix1 = null;
            if (req.getParameter("prix") != null && !"".equals(req.getParameter("prix"))) {
                prix1 = Float.parseFloat(req.getParameter("prix"));
            }
            Integer cat1 = null;
            if (req.getParameter("cat") != null && !"".equals(req.getParameter("cat"))) {
                dispoS1 = Integer.parseInt(req.getParameter("cat"));
            }
            String couleur1 = null;
            if (req.getParameter("couleur") != null && !"".equals(req.getParameter("couleur"))) {
                couleur1 = req.getParameter("couleur");
            }
            String hexcouleur1 = null;
            if ((req.getParameter("hexcouleur") != null) &&
                    !("".equals(req.getParameter("hexcouleur"))) &&
                    !("#000001".equals(req.getParameter("hexcouleur")))) {
                hexcouleur1 = req.getParameter("hexcouleur");
            }

            /*Part filePart = req.getPart("image");
            File newFile = ProduitStore.getInstance().imageDansFichier(filePart);
            String image = newFile.getName();*/

            // CREATE PRODUIT

            Produit newProduit1 = new Produit(produitId1, nameprod1, dispoS1, dispoM1, dispoL1, prix1, cat1, couleur1, null, hexcouleur1);

            // MODIFY PRODUIT
            try {

                Produit createProd = ProduitStore.getInstance().modifProduit(newProduit1);
                if (createProd == null) {
                    resp.getWriter().print("Le produit n'a pas pu être modifié, vérifiez les champs.");
                } else {
                    resp.getWriter().print("Le produit a été modifié.");
                }

            } catch (IllegalArgumentException e) {
                resp.getWriter().print(e.getMessage());
            }
        }
        else {
            resp.getWriter().print("Impossible de récupérer l'Id produit.");
        }
    }

}
