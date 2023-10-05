<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter,java.util.List" %>
<%@ page import="model.UserDataList,model.UserData" %>

<%
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
    String stylesPath = getServletContext().getContextPath() + "/src/css/errorPageStyles.css"; 
%>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Reshetov Semen P3206 WEB 2lab</title>
    <meta name="description" content="Reshetov Semen P3206 WEB 2lab">
    <meta name="author" content="Reshetov Semen">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Nunito+Sans:opsz@6..12&family=Poppins:wght@400;500;600;700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="<%= stylesPath %>">
</head>

<body>
    <header>
        <div class="introduction">
            <h3>First web laboratory</h3>
            <strong>Reshetov Semen, P3206. Variant: 1623</strong>
        </div>
        <div class="links">
            <form action="https://se.ifmo.ru/courses/web" target="_blank">
                <button class="nav-button">
                    SE VITMO
                </button>
            </form>
            <form action="https://github.com/rsp243" target="_blank">
                <button class="nav-button">
                    Github account
                </button>
            </form>
            <form action="https://t.me/rsp243" target="_blank">
                <button class="nav-button">
                    Telegram account
                </button>
            </form>
        </div>
    </header>

    <div class="main-container">
        <div class="error-info">
            <h2>An error occured: <div class="error"><%= statusCode %> <%= errorMessage %></div>
            </h2>
        </div>
        <div class="btn-block">
            <a href="${pageContext.request.contextPath}"><button class="control-btn">Back To The Main Page</button></a>
        </div>
    </div>
</body>

</html>⏎ 