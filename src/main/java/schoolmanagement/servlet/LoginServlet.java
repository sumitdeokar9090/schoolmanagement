package schoolmanagement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schoolmanagement.entity.Principal;

@WebServlet("/loginvalidation")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String pass = req.getParameter("password");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolmanagement");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select p from Principal p where email = ?1 and password = ?2");
		q.setParameter(1, name);
		q.setParameter(2, pass);
		List<Principal> p = q.getResultList();
		
		if(p.size() > 0) {
			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();
			pw.write("Login Successfull !");
			
			RequestDispatcher rd = req.getRequestDispatcher("choise.html");
			rd.include(req, resp);
		}else {
			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();
			pw.write("Invalid Email or Password!");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, resp);
		}
			
	}
}
