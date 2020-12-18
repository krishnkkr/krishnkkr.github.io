<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DASHBOARD</title>
<link rel="stylesheet" href="css/level1.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
	 <div class="logo">
        <img src="images/hrc-logo.svg" id="hrc-logo">
        <img src="images/abc-logo.png" id="abc-logo">    
    </div>
    <hr>
    <div class="grid">
   
    
   
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
                            <i class="glyphicon glyphicon-search"></i>
                            <input type="text" name="Order_ID" placeholder="search" class="fa fa-seach">
                        </form>
              </div>
        </div>
        <div>
            <table id="table">
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
	                	<td><input type="checkbox" id="krishn" value=-1 class="product-list" onclick="showprompt(this.checked)"/></td>
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
			    			$(this).parent().parent().css('background-color','#FC7500');
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
                   <a href="pagination_servlet_path?flag=11&page-number=${page}&level=${level}"><button class="page-start">&laquo;</button></a>
                   <a href="pagination_servlet_path?flag=22&page-number=${page}&level=${level}"><button class="page-prev">&#8249;</button></a>
                   page
                   <button name="page-number" class="page-of">${page}</button> of
                   <a href="pagination_servlet_path"><button class="total-page">${total-pg}</button></a>
                   <a href="pagination_servlet_path?flag=33&page-number=${page}&level=${level}"><button class="page-next">&#8250</button></a>
                   <a href="pagination_servlet_path?flag=44&page-number=${page}&level=${level}"><button class="page-skip">&raquo;</button></a>                   

       </div>
          
       
        
    </div> 
    
    <script src="js/level2.js"></script> 
</body>
</html>