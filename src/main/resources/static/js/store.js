var addOnTable = document.querySelector('#addOnTable')
var newItemBtn = document.querySelector('#newItemBtn')
var userId = document.querySelector('#userId')
var storeId = document.querySelector('#storeId')
var allNos = document.querySelectorAll('.thNo')
var newItemForm = document.querySelector('#newItemForm')
var editItem = document.querySelectorAll('.editItem')
var newItemContainer= document.querySelector("#newItemContainer")
var editItemContainer= document.querySelector("#editItemContainer")
var deleteBtn = document.querySelector("#editDeleteBtn")
var trs = document.querySelectorAll(".storeList")
var thNum = 1;

thPopulate()

function thPopulate() {
	for (var i = 0; i < allNos.length; i++) {
		allNos[i].innerText = thNum++
	}
}
function openForm() {
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
 function deleteItem(itemId){
	fetch('/user/'+userId.value+'/store/'+storeId.value+'/items/'+itemId+'/delete', {
		method:"POST", headers:{
			"Content-Type": "application/json"
		},
		body: JSON.stringify(itemId)
	})
}
function editTheItem(itemId){
	newItemContainer.style.display = "none";
	let itemNo = document.querySelector('#no'+itemId)
	let itemName = document.querySelector('#name'+itemId)
	let itemQuantity = document.querySelector('#quantity'+itemId)
	let itemLow = document.querySelector('#low'+itemId)
	let itemDescription = document.querySelector('#description'+itemId)
	deleteBtn.value=itemId
	editItem[0].innerHTML= "No. "+itemNo.innerText+" &emsp;&emsp;&emsp; Name: "+itemName.innerText//h1
	editItem[1].value= itemName.innerText			
	editItem[2].value= itemQuantity.innerText
	editItem[3].value= itemLow.innerText
	editItem[4].value= itemDescription.innerText
	editItemContainer.style.display = "block"
	window.scrollBy(0, document.body.offsetHeight - window.innerHeight);
}
