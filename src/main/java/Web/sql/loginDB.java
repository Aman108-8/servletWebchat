package Web.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class loginDB {
    public static Connection getCOnnection(){
        Connection con= null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webChat", "root", "ay704321.");
            
            
        }
        catch(Exception e){
            System.out.println("Sql Error" +e);
        }
        return con;
    }
}
