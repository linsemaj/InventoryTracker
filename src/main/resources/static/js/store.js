var userId = document.querySelector('#userId')
var storeId = document.querySelector('#storeId')
var editItem = document.querySelectorAll('.editItem')
var newItemContainer= document.querySelector("#newItemContainer")
var editItemContainer= document.querySelector("#editItemContainer")
var trs = document.querySelectorAll(".storeList")
var thNum = 1;

if(userId.value===""){
	window.location = "/";
}
thPopulate()

async function subtractQuantity(itemId){
	let itemQuantity = document.querySelector('#quantity'+itemId)
	itemQuantity.innerHTML = itemQuantity.innerHTML-1
	await fetch('/user/'+userId.value+'/store/'+storeId.value+'/item/'+itemId+'/change1',{
		method:"Post", headers:{
			"Content-Type": "application/json"
		}, body: JSON.stringify("-1")
	})
	window.location.href='/user/'+userId.value+'/store/'+storeId.value+'/items'	
}
async function addQuantity(itemId){
	let itemQuantity = document.querySelector('#quantity'+itemId)
	itemQuantity.innerHTML = parseInt(itemQuantity.innerHTML)+1
	await fetch('/user/'+userId.value+'/store/'+storeId.value+'/item/'+itemId+'/change1',{
		method:"Post", headers:{
			"Content-Type": "application/json"
		}, body: JSON.stringify("1")
	})
	window.location.href='/user/'+userId.value+'/store/'+storeId.value+'/items'	
}
function thPopulate() {
	let allNos = document.querySelectorAll('.thNo')
	let thColor = document.querySelectorAll('.thColor')
	let colorInput = document.querySelectorAll('.colorInput')
	for (var i = 0; i < allNos.length; i++) {
		allNos[i].innerText = thNum++
		thColor[i].style.backgroundColor= colorInput[i].value
	}
}
function openForm() {
	let newItemForm = document.querySelector('#newItemForm')
	editItemContainer.style.display = "none";	
	newItemForm.reset();
	newItemContainer.style.display = "block";
}

function closeForm() {
	newItemContainer.style.display = "none";
	editItemContainer.style.display = "none";
}

function isNumber(key) {
	var charCode = (key.key) ? key.which : key.key
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		alert("Only numeric input allowed for Quantities")
		key.preventDefault()
	}
	return true;
}
async function deleteItem(itemId){
	await fetch('/user/'+userId.value+'/store/'+storeId.value+'/items/'+itemId+'/delete', {
		method:"POST", headers:{
			"Content-Type": "application/json"
		},
		body: JSON.stringify(itemId)
	})
	window.location.href='/user/'+userId.value+'/store/'+storeId.value+'/items'	
}
function editTheItem(itemId){
	newItemContainer.style.display = "none";
	let itemNo = document.querySelector('#no'+itemId)
	let itemName = document.querySelector('#name'+itemId)
	let itemQuantity = document.querySelector('#quantity'+itemId)
	let itemLow = document.querySelector('#low'+itemId)
	let itemDescription = document.querySelector('#description'+itemId)
	let deleteBtn = document.querySelector('#editDeleteBtn')
	let editItemForm = document.querySelector('#editItemForm')
	deleteBtn.value=itemId
	editItemForm.action="/user/"+userId.value+"/store/"+storeId.value+"/items/"+itemId+"/edit"
	editItem[0].innerHTML= "No. "+itemNo.innerText+" &emsp;&emsp;&emsp; Name: "+itemName.innerText//h1
	editItem[1].value= itemName.innerText			
	editItem[2].value= itemQuantity.innerText
	editItem[3].value= itemLow.innerText
	editItem[4].value= itemDescription.innerText
	editItemContainer.style.display = "block"
	window.scrollBy(0, document.body.offsetHeight - window.innerHeight);
}
