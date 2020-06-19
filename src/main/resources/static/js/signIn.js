var signUsername = document.querySelector('#signupUsername')
var signupPassword =document.querySelector('#signupPassword')
var confirmPassword = document.querySelector('#confirmPassword')
var email = document.querySelector('#email')
var createAccBtn = document.querySelector('#createAccountBtn')
var signupInputs = document.getElementsByClassName("signupInput");
var divAppend = document.querySelector('#signupAppendToBtn');
createAccBtn.addEventListener("click", function(){verifySignUpComponents(),checkDuplicate()})

function checkDuplicate(){
	let user= {
		"username": signUsername.value,
		"password": signupPassword.value,
		"email": email.value
	}
	fetch('/checkDuplicationUsernameAndEmail',{
		method:"POST", headers:{
			"Content-Type": "application/json"
		},
		body: JSON.stringify(user)
	})
}
function verifySignUpComponents(){
	for(input of signupInputs){
		if (input.value == ""){
			input.style.backgroundColor="red"
			divAppend.innerHTML=("Please fill out sign up form completely")
		}else{
			input.style.backgroundColor="white"
		}
	}
	if(confirmPassword.value!==signupPassword.value){
		confirmPassword.style.backgroundColor="red"
		signupPassword.style.backgroundColor="red"
		divAppend.innerHTML=("Passwords must match")
	}
	
}
function verifyLoginComponents(){
	for(input of loginInputs){
		if (input.value == ""){
			input.style.backgroundColor="red"
			divAppend.innerHTML=("Please fill out login form completely")
		}else{
			input.style.backgroundColor="white"
		}
	}
}
function turnBackgroundWhite(target){
	target.style.backgroundColor="white"
}
