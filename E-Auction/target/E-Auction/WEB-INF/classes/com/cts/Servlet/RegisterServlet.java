package com.cts.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="RegisterServlet",urlPatterns={"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			PrintWriter out=response.getWriter();
			java.sql.Connection con=null;
			java.sql.PreparedStatement pstmt=null;
			String userId=request.getParameter("userId");
			String firstname=request.getParameter("fname");
			String lastname=request.getParameter("lname");
			String password=request.getParameter("password");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String paypal=request.getParameter("paypal");
			
			//String query="insert into Detail values(?,?,?)";
			try{
				con=DBConnection.getConnection();
			pstmt=con.prepareStatement("insert into customer(userId,firstname,lastname,password,address,phone,paypal,email) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, userId);
			pstmt.setString(2, firstname);
			pstmt.setString(3, lastname);
			pstmt.setString(4, password);
			pstmt.setString(5, address);
			pstmt.setString(6, phone);
			pstmt.setString(7, paypal);
			pstmt.setString(8, email);
			
			int i=pstmt.executeUpdate();	
			if(i!=0){
			//out.println("running");
			RequestDispatcher rd=request.getRequestDispatcher("Success.jsp");  
	        rd.include(request, response);
			}else{
				out.println("not running");
				RequestDispatcher rd= request.getRequestDispatcher("Fail.jsp");
				rd.forward(request, response);
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					pstmt.close();
					con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

}
