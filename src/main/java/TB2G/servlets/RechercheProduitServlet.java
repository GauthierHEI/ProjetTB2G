package TB2G.servlets;

import TB2G.entities.Produit;
import TB2G.managers.ProduitStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RechercheProduit")
public class RechercheProduitServlet extends AbstractWebServlet{

    private static final ObjectMapper MAPPER = new ObjectMapper();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String recherche = req.getParameter("recherche");
        List<Produit> ListedProducts = new ArrayList<>();

        try{
            ListedProducts = ProduitStore.getInstance().RechercheProduit(recherche);
            System.out.println(ListedProducts);
            String ProductsJSON = MAPPER.writeValueAsString(ListedProducts);
            System.out.println(ProductsJSON);
            resp.getWriter().print(ProductsJSON);

        }catch (IllegalArgumentException e){
            resp.getWriter().print(e);
        }
    }
}
