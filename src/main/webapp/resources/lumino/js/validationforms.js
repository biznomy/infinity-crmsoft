var validationforms = {
		validateform : function(myObject) {
			console.log(myObject);
		},
		validateformcompanyNew : function() {
			var errorflag = false;
			if (myvalidation.validateBlank($("[name='title']").val())) {
				$("#titleError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			} 
			
			if(myvalidation.validateBlank($("[name='st10']").val())){
				$("#DataError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if (myvalidation.validateBlank($("[name='detail']").val())) {
				$("#Add1Error").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			} 
			if(myvalidation.validateBlank($("[name='category1']").val())){
				$("#CategoryError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			return errorflag;
		},
		validateformemail : function(){
			var errorflag = false;
			if (myvalidation.validateBlank($("[name='eventInfo']").val())) {
				$("#InfoError").html("<i style='color:#ff0000;'> :error should not be blank</i>");	
				errorflag = true;
			} 
			
			if (myvalidation.validateEmail($("[name='sender']").val())) {
				$("#SenderError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			
			if (myvalidation.validateBlank($("[name='senderName']").val())) {
				$("#NameError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if (myvalidation.validateEmail($("[name='receiver']").val())) {
				$("#ReceiverError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			
			if(myvalidation.validateBlank($("[name='title']").val())){
				$("#titleError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='content']").val())){
				$("#bodyError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='deliveryDate']").val())){
				$("#typeError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validatephoneno($("[name='userId']").val())){
				$("#IdError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='userName']").val())){
				$("#NameError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='key']").val())){
				$("#ParaError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			return errorflag;
		},
		validateformsms : function(){
			var errorflag = false;
			if (myvalidation.validateBlank($("[name='eventInfo']").val())) {
				$("#InfoError1").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			} 
			
			if (myvalidation.validatephoneno($("[name='receiver']").val())) {
				$("#NumberError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='content']").val())){
				$("#textError").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='deliveryDate']").val())){
				$("#typeError1").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validatephoneno($("[name='userId']").val())){
				$("#IdError1").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='userName']").val())){
				$("#NameError1").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='key']").val())){
				$("#ParaError1").html("<i style='color:#ff0000;'> :error should not be blank</i>");
				errorflag = true;
			}
			return errorflag;
		},
		validateformPerson : function(){
			var errorflag = false;
			if (myvalidation.validateBlank($("[name='ref1']").val())) {
				$("#Id").html("<i style='color:#ff0000;'> error should not be blank</i>");										
				errorflag = true;
			}
			if (myvalidation.validateBlank($("[name='st1']").val())) {
				$("#fName").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			} 
			
			if (myvalidation.validateBlank($("[name='st2']").val())) {
				$("#lName").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			}
			if (myvalidation.validateBlank($("[name='st3']").val())) {
				$("#select").html("<i style='color:#ff0000;'> error should not be blank</i>");										
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='st4']").val())){
				$("#desiError").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			}
			if(!myvalidation.validateNumber($("[name='endUser.id']").val()).expression){
				$("#dataError").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			}
		return errorflag;
		},
		validateformMessage : function(){
			var errorflag = false;
			if (myvalidation.validateBlank($("[name='ref1']").val())) {
				$("#Id").html("<i style='color:#ff0000;'> error should not be blank</i>");										
				errorflag = true;
			}
			
 			if (myvalidation.validateBlank($("[name='st2']").val())) {
				$("#statusError").html("<i style='color:#ff0000;'> error should not be blank</i>");										
				errorflag = true;
			} 
 			if (myvalidation.validateBlank($("[name='st3']").val())) {
				$("#select").html("<i style='color:#ff0000;'> error should not be blank</i>");										
				errorflag = true;
			}
			
			if (myvalidation.validateBlank($("[name='st5']").val())) {
				$("#RefType1Error").html("<i style='color:#ff0000;'> error should not be blank</i>");										
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='st4']").val())){
				$("#ApproachError").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			}
			if(myvalidation.validateBlank($("[name='lt1']").val())){
				$("#text1Error").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			}
			if(!myvalidation.validateNumber($("[name='endUser.id']").val()).expression){
				$("#DataOperError").html("<i style='color:#ff0000;'> error should not be blank</i>");
				errorflag = true;
			}
		return errorflag;
		}

}