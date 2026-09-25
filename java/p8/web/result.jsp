<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculation Result</title>
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
        .result-box {
            font-size: 22px;
            color: #28a745;
            margin: 20px 0;
            padding: 15px;
            background: #eafaf1;
            border-radius: 8px;
            border: 1px solid #c3e6cb;
        }
    </style>
</head>
<body>

    <%
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String op = request.getParameter("op");

        // Parsing inputs as integers to force ArithmeticException on zero division
        int a = Integer.parseInt(num1Str);
        int b = Integer.parseInt(num2Str);
        int result = 0;

        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "x":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
    %>

    <div class="result-box">
        <%= a %> <%= op %> <%= b %> = <strong><%= result %></strong>
    </div>

</body>
</html>
