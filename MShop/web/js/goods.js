function validateGtitle(){
	return validateEmpty("gtitle");
}
function validateGprice(){
    return validateRegex("gprice",/^\d+(\.\d+)?$/);
}
function validateGamount() {
    return validateRegex("gamount",/^\d+$/);
}
function validateInsert(){
	return validateGtitle()&&validateGprice()&&validateGamount();
}