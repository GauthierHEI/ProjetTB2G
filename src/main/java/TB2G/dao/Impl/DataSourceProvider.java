package TB2G.dao.Impl;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

    public class DataSourceProvider {

        private static MariaDbDataSource dataSource;

        public static DataSource getDataSource() throws SQLException {
            if (dataSource == null) {
                dataSource = new MariaDbDataSource();
                dataSource.setServerName("localhost");
                dataSource.setPort(8889);
                dataSource.setDatabaseName("tb2g");
                dataSource.setUser("root");
                dataSource.setPassword("root");
            }
            return dataSource;
        }
    }


