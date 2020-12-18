<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
</head>
<body>
    
    <h1 id="order-managment">ORDER MANAGEMENT APPLICATION</h1>
    <div class="HRC-logo" id="hrclogo">
    <img src="images/hrc-logo.svg" >
    </div>
    <div class="login">
        <form action="LoginServletPath" method="post">
            <h3 class="SignIn">Sign in</h3>
            <div class="textbox">
                <label for="username"><b>Username</b></label>
                <br>
                <input type="text"  name="username" required>
            </div>
            <!-- <br> -->
            <c:if test="${not empty Error}">
  			<script>
  				alert("Form submitted");
			</script></c:if>
			<script type="text/javascript">
			var Msg ='<%=session.getAttribute("getAlert")%>';
	           if (Msg != "null") {
				 
				 alert("Wrong UserName Or Password");
				 
				 }
 			</script> 
            <div class="textbox">
                
                <label for="psw"><b>Password</b></label>
                <br>
                <input type="password" name="psw" required>
            </div>
            <br>
            <button type="submit" class="btn">Sign in</button>
        </form>
    </div>
    
</body>
</html>