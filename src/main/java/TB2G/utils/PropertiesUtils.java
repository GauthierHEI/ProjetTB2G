package TB2G.utils;

import TB2G.dao.Impl.DataSourceProvider;
import TB2G.dao.Impl.ProduitDaoImpl;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    static Properties prop = new Properties();

    public static String cheminPro() {

        String leChemin = "";
        try {
            prop.load(ProduitDaoImpl.class.getResourceAsStream("/data-exemple.properties"));
            // get the property value
            leChemin = prop.getProperty("path");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return leChemin;
    }

    public static String User() {

        String leUser = "";
        try {
            prop.load(DataSourceProvider.class.getResourceAsStream("/data-exemple.properties"));
            // get the property value
            leUser = prop.getProperty("user");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return leUser;
    }

    public static String Pass() {

        String lePass = "";
        try {
            prop.load(DataSourceProvider.class.getResourceAsStream("/data-exemple.properties"));
            // get the property value
            lePass = prop.getProperty("pass");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lePass;
    }

    public static int Port() {

        int lePort = 0;
        try {
            prop.load(DataSourceProvider.class.getResourceAsStream("/data-exemple.properties"));
            // get the property value
            lePort = Integer.parseInt(prop.getProperty("port"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lePort;
    }

    public static String DataBaseName() {

        String leDataBaseName = "";
        try {
            prop.load(DataSourceProvider.class.getResourceAsStream("/data-exemple.properties"));
            // get the property value
            leDataBaseName = prop.getProperty("databasename");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return leDataBaseName;
    }

    public static String ServerName() {

        String leServerName = "";
        try {
            prop.load(DataSourceProvider.class.getResourceAsStream("/data-exemple.properties"));
            // get the property value
            leServerName = prop.getProperty("servername");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return leServerName;
    }

}
