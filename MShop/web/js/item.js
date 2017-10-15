function validateIid(){
	return validateEmpty("iid");
}
function validateItitle(iid){
    if(validateEmpty("item-" + iid)){
		document.getElementById("iid").value = iid;
		document.getElementById("ititle").value = document.getElementById("item-" + iid).value ;
		document.getElementById("updateForm").submit() ;
	}
}

function validateInsert(){
	return validateItitle();
}