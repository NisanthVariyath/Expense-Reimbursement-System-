/**
 * 
 */


 window.onload = function() {
	 
    getRequests();
}


//document.getElementById("welcomeHeader").innerText=`Welcome ${globalVariable.firstname} ${globalVariable.lastname}`;


/*
 * Used to request the data for the table on the admin home page.
 */
function getRequests() {
	
	//console.log("INSIDE ADMIN JS");
	// this object allows us to make requests
	// and get data back
    let xhttp = new XMLHttpRequest();
    let xhttp1 = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
				/*
				 * The ready state property holds the status of the
				 * XMLHttpRequest
				 * 
				 * 0 - request not initialized 1 - server connection established
				 * 2 - request received 3 - processing request 4 - request is
				 * finished and response is ready
				 * 
				 * We want state of 4 and a status of 200 200 - OK
				 */
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let list = JSON.parse(xhttp.responseText);
            //let empList = JSON.parse(xhttp.responseText);
			// JSON parse turns it into an object
            setValues(list);
            //SetValues(empList);
           // console.log(list);
           //  console.log(empList);    /////////////////////////////////////////////////////////            
        }
           if (xhttp1.readyState == 4 && xhttp1.status == 200) {
           
               let empList = JSON.parse(xhttp1.responseText);
              // console.log(empList);    
               
               const unifiedValue = '1F31D';

               var emoji = parseInt(unifiedValue, 16);

               emoji = String.fromCodePoint(emoji);
               
               document.getElementById("welcomeHeader").innerText=`Welcome ${empList.user_firstname} ${empList.user_lastname} ${emoji}`;
               
           }
           
           
        
    }
	// create a connection (method, url, boolean asynch or not)
	
    xhttp.open("GET", "/Project1-ERS/loadExpense.json", true);
    xhttp.setRequestHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    xhttp.send();
    
    xhttp1.open("POST", "/Project1-ERS/getsessionvill.json", true);
    xhttp1.setRequestHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    xhttp1.send();
}


/*
 * Used to set the values in the table (populate the table)
 */
function setValues(list) {
    
    let table = document.getElementById("reimbtable");
    table.innerHTML = `<tr>
                       <th>User Id</th>
                       <th>First Name</th>
                       <th>Last Name</th>
                       <th>Type</th>
                       <th>Amount</th>
                       <th>Date submitted</th>
                       <th>Date resolved</th>
                       <th>
                        
                        	<form method="POST" action="/Project1-ERS/ShowExpenseBYStatus.change" >
	<div style="width: 100%; height: 60px;">
	     <div class="dropdown">
				<select class="btn-lg class="ui dropdown" id="type" name="reimb_type" onchange="method100()" form="TypeForm" >
					<option value="" selected disabled>Select Status</option>
					<option value="1">Pending</option>
					<option value="2">Approved</option>
					<option value="3">Denied</option>
					<option value="4">All</option>
					</select>
		    </div>
			</form>
                       
                       
                       
                       </th>
                       <th>Description</th>
                       <th>Ticket Id</th>
                       <th></th>
                       </tr>`
    
    for (r in list) {
        let trNewRow = document.createElement("tr");
        // trNewRow.className = "table-success";
        
        let tdUser = document.createElement("td");
         //tdUser.className = "table-success";
         
        let tdFirstName = document.createElement("td");
        let tdLastName = document.createElement("td");
        let tdType = document.createElement("td");
        let tdAmount = document.createElement("td");
        let tdSubmitDate = document.createElement("td");
        let tdResolveDate = document.createElement("td");
        let tdStatus = document.createElement("td");
        let tdDescription = document.createElement("td");
        let tdTicketId = document.createElement("td");
        let tdButtons = document.createElement("td");
        
        // Author Name
        
         //  console.log(list[r].reimb_author);
        tdUser.innerHTML = list[r].reimb_author;
        
        tdFirstName.innerHTML = list[r].firstname;
        tdLastName.innerHTML = list[r].lastname;
        // Type
        switch (list[r].reimb_type_id) {
        case 1:
        	tdType.innerHTML = "Lodging";
            break;
        case 2:
        	tdType.innerHTML = "Travel";
            break;
        case 3:
        	tdType.innerHTML = "Food";
            break;
        case 4:
        	tdType.innerHTML = "Other";
            break;
        }
        // Amount
        tdAmount.innerHTML = "$" + list[r].reimb_amount;
        // Submit Date
        tdSubmitDate.innerHTML = new Date(list[r].reimb_submitted);
        // Resolve Date
        //console.log(list[r].reimb_resolved);
        if(list[r].reimb_resolved != null)
            tdResolveDate.innerHTML = new Date(list[r].reimb_resolved);
         else
            tdResolveDate.innerHTML = " ";
        // Status
        switch (list[r].reimb_status_id) {
        case 2:
        	tdStatus.innerHTML = "<span style='color:#00FF00'> Approved </span>";
            break;
        case 3:
        	tdStatus.innerHTML = "<span style='color:#FF0000'> Denied </span>";
            break;
        case 1:
        	tdStatus.innerHTML = "Pending";
            break;
        }
        // Description
        
     
        
        
        tdDescription.innerHTML = list[r].reimb_description;
        // Ticket Number
        tdTicketId.innerHTML = list[r].reimb_id;
        
        // Buttons
        if (list[r].reimb_status_id == 1) {
            btnApprove = document.createElement("button");
            btnApprove.setAttribute("name", "btnApprove");
            btnApprove.setAttribute("value", list[r].reimb_id);
            btnApprove.style.width = "100px";
            btnApprove.style.background = "green";
            btnApprove.style.color = "white";
            btnApprove.style.fontSize = "16px";
            btnApprove.innerText = "APPROVE";
            btnApprove.addEventListener('click', approveExpense);

            btnDeny = document.createElement("button");
            btnDeny.setAttribute("name", "btnDeny");
            btnDeny.setAttribute("value", list[r].reimb_id);
            btnDeny.style.width = "100px";
            btnDeny.style.background = "red";
            btnDeny.style.color = "white";
            btnDeny.style.fontSize = "16px";
            btnDeny.innerText = "DENY";
            btnDeny.addEventListener('click', denyExpense);
            
            tdButtons.appendChild(btnApprove);
            tdButtons.appendChild(btnDeny);
        }
        
        trNewRow.appendChild(tdUser);
        trNewRow.appendChild(tdFirstName);
        trNewRow.appendChild(tdLastName);
        trNewRow.appendChild(tdType);
        trNewRow.appendChild(tdAmount);
        trNewRow.appendChild(tdSubmitDate);
        trNewRow.appendChild(tdResolveDate);
        trNewRow.appendChild(tdStatus);
        trNewRow.appendChild(tdDescription);
        trNewRow.appendChild(tdTicketId);
        trNewRow.appendChild(tdButtons);
       
        table.appendChild(trNewRow);
    }
}

function approveExpense(e) {
    let target = e.target;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            getRequests();
        }
    }
    xhttp.open("POST", "/Project1-ERS/ApproveExpense.change", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("value=" + e.target.value);
}

function denyExpense(e) {
    let target = e.target;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            getRequests();
        }
    }
	xhttp.open("POST", "/Project1-ERS/DenyExpense.change", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("value=" + e.target.value);
}




function method100(){
	
    // let target = e.target;
 	var selected = document.getElementById("type").value;
 	//console.log(selected);
 	var reimb_type= selected;
 	
 	 let xhttp2 = new XMLHttpRequest();
 	    let xhttp3= new XMLHttpRequest();
 	    xhttp2.onreadystatechange = function() {
 					/*
 					 * The ready state property holds the status of the
 					 * XMLHttpRequest
 					 * 
 					 * 0 - request not initialized 1 - server connection established
 					 * 2 - request received 3 - processing request 4 - request is
 					 * finished and response is ready
 					 * 
 					 * We want state of 4 and a status of 200 200 - OK
 					 */
 	        if (xhttp2.readyState == 4 && xhttp2.status == 200) {
 	            let list = JSON.parse(xhttp2.responseText);
 	            //let empList = JSON.parse(xhttp.responseText);
 				// JSON parse turns it into an object
 	            
 	              setValues(list);
 	            //SetValues(empList);
 	          //  console.log(list);
 	          //  list1=list;
 	           //  console.log(empList);    /////////////////////////////////////////////////////////            
 	        }
 	           if (xhttp3.readyState == 4 && xhttp3.status == 200) {
 	           
 	               let empList = JSON.parse(xhttp3.responseText);
 	              // console.log(empList);    
 	               
 	               const unifiedValue = '1F31D';

 	               var emoji = parseInt(unifiedValue, 16);

 	               emoji = String.fromCodePoint(emoji);
 	               
 	               document.getElementById("welcomeHeader").innerText=`Welcome ${empList.user_firstname} ${empList.user_lastname} ${emoji}`;
 	               
 	           }
 	           
 	           
 	        
 	    }
 		// create a connection (method, url, boolean asynch or not)
 		
 	    //xhttp2.open("GET", "/Project1-ERS/ShowByStatus.json", true);
 	   xhttp2.open("GET",`http://localhost:8080/Project1-ERS/ShowByStatus.json?reimb_type=${reimb_type}`);
 	  // xhttp2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 	   xhttp2.setRequestHeader("Cache-Control", "no-cache, no-store, must-revalidate");
 	    xhttp2.send();
 	   //`http://localhost:8081/ReimursementSystem/getReimbursementPendingStates?statusid=2&username=${reimb_type}`);
 	    //xhttp2.send(JSON.stringify(reimb_type));
 	   //xhttp2.send();
 	    
 	    xhttp3.open("POST", "/Project1-ERS/getsessionvill.json", true);
 	   xhttp3.setRequestHeader("Cache-Control", "no-cache, no-store, must-revalidate");
 	    xhttp3.send();
 	    
 	    
 	    
 	    
 	    
 	
 	
 	
 	/*
 	switch(selected){
 	case 1:
 	//	location.reload(true);
 		 //setValues(list1);
      break;
 	case 2:
 		location.reload();
 		// setValues(list1);
 		break;
 	case 3:
 		location.reload();
 		// setValues(list1);
 		break;
 		
 		default:
 	 
 			
 	}
 		*/	

}







