
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
	
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
  document.getElementById("myBtn-edit").disabled=true;
  
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}









var mod = document.getElementById("myModal-edit");

//Get the button that opens the modal
var bt = document.getElementById("myBtn-edit");

//Get the <span> element that closes the modal
var span1 = document.getElementsByClassName("close1")[0];

//When the user clicks the button, open the modal 
bt.onclick = function() {
  mod.style.display = "block";
}

//When the user clicks on <span> (x), close the modal
span1.onclick = function() {
	
	document.getElementById("chbox").checked=false;
	mod.style.display = "none";
}

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
if (event.target == modal-Edit) {
 mod.style.display = "none";
}
}

bt.onclick = function() {
 mod.style.display = "block";
 var grid=document.getElementById("table");
 var checkbox = grid.getElementsByTagName("INPUT");

 
   for(var i=0; i<checkbox.length ; i++)
   {
     if(checkbox[i].checked)
     {
         var row=checkbox[i].parentNode.parentNode;
         document.getElementById("order_id-Edit").value=row.cells[3].innerHTML;
         document.getElementById("Order_Amount-Edit").value=row.cells[6].innerHTML;
         document.getElementById("Notes-Edit").value=row.cells[8].innerHTML;
         document.getElementById("Approved-By-Edit").value=row.cells[2].innerHTML;
         
         
         var amount=row.cells[6].innerHTML;
         var user="";
         if(amount < 10000){
     		user="David Lee";
     	}
     	else if ((amount > 10000) && (amount <= 50000)){
     		user="Laura Smith"
     	}
     	else if(amount > 50000)
     		user="Matthew Vance";
         
//        alert(user);
     	document.getElementById("Approved-By-Edit").value=user;
         
         document.getElementById("order_id-Edit").readOnly=true;
        
     }
   }
}




function myfunction(IsChecked)
{
	if(IsChecked == true)
		 document.getElementById("myBtn-edit").disabled=false;
	else
		 document.getElementById("myBtn-edit").disabled=true;
}





// APPROVE CODE


function OnChange()
{
	var amount=document.getElementById("Order_Amount-Edit").value;
	
	if(amount < 10000){
		document.getElementById("Approved-By-Edit").value="David Lee";
	}
	else if (amount > 10000 && amount <= 50000){
		document.getElementById("Approved-By-Edit").value="Laura Smith";
	}
	else if(amount > 50000)
		document.getElementById("Approved-By-Edit").value="Matthew Vance";
}


