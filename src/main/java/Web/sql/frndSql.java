package Web.sql;

import Web.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class frndSql {
    public static List<User> getFrnd(String userName){
        List <User> allUser;
        allUser = new ArrayList<>();
        try{
            Connection con = loginDB.getCOnnection();
            PreparedStatement ps = con.prepareStatement("SELECT \n" +
            "    f.id,\n" +
            "    u.user_id AS user_id,\n" +
            "    u.username AS user_name,\n" +
            "    fr.user_id AS friend_id,\n" +
            "    fr.username AS friend_name,\n" +
            "    f.created_at\n" +
            "FROM friendships f\n" +
            "JOIN users u ON f.userId = u.user_id\n" +
            "JOIN users fr ON f.friendId = fr.user_id\n" +
            "WHERE u.username = ?;");
            
            ps.setString(1,userName);
                    
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("friend_id"));          // ✅ correct friend’s id
                u.setUserName(rs.getString("friend_name"));
                allUser.add(u);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return allUser;
    }
    
    /*public static List<User> getFrndChat(int userId){
        List<User> allUser = new ArrayList<>();
        try {
            Connection con = loginDB.getCOnnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT f.id, " +
                "       u.user_id AS user_id, " +
                "       u.username AS user_name, " +
                "       fr.user_id AS friend_id, " +
                "       fr.username AS friend_name, " +
                "       f.created_at " +
                "FROM friendships f " +
                "JOIN users u ON f.userId = u.user_id " +
                "JOIN users fr ON f.friendId = fr.user_id " +
                "WHERE u.user_id = ?;"
            );

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("friend_id"));          // store friend’s id
                u.setUserName(rs.getString("friend_name"));
                allUser.add(u);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return allUser;
    }*/
    
    public static int setFrnd(int frndId, int User){
        int status = 0;
        try {
            List<User> checkUser= userSql.getUser();
            
            Connection con = loginDB.getCOnnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO friendships (userId, friendId) VALUES (?, ?);");
            ps.setInt(1,User);
            ps.setInt(2,frndId);

            status = ps.executeUpdate();

            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return status;
    }
}
