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
@WebServlet("/updateTeacher")
public class UpdateTeacher extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String sub = req.getParameter("subject");
		double sal = Double.parseDouble(req.getParameter("salary"));
		
		Teacher t = new Teacher();
		t.setId(id);
		t.setName(name);
		t.setSubject(sub);
		t.setSalary(sal);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolmanagement");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(t);
		et.commit();
		
		PrintWriter pw = resp.getWriter();
		pw.write("Teacher Updated....");
		RequestDispatcher rd = req.getRequestDispatcher("teachermenu.html");
		rd.include(req, resp);
		resp.setContentType("text/html");
	}
}
