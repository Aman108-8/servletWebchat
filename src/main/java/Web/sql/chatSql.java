package Web.sql;

import Web.MessageObj;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class chatSql {
    /*public static List<MessageObj> getMsg(int senderId, int ReceiverId){
        List<MessageObj> msg = null;
        
        try {
            Connection con = loginDB.getCOnnection();
            PreparedStatement ps = con.prepareStatement("SELECT *\n" +
            "FROM chat_messages\n" +
            "WHERE (sender_id = :loginUserId AND receiver_id = :friendId)\n" +
            "OR (sender_id = :friendId AND receiver_id = :loginUserId)\n" +
            "ORDER BY timestamp ASC;");
            
            
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        
        return msg;
    }*/
    
    public static List<MessageObj> getMsg(int loginUserId, int friendId) {
    List<MessageObj> msgList = new ArrayList<>();
    
    try {
        Connection con = loginDB.getCOnnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * " +
            "FROM chat_messages " +
            "WHERE (sender_id = ? AND receiver_id = ?) " +
            "   OR (sender_id = ? AND receiver_id = ?) " +
            "ORDER BY timestamp ASC"
        );

        // bind values
        ps.setInt(1, loginUserId);
        ps.setInt(2, friendId);
        ps.setInt(3, friendId);
        ps.setInt(4, loginUserId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            MessageObj m = new MessageObj();
            m.setMsg(rs.getString("message"));
            m.setSender_id(rs.getInt("sender_id"));
            m.setReceiver_id(rs.getInt("receiver_id"));
            m.setTimestamp(rs.getTimestamp("timestamp"));  // if you add timestamp in MessageObj
            msgList.add(m);
        }

        con.close();
    } catch (Exception e) {
        System.out.println("Error in getMsg: " + e);
    }

    return msgList;
}

    
    public static int setMsg(int sender_id, int receiver_id, String message){
        int status = 0;
        
        try 
        {
            Connection con = loginDB.getCOnnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO chat_messages (sender_id, receiver_id, message) VALUES (?, ?, ?);");
            ps.setInt(1, sender_id);
            ps.setInt(2, receiver_id);
            ps.setString(3, message);
            
            status = ps.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(chatSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }
}
