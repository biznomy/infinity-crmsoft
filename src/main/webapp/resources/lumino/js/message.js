var obj = {};
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
	var app = $("#form9").find('[name]');
	for (var i = 0; i < app.length; i++) {
		var name = $(app[i]).attr("name");
		var value = $(app[i]).val();
		obj[name] = value;
		obj["endUser"] = id;
	}
	console.log(obj);
	if (validationforms.validateformMessage()) {
		// alert("hiii this is error");
		alertFunction("Please Fill Compulsory Field", 'danger', '#result');

	} else {
		// alert("hiiii this is success");
		ajaxsender("contentdata/", "Post", function(dt) {
			console.log(dt);
			alertFunction("success", 'success', '#result')

		}, function(dt) {
			// alert("error request");
			alertFunction("error", 'success', '#result')
		}, obj);
	}
});
function ajaxsender(URL, Method, success, error, data) {
	if (Method == "get") {
		$.ajax({
			url : urlprefix + URL,
			method : Method,
			contentType : "application/json",
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

$("form").on("submit", function(event) {
	event.preventDefault();
	/*
	 * var value = $(this).serialize(); console.log(value); $.ajax({ url :
	 * "http://localhost:9090/crmsoft/contentdata", method:"post", data : value,
	 * success : function(dt){ console.log(dt); } });
	 */

});

function close_window() {
	window.open('', '_parent', '');
	window.close();
}

$(".js-data-example-ajax")
		.select2(
				{
					ajax : {
						//url : 'http://localhost:9090/crmsoft/contentdata?',
						url : constant.baseUrl + constant.contentData + "?",
						dataType : 'json',
						data : function(params) {
							return {
								jsq : '{"query":{"edContentData":{"filter":{"title":{"like":"'
										+ params.term
										+ '"},"type":{"equals":"company"}}}},"page":{"start":1,"max":10}}', // search
																											// term

							};
						},
						processResults : function(data) {
							// data.results
							var arrayobj = [];
							for ( var mydata in data.results) {
								var obj = {};
								obj['id'] = data.results[mydata].id;
								obj['text'] = data.results[mydata].id + " "
										+ data.results[mydata].title;
								// obj['text'] = data.results[mydata].title+"
								// id="+data.results[mydata].id;
								arrayobj.push(obj);

							}
							$('#selector1').on(
									"select2:select",
									function(e) {
										var selectedVal = $('#selector1').find(
												":selected").val();
										$("#cId").val(selectedVal);
									})
							return {
								results : arrayobj
							};

						}
					}

				});

/*
 * function close_window() { if (confirm("Close Window?")) { close(); } }
 */