//package Web.Servlet;

import Web.MessageObj;
import Web.User;
import Web.sql.chatSql;
import Web.sql.frndSql;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DoChat")
public class DoChat extends HttpServlet {

    @Override
    /*protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        int friendId = (int)req.getSession().getAttribute("friendId");
        int loginUserId = (int)req.getSession().getAttribute("userId");
        
        if (message != null && !message.trim().isEmpty()) {
            chatSql.setMsg(loginUserId, friendId, message);
            List<MessageObj> chatHistory = chatSql.getMsg(loginUserId, friendId);
        }
        resp.sendRedirect("chatOpen?friendId=" + friendId);
        
    }*/
    
    
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        if (message != null && !message.trim().isEmpty()) {
            int loginUserId = (int) req.getSession().getAttribute("userId");
            int friendId = (int) req.getSession().getAttribute("friendId");

            // save message
            chatSql.setMsg(loginUserId, friendId, message);
        }

        // redirect to chat.jsp to reload messages (PRG pattern)
        resp.sendRedirect("chat.jsp");
    }
}
