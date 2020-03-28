package com.cts.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		Connection con=null;
		Statement st=null;
		String id=null;
		String name=null;
		String password=null;
		
		name=request.getParameter("username");
		password=request.getParameter("password");
		int flag=0;
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			
			ResultSet rs=st.executeQuery("select * from customer");
			System.out.println(id+" "+name+" "+password);
			
			while(rs.next()){
				if(rs.getString(2).equalsIgnoreCase(name)&& rs.getString(3).equals(password)){
					flag=1;
					break;
				}
			}
				if(flag==1){
					RequestDispatcher rd=request.getRequestDispatcher("Success.jsp");
					rd.forward(request,response);
				}
				else{
					RequestDispatcher rd=request.getRequestDispatcher("Fail.jsp");
					rd.forward(request,response);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		
	}
	}

}
