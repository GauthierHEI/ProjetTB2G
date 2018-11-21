package TB2G.utils;

import TB2G.dao.Impl.ProduitDaoImpl;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    public static String cheminPro() {
        Properties prop = new Properties();

        String leChemin = "";

        try {
            prop.load(ProduitDaoImpl.class.getResourceAsStream("/data.properties"));


            // get the property value
            leChemin = prop.getProperty("path");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return leChemin;
    }
}
