package dk.easv.database3layered.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public Connection getConnection() throws SQLServerException {
            SQLServerDataSource ds;
            ds = new SQLServerDataSource();
            ds.setDatabaseName("e31-dogs"); // make this unique as names are shared on server
            ds.setUser("CS2025b_e_31"); // Use your own username
            ds.setPassword("CS2025bE31#23"); // Use your own password
            ds.setServerName("EASV-DB4");
            ds.setPortNumber(1433);
            ds.setTrustServerCertificate(true);
            return ds.getConnection();

    }
}
