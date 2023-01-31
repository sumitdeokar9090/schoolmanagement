<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="schoolmanagement.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		int id = Integer.parseInt(request.getParameter("id"));
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolmanagement");
	EntityManager em = emf.createEntityManager();
	
	Student s = em.find(Student.class, id);
	
	%>
	
	<form action="updateStudent" method="post">
		ID : <input type="text" name="id" value=<%= s.getId() %>>
		Name : <input type="text" name="name" value=<%= s.getName() %>>
		Stream : <input type="text" name="stream" value=<%= s.getStream()%>>
		Fees : <input type="text" name="fees" value=<%= s.getFees()%>>
		<input type="submit">
	</form>
</body>
</html>