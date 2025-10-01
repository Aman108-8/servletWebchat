package Web.Servlet;

import Web.User;
import Web.sql.frndSql;
import Web.sql.userSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findFrnd")
public class findFrnd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String friendName = req.getParameter("friendName");
        //String userName = req.getParameter("userName");
        
        Integer userId = (Integer) req.getSession().getAttribute("userId"); 
        String userName = (String) req.getSession().getAttribute("userName");
            
        if (friendName != null) 
        {
            if (userId == null || userName == null) {
                // session expired or user not logged in
                resp.sendRedirect("login.jsp");
                return;
            }

            User frnd = null;
            List<User> userList = userSql.getUser();

            for (User u : userList) {
                if ((u.getUserName().equals(friendName))&& !(u.getUserName().equals(userName))) {
                    frnd = u;
                    break;
                }
            }

            if (frnd != null) 
            {
                System.out.println("User Found: " + frnd.getUserName());
                frndSql.setFrnd(frnd.getId(), userId);
            } 
            else 
            {
                System.out.println("Friend not available");
                //req.getRequestDispatcher("chat.jsp").forward(req, resp);
            }
            
        }
        List frndList = frndSql.getFrnd(userName);
        req.setAttribute("frndList", frndList);
        req.getSession().setAttribute("frndList", frndList);
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
}
