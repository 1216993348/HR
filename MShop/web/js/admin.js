function validateAid(){
	return validateEmpty("aid");
}
function validatePassword(){
	return validateEmpty("password");
}
function validateCode(){
	return validateRegex("code",/^[a-zA-Z0-9]{4}$/)
}
function validate(){
	return validateAid()&& validatePassword()&& validateCode();
}