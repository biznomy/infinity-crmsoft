var mymessage = {};
var mymessage1 = {};
var Parameters = {};
var emailsettings = {};
var camOrCom = "";
var refenceId = "";
var dataContent;

//var urlprefix = "http://localhost:9090/crmsoft/";
var urlprefix = constant.baseUrl + constant.contentData;
var mypage = {

	deleteL : function(event) {
		$(event).parent().parent().remove();
	}

};
function hashparameter(hash) {
	var dfd = {};
	var fg = (hash.slice(1)).split("&");
	for (var i = 0; i < fg.length; i++) {
		//dfd[fg[i].split("=")[0]]=fg[i].split("=")[1]; 
		var sa = fg[i].split("=");
		dfd[sa[0]] = sa[1];
		console.log(sa);
	}

	return dfd;
}

function ademail() {
	$('.form_datetime').datetimepicker({
		//language:  'fr',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1
	});

}
/* alert */
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
/* alertclose */
/* email */
function adfe(dfg) {
	if (dfg === undefined || dfg === null || dfg === "") {
		var dfg = $("#mySelect option:selected").val();
	}
	mymessage["type"] = dfg;
	mymessage1["type"] = dfg;
	if (dfg == "0") {
		$('#mymess1').hide();
		$('#mymess').show();
	} else if (dfg == "1") {
		$('#mymess').hide();
		$('#mymess1').show();
	} else if (dfg == "2") {
		$('#mymess').hide();
		$('#mymess1').hide();
	}
}

$(document)
		.ready(
				function() {

					$("#addbtn")
							.click(
									function() {
										$("#add455")
												.append(
														'<div class="form-group row">'
																+ '<div class="col-lg-5"><input class="form-control" name="key" placeholder="key" /></div>'
																+ '<div class="col-lg-5"><input class="form-control" name="value" placeholder="value"/></div>'
																+ '<div class="col-lg-2"><button class="btn btn-danger" onclick="mypage.deleteL(this)">DEL</button></div></div>');
									});
				});

$(document)
		.ready(
				function() {
					$("#addbtn123")
							.click(
									function() {
										$("#add123")
												.append(
														'<div class="form-group row">'
																+ '<div class="col-lg-5"><input class="form-control" name="key" placeholder="key"/></div>'
																+ '<div class="col-lg-5"><input class="form-control" name="value" placeholder="value"/></div>'
																+ '<div class="col-lg-2"><button class="btn btn-danger" onclick="mypage.deleteL(this)">DEL</button></div></div>');
									});
				});

$(document).ready(function() {
	$('#now').click(function() {
		$('#datapicker').hide();
	});
	$('#pick').click(function() {
		$('#datapicker').show();
	});
});

$("#sbtn")
		.on(
				"click",
				function(event) {
					mymessage = {};
					Parameters = {};
					mymessage["companyId"] = refenceId;
					var dfg = $('#mess').find("input");
					for (var i = 0; i < dfg.length; i++) {
						if ($(dfg[i]).attr("name") != "key"
								&& $(dfg[i]).attr("name") != "isScheduled"
								&& $(dfg[i]).attr("name") != undefined
								&& $(dfg[i]).attr("name") != null
								&& $(dfg[i]).attr("name") != " "
								&& $(dfg[i]).val() != "") {
							mymessage[$(dfg[i]).attr("name")] = $(dfg[i]).val();
						}
					}
					var app = $("#add455").find(".form-group.row");
					for (var q = 0; q <= app.length; q++) {
						if ($(app[q]).find("[name='key']").val() != undefined
								&& $(app[q]).find("[name='key']").val() !== null) {
							Parameters[$(app[q]).find("[name='key']").val()] = $(
									app[q]).find("[name='value']").val();
						} else {
						}
					}
					console.log(Parameters)
					mymessage["parameters"] = JSON.stringify(Parameters);
					;
					mymessage["isScheduled"] = $(
							'input[name="isScheduled"]:checked', '#myradio')
							.val();
					mymessage["content"] = $("#mess [name='content']").val();
					//	mymessage["content"] = $("#mess").find("[name='content']").val();
					console.log(mymessage);
					/* $("#typ").append('<div class="alert alert-success">'+
					 ' <strong>Success!</strong> Indicates a successful or positive action </div>');
					alert("success") */
					if (validationforms.validateformemail()) {
						// alert("hiii this is error");
						alertFunction("Please Fill Compulsory Field", 'danger',
								'#mess');

					} else {
						// alert("hiiii this is success");
						ajaxSender("manager/email", "Post", function(dt) {
							console.log(dt);
							alertFunction("success", 'success', '#mess')

						}, function(dt) {
							// alert("error request");
							alertFunction("error", 'success', '#mess')
						}, mymessage);
					}
					return;
				});

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

function chooseTemplate(e) {
	$("#mymess [name='content']").text("");
	$("#myModal").toggle();
	$("#addbtn").hide();
	$("#add455").empty();

	//$("body").removeClass("modal-open");
	var string = "";
	var dfg = $(e).children();
	for (var i = 0; i < dfg.length; i++) {
		string = string + dfg[i].outerHTML;
	}
	$("#mymess [name='content']").text(string);
	//var re = /(?:^|\W)body(\w+)(?!\w)/g, match, matches = [];
	var assd = /bizbody.[\w\S]+/ig, match, matches = [];
	while (match = assd.exec(string)) {
		matches.push(match[0]);
		$("#add455")
				.append(
						'<div class="form-group row">'
								+ '<div class="col-lg-5"><input  class="form-control" value="'
								+ match[0]
								+ '" name="key"/></div>'
								+ '<div class="col-lg-5"><input class="form-control" name="value" placeholder="value"/></div>'
								+ '</div>');

	}
	$("[data-dismiss='modal']").click();
}

$(document).on(
		"click",
		"#bzn",
		function() {
			$("#myModal .modal-body").empty();
			$.ajax({
				//url : 'http://localhost:9090/crmsoft/manager/template',
				url : constant.baseUrl + '/manager/template',
				method : "GET",
				contentType : "application/json",
				success : function(dt) {
					$("#hfu").children().remove();
					for (var i = 0; i < dt.result.template.length; i++) {
						$("#myModal .modal-body").append(
								"<div id='appendedTemplate' onclick='chooseTemplate(this)'>"
										+ dt.result.template[i] + "</div>");
						console.log(dt.result.template[i]);
					}
					console.log(dt);
				},
				error : function(dt) {
					console.log(dt);
				}
			});
		});

/*email setting post */

$("#ebzn").on("click", function(event) {
	emailsettings["host"] = $("#idemail [name='host']").val();
	emailsettings["port"] = $("#idemail [name='port']").val();
	emailsettings["userName"] = $("#idemail [name='userName']").val();
	emailsettings["password"] = $("#idemail [name='password']").val();
	console.log(emailsettings);
	$.ajax({
		url : 'http://localhost:9090/crmsoft/manager/setting',
		method : "post",
		contentType : "application/json",
		data : JSON.stringify(emailsettings),
		dataType : "json",
		success : function(dt) {
			console.log(dt);
		},
		error : function(dt) {
			console.log(dt);
		}
	});
})

Array.prototype.remove = function(x) {
	var i;
	for (i in this) {
		if (this[i].toString() == x.toString()) {
			this.splice(i, 1)
		}
	}
}

/*sms*/
$("#sbtn123")
		.on(
				"click",
				function(event) {
					mymessage = {};
					Parameters = {};
					mymessage["companyId"] = refenceId;
					var dfg = $('#mess1').find("input");
					for (var i = 0; i < dfg.length; i++) {
						if ($(dfg[i]).attr("name") != "key"
								&& $(dfg[i]).attr("name") != "value"
								&& $(dfg[i]).attr("name") != "isScheduled"
								&& $(dfg[i]).attr("name") != undefined
								&& $(dfg[i]).attr("name") != null
								&& $(dfg[i]).attr("name") != " "
								&& $(dfg[i]).val() != "") {
							mymessage[$(dfg[i]).attr("name")] = $(dfg[i]).val();
						}
					}
					console.log(mymessage);
					var app = $("#add123").find(".form-group.row");
					for (var q = 0; q <= app.length; q++) {
						if ($(app[q]).find("[name='key']").val() != undefined
								&& $(app[q]).find("[name='key']").val() !== null
								&& $(app[q]).find("[name='key']").val() !== "") {
							Parameters[$(app[q]).find("[name='key']").val()] = $(
									app[q]).find("[name='value']").val();
						}
					}
					console.log(mymessage);
					console.log(Parameters);

					mymessage["parameters"] = (Object.keys(Parameters).length > 0) ? JSON
							.stringify(Parameters)
							: "";
					;
					mymessage["isScheduled"] = $(
							'input[name="isScheduled"]:checked', '#myradio1')
							.val();
					mymessage["content"] = $("#mess1 [name='content']").val();
					//	mymessage["content"] = $("#mess").find("[name='content']").val();
					console.log(mymessage);

					if (camOrCom == "company") {
						ajaxSender("manager/sms", "Post", function() {
							alertFunction("successfully  Sending", "success",
									"#mess1");
						},
								function() {
									alertFunction("Error in Sending", "error",
											"#mess1");
								}, mymessage)
					} else {
						mymessage["dataContent"] = JSON.stringify(dataContent);
						ajaxSender("manager/bulk", "Post", function() {
							alertFunction("successfully  Sending", "success",
									"#mess1");
						},
								function() {
									alertFunction("Error in Sending", "error",
											"#mess1");
								}, mymessage)
					}

					if (validationforms.validateformsms()) {
						// alert("hiii this is error");
						alertFunction("Please Fill Compulsory Field", 'danger',
								'#mess1');

					} else {
						// alert("hiiii this is success");
						ajaxSender("manager/sms", "Post", function(dt) {
							console.log(dt);
							alertFunction("success", 'success', '#mess1')

						}, function(dt) {
							// alert("error request");
							alertFunction("error", 'success', '#mess1')
						}, mymessage);
					}
					return;

				});

$(document).ready(function() {
	$('#now1').click(function() {
		$('#datapicker1').hide();
	});
	$('#pick1').click(function() {
		$('#datapicker1').show();
	});
});

$("form").on("click", function(event) {
	event.preventDefault();
	var value = $(this).serialize();

	/* $.ajax({
		url : "http://localhost:9090/crmsoft/contentdata/update",
		method:"post",
		data : value,
		success : function(dt){
			console.log(dt);
		}				
	}); */

	console.log(value);
});
function checkData(data) {
	if (data != undefined && data != null && data != "") {
		return true;
	} else {
		return false;
	}
}
var sendAll = false;
var OnlyOne = false;
/*   function autosender(elm){
 var val = $(elm).attr("name");
 if(val=="all"){

 }
 $('#myModal').modal('hide');

 } */
function popNumberOpen(data, mode, single) {
	console.log(data);
	console.log(single);
	$('#myModal .model-body').children().remove();
	var dfd = '<label  name="all" class="radio-inline"><input type="radio" value="all" name="optradio">Send All</label><label  name="first" class="radio-inline"><input type="radio" value="first" name="optradio">Send 1st opts</label>';
	var string = '';
	for (var r = 0; r < data.length; r++) {
		string = string + '<li><h4>' + data[r]["title"] + "</h4></li>"
		for (var c = 0; c < data[r]["mobile"].length; c++) {
			string = string
					+ '<li> <input type="checkbox" name="mobile" refId="'
					+ data[r]["id"] + '" value="' + data[r]["mobile"][c] + '">'
					+ data[r]["mobile"][c] + "</li>";
		}

	}
	$('#myModal .modal-header').html("choose opts").append(
			'<ul>' + dfd + '</ul>');
	$('#myModal .modal-body').append('<ul>' + string + '</ul>');
	$('#myModal .modal-body').append(
			'<button class="btn btn-success" get="selectednum" mode=' + mode
					+ '>Send to Selected</button>');
	$('#myModal').modal('show');

	$("#myModal [name='all']").one("click", function(e) {
		for (var i = 0; i < single.length; i++) {
			data.push(single[i]);
		}
		var num = [];
		for (w = 0; w < data.length; w++) {
			num.push(data[w]["mobile"]);
		}
		$(mode).find("[name='receiver']").val(num);
		$('#myModal').modal('hide');
		dataContent = data;
	});

	$("#myModal [name='first']").one("click", function(e) {
		for (var i = 0; i < single.length; i++) {
			data.push(single[i]);
		}
		var num = [];
		for (w = 0; w < data.length; w++) {
			data[w]["mobile"] = data[w]["mobile"][0];
			num.push(data[w]["mobile"]);
		}
		$(mode).find("[name='receiver']").val(num);
		$('#myModal').modal('hide');
		dataContent = data;
		console.log(data)
	});

	$("#myModal  [get='selectednum']").one("click", function(e) {
		// var elm= $(elm).attr("mode");
		var objArr = [];

		$('#myModal .modal-body input[type=checkbox]').each(function() {
			if ($(this).is(":checked")) {
				var obj = {}
				obj["mobile"] = $(this).attr("value");
				obj["refId"] = $(this).attr("refId");
				objArr.push(obj);
			}

		});

		for (var g = 0; g < data.length; g++) {
			data[g]["mobile"] = [];
			for (var d = 0; d < objArr.length; d++) {
				if (objArr[d]["refId"] == data[g]["id"]) {
					data[g]["mobile"].push(objArr[d]["mobile"]);
				}
			}

		}
		for (var i = 0; i < single.length; i++) {
			data.push(single[i]);
		}
		console.log(data);
		var num = [];
		for (w = 0; w < data.length; w++) {
			if (data[w]["mobile"] != "")
				num.push(data[w]["mobile"]);
		}
		$(mode).find("[name='receiver']").val(num)
		dataContent = data;
		$('#myModal').modal('hide');

	});

}

/*

 $("#myModal [name='first']").on("click",function(e){
 var   selectedr=[];
 var value1 = $(mode).find("[name='receiver']").val();
 selectedr.push(value1);
 for(var r in data){
 if(checkData(data[r][0])){
 selectedr.push(data[r][0]);	
 selectedNum.push((data[r]=data[r][0]));
 }else{
 selectedr.push(data[r][1]);
 selectedNum.push((data[r]=data[r][1]));
 }
 }
 alert(selectedr);
 $(mode).find("[name='receiver']").val(selectedr);
 $('#myModal').modal('hide');
 });
 */

function SelectedNumber(elm) {
	/* 	  var elm= $(elm).attr("mode");
		  var selected = [];
		  $('#myModal .modal-body input[type=checkbox]').each(function() {
		     if ($(this).is(":checked")) {
		         selected.push($(this).attr('value'));
		     }
		  });
		 var value1 = $(elm).find("[name='receiver']").val();
		  selected.push(value1);
		 $(elm).find("[name='receiver']").val(selected)
		  $('#myModal').modal('hide');
	 */
}

function spliter(data) {
	if (checkData(data)) {
		var dfg = data.split(",");
		for (var q = 0; q < dfg.length; q++) {
			if (!checkData(dfg[q])) {
				dfg.remove(dfg[q]);
			}
		}
	}
	return dfg;
}
function multiNumberDisplat(data, mode) {
	var multiple = [];
	var single = [];
	for (var i = 0; i < data.length; i++) {
		selectedArr = spliter(data[i]["mobile"]);
		if (selectedArr.length > 1) {
			data[i]["mobile"] = selectedArr;
			multiple.push(data[i]);
		} else {
			data[i]["mobile"] = selectedArr;
			single.push(data[i])
		}

	}
	popNumberOpen(multiple, mode, single);

}
function campaignDisplayData(data, formId, mode) {
	if (mode == "sms") {
		var dataToShow = multiNumberDisplat(data.mobile, "#mymess1");
		$('#mymess1').find("[name='receiver']").val(dataToShow);
		console.log(data.mobile)
	} else {
		var dataToShowEmail = multiNumberDisplat(data.email, "#mymess");
		$('#mymess').find("[name='receiver']").val(dataToShowEmail);
	}

}

function formOpen(data, form) {
	switch (form) {
	case "email":
		adfe("0");
		$("#mySelect").val(0);
		(camOrCom == "company") ? (data.st5 != "" && data.st5 != null && data.st5 != undefined) ? $(
				'#mymess').find("[name='receiver']").val(data.st5.slice(1, -1))
				: $('#mymess').find("[name='receiver']").attr("placeholder",
						"No email id in database")
				: campaignDisplayData(data, '#mymess', "email");
		//formDataDisplay(data,'#mymess',"email")
		break;
	case "sms":
		adfe("1");
		$("#mySelect").val(1);
		(camOrCom == "company") ? (data.st7 != "" && data.st7 != null && data.st7 != undefined) ? $(
				'#mymess1').find("[name='receiver']")
				.val(data.st7.slice(1, -1))
				: $('#mymess1').find("[name='receiver']").val(
						"No number id in database")
				: campaignDisplayData(data, '#mymess1', "sms");
		//formDataDisplay(data,'#mymess1',"sms")
		break;
	}
}
/* function formDataDisplay(data,formId,mode){
 if(mode == "email"){
 if(camOrCom == "company"){
 (data.st5 != "" && data.st5 != null && data.st5 != undefined) ?	$(formId).find("[name='receiver']").val(data.st5.slice(1,-1)) : $(formId).find("[name='receiver']").attr("placeholder", "No email id in database");
 }else if(camOrCom == "campaign"){
 campaignDisplayData(data, formId);	
 }
 }
 else if(mode == "sms"){
 if(camOrCom == "company"){
 (data.st7 != "" && data.st7 != null && data.st7 != undefined) ?	$(formId).find("[name='receiver']").val(data.st7.slice(1,-1)) : $(formId).find("[name='receiver']").val("No number id in database");
 }else if(camOrCom == "campaign"){
 campaignDisplayData(data, formId,"sms");	
 }


 }
 } */

function ajaxSender(URL, Method, success, error, data) {
	if (Method == "get") {
		$.ajax({
			url : urlprefix + URL,
			method : Method,
			contentType : "application/json",
			//data : JSON.stringify(data),
			success : success,
			error : error
		});
	} else {
		$.ajax({
			url : urlprefix + URL,
			method : Method,
			contentType : "application/json",
			data : JSON.stringify(data),
			success : success,
			error : error
		});
	}

}

$(document).ready(
		function() {
			var url = "";
			if (window.location.search) {
				var hashArr = hashparameter(window.location.search);
				if (hashArr["company"]) {
					url = "contentdata/"
							+ (hashArr["company"] ? hashArr["company"]
									: alert("No company data id"));/*to do*/
					camOrCom = "company";
					refenceId = hashArr["company"];
				} else if (hashArr["campaign"]) {
					url = "manager/bulk/"
							+ (hashArr["campaign"] ? hashArr["campaign"]
									: alert("No Campaign id"));
					camOrCom = "campaign";
					refenceId = hashArr["campaign"];
				} else {
					//alert("No Data found")
				}

				ajaxSender(url, "GET", function(e) {
					formOpen(e.result, hashArr["type"]);
					console.log(e);
				}, function() {
				}, "");

			} else {

			}
		});
