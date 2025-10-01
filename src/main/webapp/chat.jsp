<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("userName");

    //List<User> frndList = (List<User>)request.getAttribute("frndList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Web Chat</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
        }

        .sidebar {
            width: 250px;
            background-color: #ffffff;
            border-right: 1px solid #ccc;
            padding: 20px;
        }

        .sidebar h3 {
            text-align: center;
            margin-bottom: 20px;
        }

        .friend {
            padding: 10px;
            margin-bottom: 10px;
            background-color: #eee;
            border-radius: 5px;
            cursor: pointer;
        }

        .friend:hover {
            background-color: #ddd;
        }

        .chat-container {
            flex: 1;
            display: flex;
            flex-direction: column;
            background-color: white;
        }

        .chat-header {
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            font-size: 20px;
            display: flex;
            justify-content: space-between; /* span left, a right */
            align-items: center;
        }
        
        .chat-header a{
            text-decoration: none;
            background-color: white;
            padding: 5px 10px;
        }
        .chat-header span{
            color: white;
            text-decoration: none;
            flex: 1;
            text-align:center;
        }

        .chat-search {
            display: flex;
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        .chat-search input[type="text"] {
            flex: 1;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .chat-search button {
            padding: 10px 20px;
            margin-left: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .chat-search button:hover {
            background-color: #1976D2;
        }

        .chat-messages {
            flex: 1;
            padding: 15px;
            overflow-y: auto;
            border-bottom: 1px solid #ccc;
        }

        .message {
            padding: 10px;
            margin-bottom: 10px;
            max-width: 100%;
            border-radius: 5px;
        }

        .message.me {
            background-color: #DCF8C6;
            align-self: flex-end;
            text-align: right;
        }

        .message.other {
            background-color: #FFF;
            align-self: flex-start;
        }

        .chat-input {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ccc;
        }

        .chat-input input[type="text"] {
            flex: 1;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .chat-input button {
            padding: 10px 20px;
            margin-left: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .chat-input button:hover {
            background-color: #45a049;
        }
        
        .message-time-Sender {
            font-size: 12px;
            color: gray;
            display: block;
            text-align: right;
            margin-top: 5px;
        }

        .message-time-Reciver {
            font-size: 12px;
            color: gray;
            display: block;
            text-align: left;
            margin-top: 5px;
        }

    </style>
</head>
<body>

    <div class="sidebar">
        <h3>Welcome, <b><%= userName %></b></h3>
        <h4>Friends</h4>
        <%@ page import="java.util.List" %>
        <%@ page import="Web.User" %>
        <%
            List<User> frndList = (List<User>)request.getAttribute("frndList");
            if(frndList == null){
                frndList = (List<User>)session.getAttribute("frndList");
            }
            if(frndList != null && !frndList.isEmpty()){
                for(User f : frndList){
        %>
                    <div class="friend">
                        <form action="chatOpen" method="post">
                            <input type="hidden" name="friendId" value="<%= f.getId() %>">
                            <button type="submit" name="friendName" value="<%= f.getUserName() %>" style="all:unset;cursor:pointer;">
                                <%= f.getUserName() %> 
                            </button>
                        </form>
                    </div>
        <%
                }
            } 
            else {
        %>
                <p>No friends yet</p>
        <%
            }
        %>
        <!-- Placeholder: You can dynamically show friend list here -->
    </div>

    <div class="chat-container">
        <div class="chat-header">
            <% 
                String friendName = (String) session.getAttribute("friendNameSet");
                if(friendName != null){ 
            %>
                    <span><%= friendName %></span>
            <% 
                } 
            %>
            <a href="login.jsp">Login</a>
        </div>

        <!-- Search bar to find friends -->
        <form class="chat-search" method="post" action="findFrnd">
            <input type="text" name="friendName" placeholder="Search for a friend..." required />
            <button type="submit">Find</button>
        </form>
        
        <%
            Integer loginUserId = (Integer) session.getAttribute("userId");
            Integer friendId = (Integer) session.getAttribute("friendId");
            List<Web.MessageObj> chatHistory = null;

            if(loginUserId != null && friendId != null) {
                chatHistory = Web.sql.chatSql.getMsg(loginUserId, friendId);
                session.setAttribute("chatHistory", chatHistory);
            } else {
                out.println("<p>Please select a friend to start chat.</p>");
            }
        %>
        
        <div class="chat-messages" id="chatMessages">
            <% if(chatHistory != null) {
                for(Web.MessageObj msg : chatHistory) {
                    boolean isMe = msg.getSender_id() == loginUserId;
            %>
                <div class="message <%= isMe ? "me" : "other" %>">
                    <%= msg.getMsg() %>
                    <span class="<%= isMe ? "message-time-Sender" : "message-time-Reciver" %>">
                        <%= msg.getTimestamp() %>
                    </span>
                </div>
            <%  }
            } %>
        </div>

        <form class="chat-input" method="post" action="DoChat">
            <input type="text" name="message" placeholder="Type a message..." required />
            <button type="submit">Send</button>
        </form>
        
        
    </div>

</body>
</html>