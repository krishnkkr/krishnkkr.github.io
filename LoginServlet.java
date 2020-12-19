package com.highradius.internship;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServletPath")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get data of user details from DatabaseConnection.java.
		String username=request.getParameter("username");
		String password=request.getParameter("psw");
//		HttpSession session = request.getSession();
		HttpSession session = request.getSession();
		if (session == null) {
		    // a not session exists
			System.out.println("session");
			session= request.getSession(true);
			response.sendRedirect("index.html");
		}
		
		String query="SELECT * FROM user_details"; 
//		PrintWriter out = response.getWriter(); 
		    Statement st = null;
		    ResultSet res = null;
			try {
				
				new DatabaseConnection();
				st = DatabaseConnection.initializeDatabase().createStatement();			
				
				res = st.executeQuery(query);
				
			}catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	      
         
	        String level="";
	        
	        try {
				while(res.next())
				{
					
					if(res.getString(2).equals(username) && res.getString(3).equals(password))
						level=res.getString(4);
				}
			} 
	        catch (SQLException e) {
				
				e.printStackTrace();
			}
  
	
	     if(level.length()==0)
		 {
			 //out.print("UserName and Password is incorrect");
			 request.setAttribute("Error", "wrong");
			 session.setAttribute("getAlert", "Yes");
	    	 session.setAttribute("Level", 0);
			 response.sendRedirect("login.jsp");
			 
		 }
		 else if(level.equals("Level 1"))
		 {
//		
			 System.out.println("sd");
			 
			 try {
				 request.setAttribute("level","1");
				 session.setAttribute("Level",1);
				 ListUser(request, response,"level1.jsp");
				 
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else if(level.equals("Level 2") || level.equals("Level 3") )
		 {
			 try {
					
//				 User user=new User();
					if(level.equals("Level 2")) { 
						request.setAttribute("level","2");
						session.setAttribute("Level",2);
						
					}
					else {
						request.setAttribute("level","3");
						session.setAttribute("Level",3);
						
					}
					ListUser(request, response,"level1.jsp");
					System.out.println("LEVEL INSIDE LOGIN " + level);
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}
		 }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}
	
	public void ListUser(HttpServletRequest request, HttpServletResponse response,String LevelJSP) throws SQLException, IOException, ServletException, ClassNotFoundException 
	{
		List<User> ls=new UserDao().getUserList(request,response,0);
		int total=2;
		Statement st=null;
		ResultSet res=null;
		new DatabaseConnection();
		st = DatabaseConnection.initializeDatabase().createStatement();
		res = st.executeQuery("select count(*) from order_details");
		res.next();
		
		total=(res.getInt(1)/10);
		System.out.println("s"+ total);
		
		request.setAttribute("totalpg", total);
		request.setAttribute("page", 1);
		
		request.setAttribute("message",ls);
		RequestDispatcher dispatcher= request.getRequestDispatcher(LevelJSP);
		dispatcher.forward(request,response);
	}

}
