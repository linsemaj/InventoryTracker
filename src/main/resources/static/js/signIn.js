var signUsername = document.querySelector('#signupUsername')
var signupPassword =document.querySelector('#signupPassword')
var confirmPassword = document.querySelector('#confirmPassword')
var email = document.querySelector('#email')
var loginUsername = document.querySelector('#loginUsername')
var loginPassword =document.querySelector('#loginPassword')
var createAccBtn = document.querySelector('#createAccountBtn')
var loginAccBtn = document.querySelector('#loginBtn')
var signupInputs = document.getElementsByClassName("signupInput");
var loginInputs = document.getElementsByClassName("loginInput");
var signupWarning= document.querySelector('#signupAppendToBtn');
var loginWarning = document.querySelector('#loginAppendToBtn');
var signupForm = document.querySelector('#signupForm');
var loginForm = document.querySelector('#loginForm');


function allowUserSignup(){
	let user = {
			"username": signUsername.value,
			"password": signupPassword.value,
			"email": email.value
	}
	if(signupNotEmpty()===true){
		fetch('/user/signup',{method:"POST",headers:{
			"Content-Type": "application/json"}, body:JSON.stringify(user)})
			.then((responseObject) => responseObject.text())
			.then((data)=>{
				if(data=== "Signed up"){
					signupForm.submit()
					return true
				}
				signupWarning.innerHTML= data + ' is already taken/registered, please enter something else'
				return false
			})
	}
	return false
}
function allowUserLogin(){
	let user = {
			"username": loginUsername.value,
			"password": loginPassword.value,
	}
	if(loginNotEmpty()===true){
		fetch('/user/login',{method:"POST",headers:{
			"Content-Type": "application/json"}, body:JSON.stringify(user)})
			.then((responseObject) => responseObject.text())
			.then((data)=>{
				if(data=== "true"){
					loginForm.submit()
					return true;
				}
				loginWarning.innerHTML= 'Incorrect username password combination'
				return false
			})
	}
	return false;
}

function signupNotEmpty(){
	for(input of signupInputs){
		if (input.value === ""){
			input.style.backgroundColor="red"
			signupWarning.innerHTML=("Please fill out sign up form completely")
		}else{
			input.style.backgroundColor="white"
		}
	}
	if(confirmPassword.value!==signupPassword.value){
		confirmPassword.style.backgroundColor="red"
		signupPassword.style.backgroundColor="red"
		signupWarning.innerHTML=("Passwords must match")
	}
	if(confirmPassword.value===signupPassword.value && signupPassword.value!== "" && signupUsername.value!== "" && confirmPassword.value!=="" && email.value!==""){
		return true
	}
	return false;
}
function loginNotEmpty(){
	for(input of loginInputs){
		if (input.value === ""){
			input.style.backgroundColor="red"
			loginWarning.innerHTML=("Please fill out login form completely")
		}else{
			input.style.backgroundColor="white"
		}
	}
	if(loginPassword.value!== "" && loginUsername.value!== ""){
		return true
	}
	return false
}

