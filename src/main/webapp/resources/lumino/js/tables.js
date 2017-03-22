var page = {
	ontoggle : function() {
		$("#advance_search_form").toggle();
	},
	makeCampaign : function(alert) {
		var key = $("[name=campaign]").val();
		var count = $("[name=count]").val();
		count = Number(count);

		if (!key || isNaN(count) || count < 1) {
			alert("invalied Campaign");
			return;
		}

		var favorite = [];
		$.each($("#resultTable input:checked"), function() {
			favorite.push($(this).val());
		});
		var data = {};
		data["st3"] = $("[name=medium]").val();
		data["title"] = key;
		data["type"] = "campaign";
		data["lt1"] = "," + favorite.join(",") + ",";
		data["ln1"] = count;
		data["media1"] = "";
		data["media2"] = "";
		data["lt2"] = "";
		data["ln2"] = new Date().getTime();
		data["endUser"] = {};
		data["edContentCollection"] = {};
		data["endUser"]["id"] = 123456;
		data["edContentCollection"]["id"] = 123;
		data["language"] = "en";

		$.ajax({
			//url : "http://localhost:9090/crmsoft/contentdata",
			url : constant.baseUrl + constant.contentData,
			method : "POST",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(result) {
				$("#result").text(result.message);
				setTimeout(function() {
					$("#result").text("");
				}, 2000);
			},
			error : function(jsXr) {
				console.log(jsXr);
				if (jsXr.status == 0) {
					$("#result").text("NetWork Problem");
				} else {
					$("#result").text(
							jsXr.status + "Error :  " + jsXr.responseText);
				}
				setTimeout(function() {
					$("#result").text("");
				}, 2000);
			}
		});
	},
	checkall : function() {
		$('#resultTable').find('input:checkbox').prop('checked', true);
		this.countChange();
	},
	uncheckall : function() {
		$('#resultTable').find('input:checkbox').prop('checked', false);
		this.countChange();
	},
	countChange : function() {
		var change = [];
		$.each($("#resultTable input:checked"), function() {
			change.push($(this).val());
		});
		$("[name=count]").val(change.length);
	},
	maxCountChange : function() {
		maxCountChange();
	},
	moveToPage : function() {
		moveToPage();
	}
}
$(document).on("change", "input:checkbox", page.countChange);

$("#Go").on("click", function() {
	maxCountPageToChange();	
});
