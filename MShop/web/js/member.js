function validateRegister(){
    return validateMid() && validatePassword();
}
function validateMid(){
    return validateEmpty("mid");
}
function validatePassword() {
    return validateEmpty("password");
}
function validateCode() {
    return validateEmpty("code");
}
function validateLogin() {
    return validateMid() && validatePassword() && validateCode();
}
function validateMaddress(){
    return validateEmpty("maddress");
}
function validateMphone(){
    return validateEmpty("mphone");
}
function validateMname(){
    return validateEmpty("mname");
}
function validateUpdate(){
    return validateMname() && validateMphone() && validateMaddress();
}
