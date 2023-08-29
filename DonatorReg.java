import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Donator")
public class DonatorReg extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hackathon","root","");
			Statement st=conn.createStatement();
			String uname="\""+req.getParameter("username")+"\"";
			String cno="\""+req.getParameter("cno")+"\"";
			String mail="\""+req.getParameter("email_id")+"\"";
			String address="\""+req.getParameter("address")+"\"";
			String pincode="\""+req.getParameter("pin_code")+"\"";
			String pass="\""+req.getParameter("pass")+"\"";
			String city="\""+req.getParameter("city")+"\"";
			String query="INSERT INTO `Donator`(`Name`, `CNumber`, `Email` ,`Address`, `City`, `Pincode`, `Password`) "
					+ "VALUES ( "+uname+","+cno+", "+mail+", "+address+", "+city+", "+pincode+"," +pass+")";
			System.out.println(query);
			if(st.executeUpdate(query)!=0)
			{
				resp.getWriter().print("<div>You have successfully sign in..Please login to continue...</div>");
				RequestDispatcher rd=req.getRequestDispatcher("LoginJSP.jsp");
				rd.include(req, resp);
			}
			st.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}