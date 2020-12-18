package com.highradius.internship;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javafx.util.Pair;

/**
 * Servlet implementation class pagination_servlet
 */
@WebServlet("/pagination_servlet_path")
public class pagination_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pagination_servlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getSession(false) == null)
		{
			System.out.println("session");
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

			Pair<String,Integer> pair=new Pair<>("",0);
			pair=OffSetSeter(request,response);
			
			int offset=0;
			String Level="";
			
			offset=pair.getValue();
			Level=pair.getKey();
			
			
			System.out.println(offset);
			List<User> ls=null;
			
			try 
			{
				ls = new UserDao().getUserList(request,response,offset);
			}
			catch (ClassNotFoundException e) 
			{
				
				e.printStackTrace();
			}
			
			
			
			request.setAttribute("message",ls);
			RequestDispatcher dispatcher= request.getRequestDispatcher("level1.jsp");
			dispatcher.forward(request,response);
			
	}
	
	public Pair<String,Integer> OffSetSeter(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		
		int offset=0;
		Statement st=null;
		ResultSet res=null;
		int page_no=1;
		System.out.println("Page no inside pagination: "+request.getParameter("page-number"));
		if(request.getParameter("page-number")!=null)
			page_no=Integer.parseInt(request.getParameter("page-number"));
		int total_row=0,task=0;
		
		if(request.getParameter("flag")!=null)
		 task=Integer.parseInt(request.getParameter("flag"));
		
		String Level="level1.jsp";
		if(task>10) {
			Level="level1.jsp";
			task%=10;
		}
		int total_page=0;
		
		  
			try {
				
				st = DatabaseConnection.initializeDatabase().createStatement();
			
				res = st.executeQuery("select count(*) from order_details");
			
				res.next();
			
			
				total_row=res.getInt(1);
			}
			 catch (SQLException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
			if(total_row%10!=0)
				total_page=(total_row/10)+1;
			else
				total_page=total_row/10;
			System.out.println("Page_no " + page_no);
			switch(task){  // Here Task refers to next,skip,previos in Pagination
		        
			    case 1:
				{
							
					offset=0;
					page_no=1;
					break;
				}
				case 2:
					
					
					if(page_no!=1)
					{
						offset=((page_no)-1)*10-10;
						page_no--;
					}
					else
					{
						offset=0;
					}
					
					break;
				
				case 3:
				
					if(page_no!=total_page)
					{
						offset=(page_no)*10;
						page_no++;
					}
					else  {
						offset=(page_no-1)*10;
						
					}
					System.out.println("case 3");
					
					break;
					
				
				case 4:
				
					offset=(total_page-1)*10;
					page_no=total_page;
					break;
				
				default:
					offset=0;
					break;
		      }
			HttpSession session = request.getSession();
			int lev=(int) session.getAttribute("Level");
			
			String level=request.getParameter("level");
			System.out.println("INSIDE Level +" + lev);
			request.setAttribute("level",lev);
			request.setAttribute("totalpg", total_page);
			request.setAttribute("page", page_no);
			
			return new Pair<>(Level,offset);
	}
	
}
