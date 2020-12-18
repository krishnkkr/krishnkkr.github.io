package com.highradius.internship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class UserDao  {
	
	
	//DAO-Data Access Object pattern.
	
	
	
	private String INSERT_USER_SQL="INSERT INTO order_details" + "(Order_ID,Customer_Name,Customer_ID,Order_Amount,Approval_Status,Approved_By,Notes,Order_Date) VALUES" + "(?,?,?,?,?,?,?,?)";
//	private String SELECT_ALL_USER="SELECT * FROM order_details";

	
	
	
	ResultSet res = null;
	Statement st = null;
	
	public void InsertUser(User user) throws SQLException, ClassNotFoundException{
		try
		{
			Connection connection=null;
			//new DatabaseConnection();
			
			int order_id=Integer.parseInt(user.getOrder_ID());
			int customer_id=Integer.parseInt(user.getCustomer_ID());
			int customer_amount=Integer.parseInt(user.getOrder_Amount());
			
			
			
			connection=DatabaseConnection.initializeDatabase();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
			preparedStatement.setInt(1, order_id);
			preparedStatement.setString(2, user.getCustomer_Name());
			preparedStatement.setInt(3, customer_id);
			preparedStatement.setInt(4, customer_amount);
			preparedStatement.setString(5, user.getApproval_Status());
			preparedStatement.setString(6, user.getApproved_By());
			preparedStatement.setString(7, user.getNotes());
			preparedStatement.setString(8, user.getOrder_Date());
			preparedStatement.executeUpdate();
			
			System.out.println("sd");
			
			
		}
		catch (SQLException E) {
			E.printStackTrace();
		}
	}
	public List<User> getUserList(HttpServletRequest request, HttpServletResponse response,int offset) throws ClassNotFoundException 
	{
		List<User> list= new ArrayList<>();
		Statement st=null;
		ResultSet res=null;
		
		try
		{
			new DatabaseConnection();
			st = DatabaseConnection.initializeDatabase().createStatement();
//			res = st.executeQuery("select count(*) from order_details");
//			res.next();
			
			String query="SELECT * FROM Order_details LIMIT 10 OFFSET " + offset;
			res=st.executeQuery(query);
			
			while(res.next())
			{
				
				String Order_ID,Customer_Name,Customer_ID,Order_Amount,Approval_Status,Approved_By,Notes,Order_Date;
				Order_ID=res.getString("Order_ID");
				Customer_Name=res.getString("Customer_Name");
				Customer_ID=res.getString("Customer_ID");
				Order_Amount=res.getString("Order_Amount");
				Approval_Status=res.getString("Approval_Status");
				Approved_By=res.getString("Approved_By");
				Notes=res.getString("Notes");
				Order_Date  = res.getString("Order_Date");
				
				User user=new User(Order_ID,Customer_Name,Customer_ID,Order_Amount,Approval_Status,Approved_By,Notes,Order_Date);
				list.add(user);
				//System.out.println("Sd");
				
				
			}
			
		}
		catch (SQLException E) {
			
		}
		return list;
	}
	public void UpdateUser(String Order_Id,String Order_Amount,String Notes, String Approved_By) {
		
		int order_id=Integer.parseInt(Order_Id);
		int order_amount=Integer.parseInt(Order_Amount);
		
		
		
		String query="UPDATE order_details set Order_Amount =" + "?" +",Notes=" + "?" + ",Approved_By = " + "?" +" where Order_ID=" +"?";
		Connection connection=null;
		try {
			connection=DatabaseConnection.initializeDatabase();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, order_amount);
			pst.setString(2, Notes);
			pst.setString(3, Approved_By);
			pst.setInt(4, order_id);	
			pst.executeUpdate();
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	public List<User> SearchedList(String ORDER_id) throws ClassNotFoundException{
		
		
		List<User> list= new ArrayList<>();
		int order_id=Integer.parseInt(ORDER_id);
		String SEARCH_USER_USING_ID="SELECT * FROM order_details where Order_ID like" + "'%" +order_id+ "%'"+"limit 10";
		Connection connection = null;
		Statement st=null;
		ResultSet res=null;
		
		
		
		  
		try
		{
			//new DatabaseConnection();
			connection = DatabaseConnection.initializeDatabase();
			PreparedStatement pst=connection.prepareStatement(SEARCH_USER_USING_ID);
			
			//pst.setInt(1, order_id);
			//System.out.println(SEARCH_USER_USING_ID);
			res = pst.executeQuery();
			
			
			
			while(res.next())
			{
				
				
				
				String Order_ID,Customer_Name,Customer_ID,Order_Amount,Approval_Status,Approved_By,Notes,Order_Date;
				Order_ID=res.getString("Order_ID");
				Customer_Name=res.getString("Customer_Name");
				Customer_ID=res.getString("Customer_ID");
				Order_Amount=res.getString("Order_Amount");
				Approval_Status=res.getString("Approval_Status");
				Approved_By=res.getString("Approved_By");
				Notes=res.getString("Notes");
				Order_Date  = res.getString("Order_Date");
				
				User user=new User(Order_ID,Customer_Name,Customer_ID,Order_Amount,Approval_Status,Approved_By,Notes,Order_Date);

				
				list.add(user);

				
				
				
			}
			
		}
		catch (SQLException E) {
			
		}
		return list;
	}
	public void Approve_Order(HttpServletRequest request, HttpServletResponse response,int order_id,int level)
	{
		int order_amount=0;
		Statement st=null;
		ResultSet res=null;
		String Approved="Approved";
		try {
			st = DatabaseConnection.initializeDatabase().createStatement();
			String Query_Amount="SELECT Order_Amount FROM order_details WHERE Order_ID="+order_id;
			res=st.executeQuery(Query_Amount);
			res.next();
			order_amount=res.getInt(1);
			
			Connection connection=null;
			if(level ==2 && (order_amount >10000 && order_amount < 50000))
			{
				System.out.println("level kk 2");
				String query="Update order_details set Approval_Status = " +"?"+ "where Order_Id=" +"?";
				connection=DatabaseConnection.initializeDatabase();
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, Approved);
				pst.setInt(2, order_id);
				pst.executeUpdate();
				
			}
			else if(level==3 && (order_amount>50000))
			{
				String query="Update order_details set Approval_Status = " +"?"+ "where Order_Id=" +"?";
				connection=DatabaseConnection.initializeDatabase();
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, Approved);
				pst.setInt(2, order_id);
				pst.executeUpdate();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Reject_Order(HttpServletRequest request, HttpServletResponse response,int order_id,int level)
	{
		int order_amount=0;
		Statement st=null;
		ResultSet res=null;
		String Approved="Reject";
		try {
			st = DatabaseConnection.initializeDatabase().createStatement();
			String Query_Amount="SELECT Order_Amount FROM order_details WHERE Order_ID="+order_id;
			res=st.executeQuery(Query_Amount);
			res.next();
			order_amount=res.getInt(1);
			
			Connection connection=null;
			if(level ==2 && (order_amount >10000 && order_amount < 50000))
			{
				System.out.println("level kk 2");
				String query="Update order_details set Approval_Status = " +"?"+ "where Order_Id=" +"?";
				connection=DatabaseConnection.initializeDatabase();
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, Approved);
				pst.setInt(2, order_id);
				pst.executeUpdate();
				
			}
			else if(level==3 && (order_amount>50000))
			{
				String query="Update order_details set Approval_Status = " +"?"+ "where Order_Id=" +"?";
				connection=DatabaseConnection.initializeDatabase();
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, Approved);
				pst.setInt(2, order_id);
				pst.executeUpdate();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
