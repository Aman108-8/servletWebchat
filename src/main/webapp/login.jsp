<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-box {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            width: 300px;
        }

        .login-box h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .login-box input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .login-box button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .login-box button:hover {
            background-color: #45a049;
        }
    </style>
    </head>
    <body>
        <div class="login-box">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/Login" method="post">
            <input type="text" name="userId" placeholder="User ID" required />
            <input type="password" name="password" placeholder="Password" required />
            <button type="submit">Login</button>
        </form>
            <div style="text-align: center; padding-top: 10px;">
                <a href="SignUp.jsp">Don't Have Account?</a>
            </div>
        <% 
            String errorMsg = (String) request.getAttribute("LoginMsg");
            if (errorMsg != null) {
        %>
            <div style="color: red; text-align: center;">
                <%= errorMsg %>
            </div>
        <%
            }
        %>
        
    </div>
    </body>
</html>
