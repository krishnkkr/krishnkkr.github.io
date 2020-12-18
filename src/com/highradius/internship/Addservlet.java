package com.highradius.internship;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Addservlet
 */
@WebServlet("/Addservlet")
public class Addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		// TODO Auto-generated method stub
//		doGet(request, response);
		if(request.getSession(false) == null)
		{
			System.out.println("session");
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
		String update=request.getParameter("update");
		int one =0;
		System.out.println("Update " + update);
		String level=request.getParameter("level");

		
		String page=request.getParameter("page");
		System.out.println("Page No "+page);
		int pg=1;
//		if(page!=null && page.length()>0)
//		{
//			pg=Integer.parseInt(page);
//		}
//		System.out.println("update.equals()"+ update.equals(0) + " update ="+update );
		if(update.equals("0")) {
			
			System.out.println("INSERT");
			Insert(request,response);
			one=0;
		}
		else if(update.equals("1")) {
			update(request,response);
			one=0;
		}
		else if(update.equals("3")) {
			Approve(request,response);
			one=1;
		}
		else if(update.equals("4"))
		{
			Reject(request,response);
			one=1;
		}
		

		
		if(one==0)
			response.sendRedirect(request.getContextPath() + "/pagination_servlet_path?" + "flag=1&page-number="+pg);
		else if(one == 1)
			response.sendRedirect(request.getContextPath() + "/pagination_servlet_path?" + "flag=1&level="+level+"&page-number="+pg);
		
	}
	public void Approve(HttpServletRequest request, HttpServletResponse response)
	{
		String Level=request.getParameter("level");
		String Order_Id=request.getParameter("ORDER-ID");
		
		System.out.println("Level " + Level);
		System.out.println("Order_ID" + Order_Id);
		int order_id=-1,level=-1;
		if(Order_Id!=null)
			order_id=Integer.parseInt(Order_Id);
		if(Level!=null && Level.length() > 0)
			level=Integer.parseInt(Level);
		
		UserDao userdao=new UserDao();
		userdao.Approve_Order(request, response, order_id, level);
		request.setAttribute("level",Level);
	}
	public void Reject(HttpServletRequest request, HttpServletResponse response)
	{
		String Level=request.getParameter("level");
		String Order_Id=request.getParameter("ORDER-ID-rej");
		
		
		System.out.println("Order_ID-rej" + Order_Id);
		int order_id=-1,level=-1;
		if(Order_Id!=null)
			order_id=Integer.parseInt(Order_Id);
		if(Level!=null && Level.length() > 0)
			level=Integer.parseInt(Level);
		HttpSession session = request.getSession();
		int lev=(int) session.getAttribute("Level");
		System.out.println("Level-rej " + lev);
		UserDao userdao=new UserDao();
		userdao.Reject_Order(request, response, order_id, lev);
		request.setAttribute("level",Level);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void update(HttpServletRequest request, HttpServletResponse response)
	{
		String OrderId=request.getParameter("order_id-Edit");
		String Order_Amount=request.getParameter("Order_Amount-Edit");
		String Notes=request.getParameter("Notes-Edit");
		String Approved_By=request.getParameter("Approved-By-Edit");
		System.out.println("Order_Id" + OrderId);
		System.out.println("Order_amount" + Order_Amount);
		System.out.println("Notes" + Notes);
		System.out.println("Approved By" + Approved_By);
		
		UserDao userdao=new UserDao();
		userdao.UpdateUser(OrderId,Order_Amount,Notes,Approved_By);
	}
	public void Insert(HttpServletRequest request, HttpServletResponse response)
	{
		 
		String Order_Id=request.getParameter("order_id");
		String Order_Date=request.getParameter("order_date");
		String Company_Name=request.getParameter("company_Name");
		String Company_Number=request.getParameter("Company_Number");
		String Order_Amount=request.getParameter("Order_Amount");
		String Notes=request.getParameter("Notes");
		String Level=request.getParameter("level");
		String Approved_By="";
		String Approved_Status="Awaiting Approval";
		
		System.out.println("Order_ID" + Order_Id);
		System.out.println("Order_Amount" + Order_Amount);
		System.out.println("Company_Number" + Company_Number);
		System.out.println("Company_Name" + Company_Name);
		System.out.println("Level "+ Level);
		System.out.println("Order_Amount.length()" + Order_Amount.length());
		System.out.println("Integer.parseInt(Order_Amount)" + Integer.parseInt(Order_Amount));
		if( Order_Amount.length()> 0  &&  (Integer.parseInt(Order_Amount)) <= 10000 )
		{
			System.out.println("sd");
			Approved_By="David Lee";
			Approved_Status="Approved";
			Notes="Order Approved";
		}
		
		User user=new User(Order_Id,Company_Name,Company_Number,Order_Amount,Approved_Status,Approved_By,Notes,Order_Date);
		UserDao userdao=new UserDao();
		System.out.println("Approved_Status "+ user.getApproval_Status());
		try {
			userdao.InsertUser(user);
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
		

	}
	
		
	
}
