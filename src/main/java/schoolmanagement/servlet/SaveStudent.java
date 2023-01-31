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

@WebServlet("/saveStudent")
public class SaveStudent extends HttpServlet{ 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String stream = req.getParameter("stream");
		String fees = req.getParameter("fees");
		
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setStream(stream);
		s.setFees(fees);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolmanagement");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(s);
		et.commit();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.write("Studnet Added .....");
		
		RequestDispatcher rd = req.getRequestDispatcher("studentmenu.html");
		rd.include(req, resp);
		
	}
}
