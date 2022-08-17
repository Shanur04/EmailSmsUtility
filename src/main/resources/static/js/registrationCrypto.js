$(document).ready(function() {

	EncryptData = function(text, secret) {
		var encrypted = CryptoJS.AES.encrypt(text, secret);
		encrypted = encrypted.toString();
		return encrypted;
	}
	
	$("#registrationForm").submit(function(event) {
		event.preventDefault();
		
		var key = "icgseatAllocationkey";

		var password = $('#password').val();
		password = EncryptData(password, key);
		$('#password').val(password);

		$(this).unbind('submit').submit();
	})
})

function clearErrorBoxes() {
	$("#emailError").empty();
	$("#passwordError").empty();
	$("#captchaError").empty();	
}
