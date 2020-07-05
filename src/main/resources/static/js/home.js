var addStore = document.querySelector('#addStoreBtn')
var addOnTable = document.querySelector('#addOnDiv')
var userId = document.querySelector('#userId')
var storeCounter= document.querySelector('#storeCounter')
var initalStoreCounter = storeCounter
var storeId
var tableNum= document.querySelectorAll('.tableNum')
var i=0;
if(userId.value===""){
	window.location = "/";
}
populateTableNum()

function populateTableNum(){
	for(; i<initalStoreCounter.value; i++){
		tableNum[i].innerText= i+1
	}
}
function newStore(){
	let text = document.createTextNode('Store '+(++storeCounter.value))
	let a= serverAddStore(text.data)
	.then((response)=>{
		let anchor = document.createElement("a")
		let thNo = document.createElement("th")
		let thNoValue = document.createTextNode(++i)
		let thName = document.createElement("th")
		let thStatus = document.createElement("th")
		let tr = document.createElement("tr")
		let btn = document.createElement("button")
		thNo.appendChild(thNoValue)
		btn.appendChild(text)
		thNo.setAttribute("class","tableNum")
		anchor.appendChild(btn)
		anchor.setAttribute("href", '/user/'+userId.value+'/store/'+storeId+'/items')
		thName.appendChild(anchor)
		addOnTable.appendChild(tr)
		tr.setAttribute("class", "storeList")
		tr.appendChild(thNo)
		tr.appendChild(thName)
		tr.appendChild(thStatus)
	})
}


async function serverAddStore(text){
	await Promise.resolve(
		fetch('/user/'+userId.value+'/addStore',{
			method:"Post",
			headers:{
				"Content-Type":"application/json"
			},
			body: text
		})
		.then((responseObject)=> responseObject.json())
		.then((data)=> {
			storeId = data
			return storeId
		})
	)
}