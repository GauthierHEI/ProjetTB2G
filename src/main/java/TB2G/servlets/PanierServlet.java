package TB2G.servlets;

import TB2G.entities.Panier;
import TB2G.entities.Utilisateur;
import TB2G.managers.PanierManager;
import TB2G.utils.PropertiesUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/panier")
public class PanierServlet extends AbstractWebServlet {

    protected void doGet(HttpServletRequest rsq, HttpServletResponse rsp) throws IOException {

        int connecte = VariableSessionConnecte(rsq);

        float Total=0;

        List<Panier> listOfPanier = new ArrayList<>();

        HttpSession session = rsq.getSession();
        Utilisateur utilCo = (Utilisateur) session.getAttribute("utilisateurConnecte");

        Integer IdUtil;
        if (utilCo == null) {
            IdUtil = 0;
        } else {
            IdUtil = utilCo.getId();
        }


        //TemplateEngine&Resolver
        TemplateEngine engine = CreateTemplateEngine(rsq.getServletContext());

        //WebContext
        WebContext context = new WebContext(rsq, rsp, rsq.getServletContext());
        context.setVariable("connecte",connecte);

        listOfPanier = PanierManager.getInstance().listPanier(IdUtil);
        for(int i=0; i < listOfPanier.size(); i++){
            Total += ( listOfPanier.get(i).getQuantite() * listOfPanier.get(i).getProduit().getPrix());

        }

        DecimalFormat df = new DecimalFormat("#.##");

        context.setVariable("chemin", PropertiesUtils.cheminPro());
        context.setVariable("panierList", listOfPanier);
        context.setVariable("total", df.format(Total));

        //process method
        String finalDocument = engine.process("panier", context);
        rsp.getWriter().write(finalDocument);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Utilisateur utilCo = (Utilisateur) session.getAttribute("utilisateurConnecte");

        Integer IdUtil = utilCo.getId();


        PanierManager.getInstance().AcheterPanier(IdUtil);
        resp.sendRedirect("panier");

    }
}
