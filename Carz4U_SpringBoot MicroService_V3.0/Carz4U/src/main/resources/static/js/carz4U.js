/**
 * This function is used to perform authentication and JWT generation 
 * by sending Username and Password provided in loginPage.jsp to 
 * the "/authenticate" URI in JwtAuthenticationController. 
 * Username and Password are converted into JSON object and are sent as 
 * @RequestBody to "/authenticate" URI. 
 * 
 *  Once the JWT is received from server (verified using 
 *  this.readyState == this.HEADERS_RECEIVED), JWT is sent
 *  to "/welcome" as Request Header, with the Header name "Authorization"*/
var xhr = new XMLHttpRequest();
var jwtToken;
function login(){
	var form = document.getElementById("loginForm");
	var username = form.elements[0].value;
	var password = form.elements[1].value;
	var url = "http://localhost:8080/authenticate";
	var params = {"userName": username, "password": password};
	
	   xhr.open('POST', url);
	   xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	   alert('userName: ' + username + ' ,password: ' + password);
	   xhr.send(JSON.stringify(params));
	   xhr.onreadystatechange = function() {
			  if(this.readyState == this.HEADERS_RECEIVED) {
			    jwtToken = xhr.getResponseHeader("Authorization");
			    var url = "http://localhost:8080/welcome";
			    xhr.open('POST', url, false);
			    xhr.setRequestHeader("Authorization", jwtToken);
			    //form.action="welcome";
				alert('jwtToken: ' + jwtToken);
				xhr.send();
				//xhr.onreadystatechange = function() {
						form.action="welcome";
						form.submit();
					//}
				}
			  }
		}

/*function getAction(form){
	alert('getAction()')
	var url = "http://localhost:8080/welcome";
    xhr.open('GET', url);
	xhr.setRequestHeader("Authorization", jwtToken);
	alert('jwtToken: ' + jwtToken);
	xhr.send();
	form.action="welcome";
} */

/*function getAction(form){
	form.action="welcome";
	form.submit();
}*/
