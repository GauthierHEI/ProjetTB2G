package TB2G.servlets;

import TB2G.utils.PropertiesUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/images/*")
public class ImageServlet extends AbstractWebServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String imageName = request.getPathInfo().substring(1);

        String chemin = PropertiesUtils.cheminPro();

        String cheminTotal = chemin + "/" + imageName;


        byte[] content = Files.readAllBytes(Paths.get(cheminTotal));
        response.setContentType(getServletContext().getMimeType(imageName));
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
    }
}



