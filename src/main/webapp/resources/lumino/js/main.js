var constant = {
	baseUrl : "http://localhost:9090/crmsoft",
	contentData : "/contentdata",
	campaign : "/campaign",
	update : "/update"
}

function getQueryVariable(variable) {
	var query = window.location.search;
	query = query.replace("?", "");
	var vars = query.split("&");
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (pair[0] == variable) {
			return pair[1];
		}
	}
}

!function($) {
	$(document).on("click", "ul.nav li.parent > a > span.icon", function() {
		$(this).find('em:first').toggleClass("glyphicon-minus");
	});
	$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
}(window.jQuery);

$(window).on('resize', function() {
	if ($(window).width() > 768)
		$('#sidebar-collapse').collapse('show')
})
$(window).on('resize', function() {
	if ($(window).width() <= 767)
		$('#sidebar-collapse').collapse('hide')
})
function next(event) {
	event.preventDefault();
	event.stopPropagation();
	var search = window.location.search;
	var value = getQueryVariable("page");
	if (value == undefined)value = 0;
	search = search.replace("page="+value, "page=" + (++value));
	if (search === "" || search.indexOf("page") == -1 || search.indexOf("max") == -1) {search += "&page=1&max=10";}
	window.location.search = search;

}
function previous() {
	event.preventDefault();
	event.stopPropagation();
	var search = window.location.search;
	var value = getQueryVariable("page");
	if (value == undefined)value = 0;
	search = search.replace("page="+value, "page=" + (--value));
	if (search === "" || search.indexOf("page") == -1 || search.indexOf("max") == -1) {search += "&page=1&max=10";}
	window.location.search = search;
}

function maxCountChange(){
	var updateMax = $("[name=pageLength]").val();
	var search = window.location.search;
	var value = getQueryVariable("max");
	if (value == undefined)value = 0;
	search = search.replace("max="+value, "max=" + (updateMax));
	if (search === "" || search.indexOf("page") == -1 || search.indexOf("max") == -1) {search += "&page=1&max="+updateMax;}
	window.location.search = search;		
}

function maxCountPageToChange(){
	var updateMax = $("[name=pageLength]").val();
	console.log(updateMax);
	var movePage = $("[name=pageTo]").val();
	console.log(movePage);
   // alert(updateMax  +"--"+movePage);
	var search = window.location.search;
    console.log(search);
	var value = getQueryVariable("max");
	console.log(value);
	var value1 = getQueryVariable("page");
	console.log(value1);
	if (value == undefined)value = 0;
	if (value1 == undefined)value1 = 0;
	console.log(value);
	search = search.replace("max="+value, "max=" + (updateMax));
	search = search.replace("page="+value1, "page=" + (movePage));
	if (search === "" || search.indexOf("page") == -1 || search.indexOf("max") == -1) {search += "&page="+movePage+"&max="+updateMax;
	  console.log(search);}
	window.location.search = search;		
}

function moveToPage() {
	var movePage = $("[name=pageTo]").val();
	var search = window.location.search;
	var value = getQueryVariable("page");
	if (value == undefined)value = 0;
	if (search === "" || search.indexOf("page") == -1 || search.indexOf("max") == -1) {search += "&page="+movePage+"&max=10";}
	window.location.search = search;
}

