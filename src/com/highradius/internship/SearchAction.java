package com.highradius.internship;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchAction
 */
@WebServlet("/search-servlet")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession(false) == null)
		{
			System.out.println("session");
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String s=request.getServletPath();
		if(request.getSession(false) == null)
		{
			System.out.println("session");
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
		
		try {
			
				SearchList(request.getParameter("Order_ID"),request,response);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public void SearchList(String OrderID,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException
	{
		System.out.println("asdsadsa" + OrderID);
		List<User> ls=null;
		if(OrderID.length()==0)
			ls=new UserDao().getUserList(request,response,0);
		else
			ls=new UserDao().SearchedList(OrderID);
			
		String Level=request.getParameter("Level");
		
		System.out.println("Level: "+ Level);
		
		request.setAttribute("message",ls);
		request.setAttribute("page", 1);
		request.setAttribute("totalpg", 1);
		RequestDispatcher dispatcher;
		if(Level.equals("1"))
			dispatcher= request.getRequestDispatcher("level1.jsp");
		else
			 dispatcher= request.getRequestDispatcher("level1.jsp");
		dispatcher.forward(request,response);
	}
}
