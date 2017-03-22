var obj = {};
var endUser = {};
var id = {
	"id" : 123456
};
//var urlprefix = "http://localhost:9090/crmsoft/";
var urlprefix = constant.baseUrl;
function alertFunction(text, succOrErr, id) {
	if (succOrErr == "success") {
		$(id)
				.prepend(
						'<div id="apper-alert" style="position: fixed; top: 50%; z-index: 99;left: 50%;" class="alert alert-success">'
								+ ' <strong>' + text + '</div>');
	} else {
		$(id)
				.prepend(
						'<div id="apper-alert" style="position: fixed; top: 50%; z-index: 99;left: 50%;" class="alert alert-danger">'
								+ ' <strong>' + text + '</div>');
	}
	setTimeout(function() {
		$("#apper-alert").remove();
	}, 2000);
}

$(document).on("click", "#btn", function() {
	var app = $("#form1").find("[name]");
	for (var i = 0; i < app.length; i++) {
		var name = $(app[i]).attr("name");
		var value = $(app[i]).val();
		obj[name] = value;
		obj["endUser"] = id;
	}
	//console.log(obj);
	//myvalidation.validateEmail("");
	//console.log(myvalidation.validatephoneno("088 422299"));
	if (validationforms.validateformcompanyNew()) {
		//alert("hiii this is error");
		alertFunction("Please Fill Compulsory Field", 'danger', '#form1');

	} else {
		// alert("hiiii this is success");
		ajaxsender("contentdata/", "Post", function(dt) {
			console.log(dt);
			alertFunction("success", 'success', '#form1')

		}, function(dt) {
			// alert("error request");
			alertFunction("error", 'success', '#form1')
		}, obj);
	}
	return;
});

$("form").on("click", function(event) {
	event.preventDefault();
	//var value = $(this).serialize();

	/*  $.ajax({
		url : "http://localhost:9090/crmsoft/contentdata/update",
		method:"PATCH",
		contentType : "application/json",
		data : JSON.stringify(obj),
		dataType : "json",
		success : function(dt){
			console.log(dt);

		}				
	}); */

	//console.log(value);	
});
function ajaxsender(URL, Method, success, error, data) {
	if (Method == "get") {
		$.ajax({
			url : urlprefix + URL,
			method : Method,
			contentType : "application/json",
			//data : JSON.stringify(data),
			dataType : "json",
			success : success,
			error : error,
		});
	} else {

		$.ajax({
			url : urlprefix + URL,
			method : Method,
			contentType : "application/json",
			data : JSON.stringify(data),
			dataType : "json",
			success : success,
			error : error,
		});
	}
}
/* $(document).on("change", "input" , function(){
alert();

}); */