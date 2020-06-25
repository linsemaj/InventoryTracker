var addStore = document.querySelector('#addStoreBtn')
var storeList = document.querySelector('#storeList')
var addOnDiv = document.querySelector('#addOnDiv')
var userId = document.querySelector('#userId')
var storeCounter= document.querySelector('#storeCounter')

if(userId.value===""){
	window.location = "/";
}

function newStore(){
	let para = document.createElement("button");
	let text = document.createTextNode("Store "+storeCounter++);
	para.appendChild(text);
	addOnDiv.appendChild(para);
	serverAddStore(para.childNodes[0].nodeValue)
}

function serverAddStore(text){
	fetch('/user/'+userId.value+'/addStore',{
		method:"Post"
		, headers:{"Content-Type":"application/json"},
		body: JSON.stringify(text)
	})
}