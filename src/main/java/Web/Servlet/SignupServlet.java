package Web.Servlet;

import Web.User;
import Web.sql.userSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userId");
        String password = req.getParameter("password");

        if (userName != null && password != null) 
        {
            User u = new User();
            u.setUserName(userName);
            u.setPassword(password);

            if (userSql.setUser(u)>0) {
                System.out.println("SignUp Success");
                
                //req.getSession().setAttribute("userName", userName);
                resp.sendRedirect("login.jsp");
            } else {
                req.setAttribute("SignUpError", "choice another user ID.");
                req.getRequestDispatcher("SignUp.jsp").forward(req, resp);  // Failure → Show login page again with error
            }
        }
    }
}
