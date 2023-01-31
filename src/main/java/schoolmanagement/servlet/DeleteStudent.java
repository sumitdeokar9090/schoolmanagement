package schoolmanagement.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schoolmanagement.entity.Student;
import schoolmanagement.entity.Teacher;

@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int id = Integer.parseInt(req.getParameter("id"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolmanagement");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Student s = em.find(Student.class, id);
		
		et.begin();
		em.remove(s);
		et.commit();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.write("Student Deleted...");
		
		RequestDispatcher rd = req.getRequestDispatcher("studentmenu.html");
		rd.include(req, resp);
	}
}
