package Web.sql;

import Web.User;
import Web.sql.loginDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class userSql {
    
    public static int setUser(User u){
        int status = 0;
        try {
            List<User> checkUser= userSql.getUser();
            String newUser = u.getUserName();
            //check user exist or not if exist then don't add the user again
            boolean userExit = checkUser.stream().anyMatch(user->user.getUserName().equals(newUser));
            
            if(!userExit){
                Connection con = loginDB.getCOnnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                ps.setString(1,u.getUserName());
                ps.setString(2,u.getPassword());
                
                status = ps.executeUpdate();
                
                con.close();
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return status;
    }
    
    public static List<User> getUser(){
        List <User> allUser;
        allUser = new ArrayList<>();
        try{
            Connection con = loginDB.getCOnnection();
            PreparedStatement ps = con.prepareStatement("select * from users;");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("user_id")); 
                u.setUserName(rs.getString("username"));  // Set the username from DB
                u.setPassword(rs.getString("password")); // Set the password from DB
                allUser.add(u);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return allUser;
    }
}
