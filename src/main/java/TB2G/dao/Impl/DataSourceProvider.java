package TB2G.dao.Impl;

import java.sql.SQLException;
import javax.sql.DataSource;

import TB2G.utils.PropertiesUtils;
import org.mariadb.jdbc.MariaDbDataSource;

    public class DataSourceProvider {

        private static MariaDbDataSource dataSource;

        public static DataSource getDataSource() throws SQLException {
            if (dataSource == null) {
                dataSource = new MariaDbDataSource();
                dataSource.setServerName(PropertiesUtils.ServerName());
                dataSource.setPort(PropertiesUtils.Port());
                dataSource.setDatabaseName(PropertiesUtils.DataBaseName());
                dataSource.setUser(PropertiesUtils.User());
                   dataSource.setPassword(PropertiesUtils.Pass());
            }
            return dataSource;
        }
    }


