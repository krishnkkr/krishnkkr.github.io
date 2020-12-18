/**
 * 
 */

var bt = document.getElementById("APPROVE");


//bt.onclick = function() {
//	 
//	 var grid=document.getElementById("table");
//	 var checkbox = grid.getElementsByTagName("INPUT");
//
//	 
//	   for(var i=0; i<checkbox.length ; i++)
//	   {
//	     if(checkbox[i].checked)
//	     {
//	    	 
//	         var row=checkbox[i].parentNode.parentNode;
//	         document.getElementById("ORDER").value=row.cells[3].innerHTML;
//	         
//	         alert(row.cells[3].innerHTML);
//	        
//	     }
//	   }
//}
//

var checkedValue = null; 
var inputElements = document.getElementsByClassName('product-list');


function showprompt(ischecked)
{
	if(ischecked==true)
		{
			
		 var grid=document.getElementById("table");
		 var checkbox = grid.getElementsByTagName("INPUT");
	
		 
		   for(var i=0; i<checkbox.length ; i++)
		   {
		     if(checkbox[i].checked)
		     {
		    	 
		         var row=checkbox[i].parentNode.parentNode;
		         document.getElementById("ORDER").value=row.cells[3].innerHTML;
		         document.getElementById("ORDER-rej").value=row.cells[3].innerHTML;
		        
		         document.getElementById("reject-button").disabled=false;
		         document.getElementById("approve-button").disabled=false;
		        
		     }
		   }
	
		}
	else
		{
			document.getElementById("reject-button").disabled=true;
	        document.getElementById("approve-button").disabled=true;
		}
}