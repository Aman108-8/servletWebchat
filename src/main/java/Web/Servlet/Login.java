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

@WebServlet("/Login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String userName = req.getParameter("userId");
        String password = req.getParameter("password");

        if (userName != null && password != null) 
        {

            List<User> userList = userSql.getUser();
            User loginSuccess = null;

            for (User u : userList) {
                if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                    req.getSession().setAttribute("userId", u.getId());       // store numeric ID
                    req.getSession().setAttribute("userName", u.getUserName()); // store username

                    loginSuccess = u;
                    break;
                }
            }

            if (loginSuccess != null) 
            {
                System.out.println("Success");

                List<User> frndList = frndSql.getFrnd(loginSuccess.getUserName());
                req.getSession().setAttribute("frndList", frndList);

                req.getRequestDispatcher("chat.jsp").forward(req, resp);

            }
            else {
                req.setAttribute("LoginMsg", "Wrong userName and Password");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                System.out.println("failS");
            }
        }
    }
}
