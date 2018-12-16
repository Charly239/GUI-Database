package sample;


import java.sql.*;


public class DBConnect {

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    //relative path
    public static final String JDBC_URL = "jdbc:derby:C:\\Users\\Charly Garcia\\Desktop\\GUI-DbFinal\\Lib\\DBuser";
    public static Connection conn;

    public DBConnect() {
        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            if (this.conn != null) {
                System.out.println("Connected!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public void createTaskTable() {

        try {
            //String type, String userName, String password,  String firstName, String lastName, String email, String city,  String tags, String gender
            conn.createStatement()
                    .execute("Create TABLE TaskTable (name varchar(50), description varchar(500), importaance varchar(50), status varchar(50) )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTaskTable(String name, String description, String importance, String status) {
        //Initializing a volunteer to have no organizations to follow
        try {
            conn.createStatement()
                    //String type, String userName, String password,  String firstName, String lastName, String email, String city, String gender
                    .execute("INSERT INTO TaskTable VALUES ('" + name + "','" + description + "'" +
                            ",'" + importance + "','" + status + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromTable(String description) {
        try {
            conn.createStatement()
                    //String type, String userName, String password,  String firstName, String lastName, String email, String city, String gender
                    .execute("DELETE FROM TaskTable WHERE DESCRIPTION = '" + description + "' ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printAllEvent() {

        try {
            Statement statement = this.conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM EventTable1");
            while (res.next()) {
                System.out.println(res.getString("Name") + " " + res.getString("Age") + " " + res.getString("Gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Trying to Verify userNAme and password in the Table
    public boolean verifyExist(String userName, String password) {
        try {
            Statement statement = this.conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM UserTable1");
            while (res.next()) {
                if (userName.equals(res.getString("userName")) && password.equals(res.getString("password"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}