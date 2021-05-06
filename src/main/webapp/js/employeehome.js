window.onload = function() {
    getRequests();
}

 // import * as globalVariable  from '@/Project1-ERS/js/home.js'
  
 // import { lastname } from '@/Project1-ERS/js/home.js'

//document.getElementById("welcomeHeader").innerText=`Welcome ${globalVariable.firstname} ${globalVariable.lastname}`;



/*
 * Used to request the data for the table on the admin home page.
 */
function getRequests() {
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
			// JSON parse turns it into an object
            setValues(list);
            //console.log(list);
            
        }
        
            if (xhttp1.readyState == 4 && xhttp1.status == 200) {
           
               let empList = JSON.parse(xhttp1.responseText);
              // console.log(empList);    
               
               const unifiedValue = '1F31D';

               var emoji = parseInt(unifiedValue, 16);

               emoji = String.fromCodePoint(emoji);

               //console.log(emoji);
               document.getElementById("welcomeHeader").innerText=`Welcome ${empList.user_firstname} ${empList.user_lastname} ${emoji}`;
               
           }
        
        
        
    }
	// create a connection (method, url, boolean asynch or not)
    xhttp.open("GET", "/Project1-ERS/LoadExpensesById.json", true);
    xhttp.send();
    
      xhttp1.open("POST", "/Project1-ERS/getsessionvill.json", true);
    xhttp1.send();
    
}
/*
 * Used to set the values in the table (populate the table)
 */
function setValues(list) {
    
    let table = document.getElementById("reimbtable");
    table.innerHTML = `<tr>
                       <th scope="col">Ticket Id</th>
                       <th scope="col">Type</th>
                       <th scope="col">Amount</th>
                       <th scope="col">Date submitted</th>
                       <th scope="col">Date resolved</th>
                       <th scope="col">Status</th>
                       <th scope="col">Description</th>
                      
                       </tr>`
    
    for (r in list) {
        let trNewRow = document.createElement("tr");
        let tdType = document.createElement("td");
        let tdAmount = document.createElement("td");
        var tdSubmitDate = document.createElement("td");
        let tdResolveDate = document.createElement("td");
        let tdStatus = document.createElement("td");
        let tdDescription = document.createElement("td");
        let tdTicketId = document.createElement("td");
        let tdRecipts = document.createElement("td");  ///////////////////////////////////
        
        // Ticket Number
        tdTicketId.innerHTML = list[r].reimb_id;
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
        
        
      //  console.log(list[r].reimb_submitted);
        //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
       // Date myDate = df.parse(list[r].reimb_submitted);
       // console.log(df.format(myDate));
    
       
       
        
        tdSubmitDate.innerHTML = new Date(list[r].reimb_submitted);
        // Resolve Date
        
        if(list[r].reimb_resolved != null)
            tdResolveDate.innerHTML = new Date(list[r].reimb_resolved);
         else
            tdResolveDate.innerHTML = " ";
       // tdResolveDate.innerHTML = new Date(list[r].reimb_resolved);
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
 
        //add to the row
        trNewRow.appendChild(tdTicketId);
        trNewRow.appendChild(tdType);
        trNewRow.appendChild(tdAmount);
        trNewRow.appendChild(tdSubmitDate);
        trNewRow.appendChild(tdResolveDate);
        trNewRow.appendChild(tdStatus);
        trNewRow.appendChild(tdDescription);
        trNewRow.appendChild(tdRecipts); //////////////////////////////////////////////////
        

        //add row to the table
        table.appendChild(trNewRow);
    }
}

function myFunction(){
	
	//console.log("Inside myFun");
	var type, x;
	  type = document.getElementById("type").value;
	  
	  x = document.getElementById("amount").value;
	  try {
	    if(type == null) throw "Type is Empty";
	    if(x==null) throw "Amount is Empty";
	    
	  }
	  catch(err) {
	    message.innerHTML = "Input " + err;
	  }
}
