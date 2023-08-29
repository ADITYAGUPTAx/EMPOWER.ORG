import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DataBase;

@WebServlet("/LoginCheckServlet1")
public class LoginCheckServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginCheckServlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cName = request.getParameter("NGO_ID");
		String passWord = request.getParameter("Password");
		RequestDispatcher rd;
		comm.DAO.DataBase db = new comm.DAO.DataBase();
		try {
			int result = db.check(cName, passWord);
			if (result == 0) {
				HttpSession session = request.getSession();
				String hss=(String) request.getAttribute("NGO_ID");
				session.setAttribute("NGO_ID", hss);
				rd = request.getRequestDispatcher("NGO_profile.jsp");
				rd.forward(request, response);
			} else if (result == 1) {
				response.getWriter().print("<div>Wrong password</div>");
				rd = request.getRequestDispatcher("login_ngo.html");
				rd.include(request, response);
			} else {
				response.getWriter().print("<div style='font-size:30px'>Account doesn't exist</div>");
				rd = request.getRequestDispatcher("signup_ngo.html");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}