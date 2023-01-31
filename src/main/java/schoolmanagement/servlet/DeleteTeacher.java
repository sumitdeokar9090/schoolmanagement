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

import schoolmanagement.entity.Teacher;

@WebServlet("/deleteTeacher")
public class DeleteTeacher extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolmanagement");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Teacher t = em.find(Teacher.class, id);
		
		et.begin();
		em.remove(t);
		et.commit();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.write("Teacher Deleted...");
		
		RequestDispatcher rd = req.getRequestDispatcher("teachermenu.html");
		rd.include(req, resp);
	}
}
