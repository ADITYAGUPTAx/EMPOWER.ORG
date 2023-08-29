import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

/**
 * Servlet implementation class UpdateNGODB
 */
@WebServlet("/UpdateNGODB")
public class UpdateNGODB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackathon", "root", "");
			Statement statement = connection.createStatement();
			String s = request.getParameter("qtyval");
			System.out.println(s);
			int req_item = Integer.parseInt(request.getParameter("qtyval"));

			ResultSet rs = statement.executeQuery("select * from  item_stock");
			rs.next();

			int curr_item = Integer.parseInt(rs.getString(3));
			int curr_req = Integer.parseInt(rs.getString(4));
			curr_item += req_item;
			curr_req -= req_item;

			rs.next();
			int curr_a2 = Integer.parseInt(rs.getString(3));
			curr_a2 -= req_item;

			rs.close();

			String sql = "update item_stock set avail_quantity=" + curr_item + ",required_quantity="+curr_req+" where item_ngo_id='1000'";
			statement.executeUpdate(sql);
			String sql1 = "update item_stock set avail_quantity=" + curr_a2 + " where item_ngo_id='1001'";
			statement.executeUpdate(sql1);
			PrintWriter out = response.getWriter();

			RequestDispatcher rd = request.getRequestDispatcher("dashboard_ngo.jsp");
			out.print("<div>Request Successful</div>");
			rd.include(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}