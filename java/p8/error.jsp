<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Exception Caught</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            
            .error-message {
                background-color: #f8d7da;
                color: #721c24;
                padding: 15px;
                border-radius: 8px;
                border: 1px solid #f5c6cb;
                margin: 20px 0;
                font-size: 15px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <div class="error-message">
            <h2><strong>Exception Type:</strong> <%= exception.getClass().getName() %></h2>
            <p><% out.print(exception.getMessage()); %></p>
        </div>
    </body>
</html>