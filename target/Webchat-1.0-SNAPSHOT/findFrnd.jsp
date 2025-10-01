<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("userName");

    if (userName == null) {
        response.sendRedirect("login.jsp");  // Redirect to login if not logged in
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Check - Web Chat</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 50px;
        }

        .welcome-header {
            font-size: 24px;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .check-box {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            width: 400px;
        }

        .check-box h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .check-box input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .check-box button {
            width: 100%;
            padding: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .check-box button:hover {
            background-color: #1976D2;
        }

        .result-message {
            margin-top: 15px;
            text-align: center;
            font-size: 18px;
        }

        .result-available {
            color: green;
        }

        .result-unavailable {
            color: red;
        }
    </style>

</head>

<body>
    <div class="welcome-header">
        Welcome, <%= userName %>
    </div>

    <div class="check-box">
        <h2>Check User Availability</h2>

        <form action="${pageContext.request.contextPath}/findFrnd" method="post">
            <input type="text" name="userName" placeholder="Enter User ID" required />
            <button type="submit">Check</button>
        </form>

        <div id="resultBox" class="result-message"></div>
    </div>
</body>
</html>
