<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/level1.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
	 <div class="logo">
        <img src="images/hrc-logo.svg" id="hrc-logo">
        <img src="images/abc-logo.png" id="abc-logo">    
    </div>
   
    <div class="grid">
     
    		<%if(session!=null && session.getAttribute("Level").equals(1)){%>
    		<div class="btn">
            <button class="add-button" id="myBtn">ADD</button>
            <input type="button" class="edit-button" value="EDIT" id="myBtn-edit" disabled/>
             <div class="search">
             
                        <form method="post" action="search-servlet?Level=1">
                            <i class="fa fa-search" style="color:#4FC4F7"></i>
                            <input type="text" name="Order_ID" placeholder="Search" class="fa fa-seach">
                        </form>
              </div>
        	</div>
    		<%}else if(session !=null && (session.getAttribute("Level").equals(2) || session.getAttribute("Level").equals(3))){%>
    			<div class="btn">    
             
             <form action="Addservlet?level=${level}&update=3" method="post" class="approve-reject-form">
	        	<input type="hidden" id="ORDER" name="ORDER-ID" value="">
	       		<button class="Approve-button" id="approve-button" disabled>Approve</button>
       		</form> 
            <form action="Addservlet?level=${level}&update=4" method="post" class="approve-reject-form">
	        	<input type="hidden" id="ORDER-rej" name="ORDER-ID-rej" value="">
	       		<button class="Reject-button" id="reject-button" disabled>Reject</button>
       		</form>
       	
             <div class="search">
                        <form method="post" action="search-servlet?Level=2">
                              <i class="fa fa-search" style="color:#4FC4F7"></i>
                            <input type="text" name="Order_ID" placeholder="search" class="fa fa-seach">
                        </form>
              </div>
        	</div>
    			
    		<%}else if(request.getSession(false)==null||session!=null ){%>
    		     
    		    RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request,response);
    	    <%}%> 
    	
        
        <div>
           <table id="table" class="table">
            <thead>
                <tr>
                	<th></th>
                    <th>Order Date</th>
                    <th>Approved By</th>
                    <th>Order Id</th>
                    <th>Company Name </th>
                    <th>Company ID</th>
                    <th>Order Amount</th>
                    <th>Approval Status</th>
                    <th>Notes</th>
                </tr>
             </thead>
              <tbody>
                          
                 <c:forEach var="msg" items="${message}">
	                <tr>
	                	<%if(session!=null){%>
    					<%if(session.getAttribute("Level").equals(1)){%>
	                		<td><input type="checkbox" class="product-list" id="chbox" onclick="myfunction(this.checked);"/></td>
	                	<%}else if(session.getAttribute("Level").equals(2)||session.getAttribute("Level").equals(3)){%>
	                		<td><input type="checkbox" id="krishn" value=-1 class="product-list" onclick="showprompt(this.checked)"/></td>
	                	<%}%>
	                		
    					<%}%>
	                	<td><c:out value="${msg.getOrder_Date()}" /></td>
	                	<td>${msg.getApproved_By()}</td>
	                	<td>${msg.getOrder_ID()}</td>
	                	<td>${msg.getCustomer_Name()}</td>
	                	<td>${msg.getCustomer_ID()}</td>
	                	<td>${msg.getOrder_Amount()}</td>
	                	<td>${msg.getApproval_Status()}</td>
	                	<td>${msg.getNotes()}</td>	                	
	                </tr>
	                
                </c:forEach>
                
                
                
                <script type="text/javascript">
                
		    		$('.product-list').on('change', function() {
			    	$('.product-list').not(this).prop('checked', false).parent().parent().css('background-color','');  
			    	if($(this).prop('checked'))
			    		{
			    			$(this).parent().parent().css('background-color','#ffdec1');
			    		}
			    	else
			    		{
				    		$(this).parent().parent().css('background-color','');
			    		}
					});
    			</script>
               </tbody>
               
             </table>
             
        </div>
        <div class="Pagination">
                   <a href="pagination_servlet_path?flag=1&page-number=${page}"><button class="page-start" style="border:none">&laquo;</button></a>
                   <a href="pagination_servlet_path?flag=2&page-number=${page}"><button class="page-prev" style="border:none">&#8249;</button></a>
                   page
                   <button name="page-number" class="page-of" style="border:none">${page}</button> of
                   <a href="pagination_servlet_path"><button class="total-page">${totalpg}</button></a>
                   <a href="pagination_servlet_path?flag=3&page-number=${page}"><button class="page-next" style="border:none">&#8250</button></a>
                   <a href="pagination_servlet_path?flag=4&page-number=${page}"><button class="page-skip" style="border:none">&raquo;</button></a>                   

       </div>
       <div id="myModal" class="modal">

                <!-- Modal content -->
                <div class="modal-content">
                  <span class="close">&times;</span>
                  <div class="header-add">
                     <h4 class="add-head">ADD ORDER</h4>      
                                           <hr >                     
                  </div>
                  <form action="Addservlet?level=1&update=0&page=${page}" method="post">
                                  <div class="textbox">
                                      <label for="order_id">Order Id</label>
                                      <input type="text" name="order_id" id="order_id" required>
                                  </div>
                                  <div class="textbox">
                                      <label for="order_date">Order Date</label>
                                      <input type="datetime-local" name="order_date" id="order_date" required >
                                  </div>
                                  <div class="textbox">
                                      <label for="company_Name">Customer Name</label>
                                      <input type="text" name="company_Name" id="company_Name" required>
                                  </div>    
                                  <div class="textbox">
                                      <label for="Company_Number">Customer ID</label>
                                      <input type="text" name="Company_Number" id="Company_Number" required>
                                  </div>
                                  <div class="textbox">
                                      <label for="Order_Amount">Order Amount</label>
                                      <input type="text" name="Order_Amount" id="Order_Amount" required>
                                  </div>
                                  <div class="textbox">
                                      <label for="Notes">Notes</label>
                                      <input type="text" name="Notes" id="Notes">
                                  </div>
                                  <a href=""><button class="ADD-BUTTON">ADD</button></a>
              
                    </form>
                </div>
            </div>
            </div>
            <div id="myModal-edit" class="modal-Edit">

                <!-- Modal content -->
                <div class="modal-content-edit">
                  <span class="close1">&times;</span>
                  <div class="header-add">
                     <h4 class="add-head">EDIT</h4>      
                                           <hr >                     
                  </div>
                  <form action="Addservlet?update=1&page=${page}" method="post">

                    <div class="textbox">
                        <label for="order_id">Order Id</label>
                        <input type="text" name="order_id-Edit" id="order_id-Edit">
                    </div>
                    <div class="textbox">
                        <label for="Order_Amount">Order Amount</label>
                        <input type="text" name="Order_Amount-Edit" id="Order_Amount-Edit" onchange="OnChange()">
                    </div>
                    <div class="textbox">
                        <label for="Notes">Notes</label>
                        <input type="text" name="Notes-Edit" id="Notes-Edit">
                    </div>
                    <div class="textbox">
                        <label for="Notes">Approved By</label>
                        <input type="text" name="Approved-By-Edit" id="Approved-By-Edit" value="">
                    </div>
                    <button class="ADD-BUTTON">SUBMIT</button>
                  </form>
                </div>
              
              </div>
        
             
              
           
         
    
    <script src="js/index.js"></script>
    <script src="js/level2.js"></script>
</body>
</html>