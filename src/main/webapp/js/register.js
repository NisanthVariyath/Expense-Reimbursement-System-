     
window.onload = function(){
	getSessVill();
}


function getSessVill() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			let vill = JSON.parse(xhttp.responseText);
			//console.log(vill);
			//console.log("-----------------------");
			//console.log(vill.user_firstname);
			//globalVariable.firstname = vill.user_firstname;
			//globalVariable.lastname = vill.user_lastname;
			//globalVariable.firstname = vill.user_firstname;
			//globalVariable.lastname = vill.user_lastname;
			
			
			//window.x= vill.user_firstname;
			//window.y= vill.user_lastname;
			//document.getElementById("welcomeHeader").innerText=`Welcome ${vill.user_firstname} ${vill.user_lastname}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/Project1-ERS/RegisterERS.json");

	xhttp.send();
}