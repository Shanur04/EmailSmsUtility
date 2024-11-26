$(document).ready(function() {

	EncryptData = function(text, secret) {
		var encrypted = CryptoJS.AES.encrypt(text, secret);
		encrypted = encrypted.toString();
		return encrypted;
	}
	
	$("#seatAllocationLoginForm").submit(function(event) {
		event.preventDefault();

		if(!validateLoginInputs()){	
			return ;	
		}
		
		var key = "icgseatAllocationkey";

		var password = $('#password').val();
		password = EncryptData(password, key);
		$('#password').val(password);

		$(this).unbind('submit').submit();
	})
})

function validateLoginInputs() {

			var username = $("#username").val();
			var password = $("#password").val();

			if (username=== "" || username.trim() === "" || username === null) {
				$("#emailError").html("Email Id cannot be blank").css("color", "red");
				$([ document.documentElement, document.body ]).animate({scrollTop : $("#username").first().focus().offset().top-300}, 1000);
				return false;
			}
			if (/^[a-zA-Z0-9._]+@[a-zA-Z.]+[a-zA-Z]{2,6}$/.test(username) == false) {
				$("#emailError").html("Enter valid Email Id").css("color", "red");
				$([ document.documentElement, document.body ]).animate({scrollTop : $("#username").first().focus().offset().top-300}, 1000);
				return false;
			}
			if (password ==="" || password.trim() ==="" || password === null) {
				$("#passwordError").html("Password cannot be blank").css("color", "red");
				$([ document.documentElement, document.body ]).animate({scrollTop : $("#password").first().focus().offset().top-300}, 1000);
				return false;
			}
			return true;
	}

function clearErrorBoxes() {
	$("#emailError").empty();
	$("#passwordError").empty();
	$("#captchaError").empty();	
}
