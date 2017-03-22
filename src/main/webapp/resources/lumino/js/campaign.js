
	var page = {
		ontoggle : function(){
			$("#advance_search_form").toggle();
		},		
		checkall : function(){
			$('#resultTable').find('input:checkbox').prop('checked', true);
			this.countChange();
		},
		uncheckall : function(){
			$('#resultTable').find('input:checkbox').prop('checked', false);
			this.countChange();
		},
		countChange :  function(){
			var change = [];
	        $.each($("#resultTable input:checked"), function(){            
	        	change.push($(this).val());
	        });
	        $("[name=count]").val(change.length);
		},
		deleteL :  function(id){
			$.ajax({
				url : constant.baseUrl + constant.contentData + "/" + id,
				method:"delete",
				contentType : "application/json",
				success : function(data){
					$("input[value="+id+"]").parent().parent().remove();
				},
				error : function(jxHR){
					console.log(jsXr);
					}
			});
		},
		maxCountChange : function(){
			maxCountChange();		
		},
		moveToPage : function(){
			moveToPage();	
		},
		searchType : function(){
			var medium = $("[name=medium]").val();
			var search = window.location.search;
			var value = getQueryVariable("medium");
			if (value == undefined)value = "";
			search = search.replace("medium="+value, "medium=" + medium);
			if (search === "" || search.indexOf("medium") == -1 ) {search += "&medium="+medium;}
			window.location.search = search;
		},
		searchUpdate : function(){
			moveToPage();
			maxCountChange();
			var title = $("[name=title]").val();
			var search = window.location.search;
			var value = getQueryVariable("title");
			if (value == undefined)value = "";
			search = search.replace("title="+value, "title=" + title);
			if (search === "" || search.indexOf("title") == -1 ) {search += "&title="+title;}
			window.location.search = search;
		}

	}	
			$(document).on("change", "input:checkbox", page.countChange);
	
	$("#ongo").on("click", function () {
		maxCountPageToChange();
	
		});
	
	function myFunction() {
		 location.href = "http://localhost:9090/crmsoft/campaign";
	}
