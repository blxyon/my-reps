package LogIn;

import java.io.File;
import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class dbCreator {
    String fileName;
    Connection conn;

    public dbCreator(String fileName) {this.fileName=fileName+".db";
    }



    public int createNewDatabase() {

        int createdDB=2;

        if(!(new File(fileName)).exists())
        {
            String url = "jdbc:sqlite:" + fileName;

            try{
                conn = DriverManager.getConnection(url);
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");

                    //connect();

                    String sql = "CREATE TABLE IF NOT EXISTS ppl (\n"
                            + "	id integer PRIMARY KEY autoincrement,\n"
                            + "	name text NOT NULL,\n"
                            + "	email text NOT NULL,\n"
                            + "	type text NOT NULL\n"
                            + ");";

                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);

                    createdDB=0;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("DB file exists already!");
            createdDB=1;}
        return createdDB;
    }
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:"+fileName;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public void insert(String name, String email, String type) {
        String sql = "INSERT INTO ppl(name,email,type) VALUES(?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}