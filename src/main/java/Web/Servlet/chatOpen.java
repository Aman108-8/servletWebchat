package Web.Servlet;

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

@WebServlet("/chatOpen")
public class chatOpen extends HttpServlet {

    @Override
    /*protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        int loginUserId = (int)req.getSession().getAttribute("userId");
        String loginUserName = (String) req.getSession().getAttribute("userName");
        
        System.out.println(friendId);
        System.out.println(loginUserId);
        
        req.getSession().setAttribute("friendId", friendId);
        List<MessageObj> chatHistory = chatSql.getMsg(loginUserId, friendId);
        req.setAttribute("chatHistory", chatHistory);
        
        List<User> frndList = frndSql.getFrnd(loginUserName);

        // Put in both session (persistent) and request (first render)
        req.getSession().setAttribute("frndList", frndList);
        req.setAttribute("frndList", frndList);
        
        req.getRequestDispatcher("chat.jsp").include(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }*/
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        String friendName = req.getParameter("friendName");
        
        int loginUserId = (int) req.getSession().getAttribute("userId");

        // store friendId in session
        req.getSession().setAttribute("friendId", friendId);
        req.getSession().setAttribute("friendNameSet", friendName);

        // fetch chat history
        List<MessageObj> chatHistory = chatSql.getMsg(loginUserId, friendId);
        req.getSession().setAttribute("chatHistory", chatHistory); // store in session

        // redirect to chat.jsp (URL will not show friendId)
        resp.sendRedirect("chat.jsp");
    }
}
