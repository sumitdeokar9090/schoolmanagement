<%@page import="schoolmanagement.entity.Teacher"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
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
	
	Teacher t = em.find(Teacher.class, id);
	
	%>
	
	<form action="updateTeacher" method="post">
		id:  <input type="text" name="id" value=<%= t.getId()%>>
		name:  <input type="text" name="name" value=<%= t.getName()%>>
		subject:  <input type="text" name="subject" value=<%= t.getSubject() %>>
		salary:   <input type="text" name="salary" value=<%= t.getSalary()%>>
		<input type="submit">
	</form>
</body>
</html>