var myvalidation = {
	validate : function(myObject) {
		console.log(myObject);
	},
	validateEmail : function(emailAddress) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var something = {};
		if (re.test(emailAddress)) {
			something["expression"] = false;
			something["message"] = "its a valid email address";
			//alert("1---"+something);
			return something;
		}
		something["expression"] = true;
		something["message"] = "its a invalid email address";
		//alert("2---"+something);
		return something;

	},
	validatephoneno : function(number) {
		var phoneno = /^([0-9]{8,10})$/;
		var something = {};
		//number = number.replace(/\s/g, "");
		if (phoneno.test(number)) {
			something["expression"] = true;
			something["message"] = "its a valid phonenumber";
			//alert("1---"+something);
			return something;
		}
		something["expression"] = false;
		something["message"] = "its a invalid phonenumber";
		//alert("2---"+something);
		return something;

	},
	validateNumber : function(number) {
		var identity = /^([0-9]{1,})$/;
		//number = number.replace(/\s/g, "");
		var something = {};
		if (identity.test(number)) {
			something["expression"] = true;
			something["message"] = "its a valid number";
			//alert("1---"+something);
			return something;
		}
		something["expression"] = false;
		something["message"] = "its a invalid number";
		//alert("2---"+something);
		return something;

	},
	validateBlank : function(value) {
		if (value && value != "") {
		    return false;
		} else {
			return true;
		}
	},
	updateView : function(ref) {
			$(ref).html("");		
	},

}