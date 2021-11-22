 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management App</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body> 
<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> User
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
     <br>
   <div class="container">
   <h3 class="text-center">List of Users</h3>
   <hr>
   
   <div class="container text-left">
   
   <a href="<%= request.getContextPath()%>/new" class="btn btn-success"> Add new User</a>
    </div>
    <br>
    <table class="table table-bordered">
    <thead>
    <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Country</th>
    <th>Actions</th>
    </tr>
    
    </thead>
    <tbody>
    <c:forEach var="user" items="${listUser}">
    
    <tr>
    <td>
    <c:out value="${user.id}"></c:out>
    </td>
    <td><c:out value="${user.name}"></c:out></td>
    <td><c:out value="${user.email}"></c:out></td>
    <td><c:out value="${user.country}"></c:out></td>
    <td><a href="edit?id=<c:out value='${user.id}'/>">Edit</a>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="delete?id=<c:out value='${user.id}'/>">Delete</a>
    </td>
    </tr>
    
    
    
    </c:forEach>
    
    
    
    </tbody>
    
    </table>
   </div>  
     
     

      

</body>
</html>