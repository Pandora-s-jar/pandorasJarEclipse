package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    static private DataSource instance = null;
    private Connection connection = null;
    private DataSource() {

    }

    static public DataSource getInstance(){
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getConnection()
    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://52.232.2.252:5432/phukyqqr", "phukyqqr", "hpUab8l3fo2EeyNPHqG_u6tcqhApry_c");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
