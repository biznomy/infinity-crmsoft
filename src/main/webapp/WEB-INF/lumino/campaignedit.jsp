<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Forms</title>

<link
	href='<c:url value="/web-resources/lumino/css/bootstrap.min.css"/>'
	rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/datepicker3.css"/>'
	rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/styles.css"/>'
	rel="stylesheet">

<!--Icons-->
<script src='<c:url value="/web-resources/lumino/js/lumino.glyphs.js"/>'></script>

<!--[if lt IE 9]>
<script src='<c:url value="/web-resources/lumino/js/html5shiv.js"/>'></script>
<script src='<c:url value="/web-resources/lumino/js/respond.min.js"/>'></script>
<![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
				<ul class="user-menu">
					<li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><svg	class="glyph stroked male-user">
								<use xlink:href="#stroked-male-user"></use></svg> User <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><svg class="glyph stroked male-user">
										<use xlink:href="#stroked-male-user"></use></svg> Profile</a></li>
							<li><a href="#"><svg class="glyph stroked gear">
										<use xlink:href="#stroked-gear"></use></svg> Settings</a></li>
							<li><a href="#"><svg class="glyph stroked cancel">
										<use xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
						</ul></li>
				</ul>
			</div>

		</div>
		<!-- /.container-fluid -->
	</nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li><a href="index"><svg
						class="glyph stroked dashboard-dial">
						<use xlink:href="#stroked-dashboard-dial"></use></svg> Dashboard</a></li>
			<li><a href="widgets"><svg class="glyph stroked calendar">
						<use xlink:href="#stroked-calendar"></use></svg> Widgets</a></li>
			<li><a href="charts"><svg class="glyph stroked line-graph">
						<use xlink:href="#stroked-line-graph"></use></svg> Charts</a></li>
			<li><a href="campaign"><svg class="glyph stroked hourglass"><use xlink:href="#stroked-hourglass"/></svg> campaign</a></li>
			<li><a href="companies"><a href="companies"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> companies</a></li>
			<li><a data-toggle="collapse" data-target="#demo"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg>edmessagemanager<span class="caret"></span></a>
               <ul id="demo" class="collapse" style="list-style-type: none;">
			     <li><a href="edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
				 <li><a href="edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>
			    </ul>
			  </li>
			<li><a href="person"><svg class="glyph stroked male user "><use xlink:href="#stroked-male-user"/></svg>Add Person</a></li>
			<li><a href="message"><svg class="glyph stroked empty message"><use xlink:href="#stroked-empty-message"/></svg>Add Message</a></li>
			<li class="active"><a href="forms"><svg
						class="glyph stroked pencil">
						<use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
			<li><a href="panels"><svg class="glyph stroked app-window">
						<use xlink:href="#stroked-app-window"></use></svg> Alerts &amp; Panels</a></li>
			<li><a href="icons"><svg class="glyph stroked star">
						<use xlink:href="#stroked-star"></use></svg> Icons</a></li>
			<li class="parent "> <!--  <a href="#"> <span
					data-toggle="collapse" href="#sub-item-1"><svg
							class="glyph stroked chevron-down">
							<use xlink:href="#stroked-chevron-down"></use></svg></span> Dropdown
			</a>-->
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="#"> <svg
								class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 1
					</a></li>
					<li><a class="" href="#"> <svg
								class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 2
					</a></li>
					<li><a class="" href="#"> <svg
								class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 3
					</a></li>
				</ul></li>
			<li role="presentation" class="divider"></li>
			<li><a href="login"><svg class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use></svg> Login Page</a></li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Icons</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">${edContentData.title}  - (${edContentData.id})</h3>
			</div>
		</div>
		<!--/.row-->


      <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Message</h4>
        </div>
        <div class="modal-body">
          <p>		
          <div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
						<div class="panel-body" id="campid">
						<div class="row table">
							<div class="col-md-3"><strong>campaign Id</strong></div>
							<div class="col-md-8"><input type="number" name="ref1" class="form-control" placeholder="campaign Id" value="${edContentData.id}"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Status</strong></div>
							<div class="col-md-3"><input type="text" name="st2" class="form-control" placeholder="Status"></div>
							<div class="col-md-3"><strong>Medium</strong></div>
							<div class="col-md-3"><input type="text" name="st3" class="form-control" placeholder="Medium"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Contact Date</strong></div>
							<div class="col-md-3"><input type="text" class="form-control" placeholder="Contact Date" readonly="readonly"></div>
							<div class="col-md-3"><strong>Ref Type</strong></div>
							<div class="col-md-3"><input type="text" name="st5" class="form-control" placeholder="Ref Type"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Touch Again</strong></div>
							<div class="col-md-3"><input type="text" class="form-control" placeholder="Touch Again" readonly="readonly"></div>
							<div class="col-md-3"><strong>Aproach By</strong></div>
							<div class="col-md-3"><input type="text" name="st4" class="form-control" placeholder="Aproach By"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Text 1</strong></div>
							<div class="col-md-3"><input type="text" name="lt1" class="form-control" placeholder="Text 1"></div>
							<div class="col-md-3"><strong>Text 2</strong></div>
							<div class="col-md-3"><input type="text" name="lt2" class="form-control" placeholder="Text 2"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Media 1</strong></div>
							<div class="col-md-8"><input type="text" name="media1" class="form-control" placeholder="Media 1"></div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Media 2</strong></div>
							<div class="col-md-8"><input type="text" name="media2" class="form-control" placeholder="Media 2"></div>
														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 1</strong></div>
							<div class="col-md-8"><input type="text" name="lt8" class="form-control" placeholder="Remark 1"></div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 2</strong></div>
							<div class="col-md-8"><input type="text" name="lt9" class="form-control" placeholder="Remark 2"></div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 3</strong></div>
							<div class="col-md-8"><input type="text" name="lt10" class="form-control" placeholder="Remark 3"></div>														
						</div>
						
						<div class="row table">
							<div class="col-md-3"><strong>language</strong></div>
							<div class="col-md-8"><input type="text" name="language" class="form-control" placeholder="language" value="en"></div>														
						</div>
						
						<div class="row table">
							<div class="col-md-3"><strong>type</strong></div>
							<div class="col-md-8"><input type="text" name="type" class="form-control" placeholder="message" value="message"></div>														
						</div>
						
						<div class="row table">
							<div class="col-md-3"><strong>endUser</strong></div>
							<div class="col-md-8"><input type="number" name="endUser" class="form-control" placeholder="123456" readonly="readonly"></div>														
						</div>					
						
						<!--  <div class="col-md-12">
							<button id="btnId" type="submit" class="btn btn-primary" onclick="objectmapper('#campid')">Save</button>								
						</div>  -->
																											
					</div>
					
					
				</div>
			</div><!-- /.col-->
		</div><!-- /.row --></p>
        </div>
        
        <div class="modal-footer">
                        <button type="button" id="btnId" class="btn btn-primary" style="float: left;" data-dismiss="modal" onclick="objectmapper1('#campid')">save</button>
                        </div>	
        
      </div>
      
    </div>
  </div>




		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<form role="form" id="form1">
							<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
							<div class="col-md-6">
								<div class="form-group">
									<label>Title</label> <input name="title" class="form-control" placeholder="Title" value="${edContentData.title}">
								</div>
								<div class="form-group">
									<label>Type</label> <input name="type" class="form-control" placeholder="Type" value="${edContentData.type}" readonly="readonly">
								</div>
								<div class="form-group">
									<label>Medium</label> <input name="st3" class="form-control" placeholder="Campaign Medium" value="${edContentData.st3}">
								</div>									
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Status</label> <input name="status1" class="form-control" placeholder="Status" value="${edContentData.status1}">
								</div>
								<div class="form-group">
									<label>Count</label> <input name="ln1" class="form-control" placeholder="Count" value="${edContentData.ln1}">
								</div>
								<div class="form-group">
									<label>Created On</label> <input class="form-control" placeholder="Received On" readonly="readonly" value="${edContentData.receivedDate}">
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form1')">Save</button>								
							</div>
						</form>
					</div>
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Remarks : ${ edContentData.id }</div>
					<div class="panel-body">
						<form role="form" id="form2">
							<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
							<div class="col-md-12">
								<div class="form-group">
									<label>First Remark</label> <textarea name="lt8" class="form-control" placeholder="Ist Remark">${edContentData.lt8}</textarea>
								</div>
								<div class="form-group">
									<label>Second Remark</label> <textarea name="lt9" class="form-control" placeholder="IInd Remark">${edContentData.lt9}</textarea>
								</div>
								<div class="form-group">
									<label>Third Remark</label> <textarea name="lt10" class="form-control" placeholder="IIIrd Remark">${edContentData.lt10}</textarea>
								</div>
								<div class="form-group">
									<label>Message</label> <textarea name="lt2" class="form-control" placeholder="Message">${edContentData.lt2}</textarea>
								</div>
								<div class="form-group">
									<label>Media 1</label> <input type="text" name="media1" class="form-control" placeholder="Media 1" value="${edContentData.media1}"/>
								</div>
								<div class="form-group">
									<label>Media 2</label> <input type="text" name="media2" class="form-control" placeholder="Media 2" value="${edContentData.media2}"/>
								</div>																	
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form2')">Save</button>								
							</div>
						</form>
					</div>
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row table">
							<div class="col-md-3">Messages</div>
							<div class="col-md-8">Total Count = ${messageDatas.size()}</div>
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float: right;margin-top: -40px">Messages</button>
						</div>						
					</div>
					<c:forEach var="messageData" items="${messageDatas}">
						<div class="panel-body" style="border:1px solid #ccc;">
						<form role="form" id="form3">
						<input type="hidden" name="id" value="${messageData.id}"/>
						<div class="row table">
							<div class="col-md-3"><strong>Message Id</strong></div>
							<div class="col-md-8"><b>${messageData.id}</b></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Status</strong></div>
							<div class="col-md-3"><input type="text" name="st2" class="form-control" placeholder="Status" value="${messageData.st2}"></div>
							<div class="col-md-3"><strong>Medium</strong></div>
							<div class="col-md-3"><input type="text" name="st3" class="form-control" placeholder="Medium" value="${messageData.st3}"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Contact Date</strong></div>
							<div class="col-md-3"><input type="text" class="form-control" placeholder="Contact Date" value="${messageData.date2}" readonly="readonly"></div>
							<div class="col-md-3"><strong>Ref Type</strong></div>
							<div class="col-md-3"><input type="text" name="st5" class="form-control" placeholder="Ref Type" value="${messageData.st5}"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Touch Again</strong></div>
							<div class="col-md-3"><input type="text" class="form-control" placeholder="Touch Again" value="${messageData.date1}" readonly="readonly"></div>
							<div class="col-md-3"><strong>Aproach By</strong></div>
							<div class="col-md-3"><input type="text" name="st4" class="form-control" placeholder="Aproach By" value="${messageData.st4}"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Text 1</strong></div>
							<div class="col-md-3"><input type="text" name="lt1" class="form-control" placeholder="Text 1" value="${messageData.lt1}"></div>
							<div class="col-md-3"><strong>Text 2</strong></div>
							<div class="col-md-3"><input type="text" name="lt2" class="form-control" placeholder="Text 2" value="${messageData.lt2}"></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Media 1</strong></div>
							<div class="col-md-8"><input type="text" name="media1" class="form-control" placeholder="Media 1" value="${messageData.media1}"></div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Media 2</strong></div>
							<div class="col-md-8"><input type="text" name="media2" class="form-control" placeholder="Media 2" value="${messageData.media2}"></div>
														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 1</strong></div>
							<div class="col-md-8"><input type="text" name="lt8" class="form-control" placeholder="Remark 1" value="${messageData.lt8}"></div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 2</strong></div>
							<div class="col-md-8"><input type="text" name="lt9" class="form-control" placeholder="Remark 2" value="${messageData.lt9}"></div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 3</strong></div>
							<div class="col-md-8"><input type="text" name="lt10" class="form-control" placeholder="Remark 3" value="${messageData.lt10}"></div>														
						</div>
						<div class="col-md-12">
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form3')">Save</button>								
							</div>
						</form>																							
					</div>
					</c:forEach>
					
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
	</div>
	<!--/.main-->

	<script src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
	<script>
		$("form").on("submit", function(event){
			event.preventDefault();
			/* var value = $(this).serialize();
			$.ajax({
				url : constant.baseUrl + constant.contentData + constant.update,
				method:"post",
				data : value,
				success : function(dt){
					
				}				
			});		 */		
		});
        var obj ={};
        var urlprefix = "http://localhost:9090/crmsoft/";
        function objectmapper(DomId){
        	var app =$(DomId).find("[name]");
        	for(var i=0; i<app.length; i++){
        		var name=$(app[i]).attr("name");
        		var value=$(app[i]).val();
        		var id= $(DomId).find('[name="id"]').val();
        		obj[name]=value;
        	}
        	console.log(obj);
        	ajaxsender("contentdata/" ,"PATCH" ,function(dt){
    			console.log(dt);
    		},function(){
    			alert("error request");
    		},obj,id);
        }
    
        
        var endUser ={};
        var id = {"id":123456};
        function objectmapper1(DomId){
        	var app =$(DomId).find("[name]");
        	endUser ={};
        	for(var i=0; i<app.length; i++){
        		var name=$(app[i]).attr("name");
        		var value=$(app[i]).val();
        		obj[name]=value; 
        	    obj["endUser"]=id; 
        	}
        	console.log(obj);
        	ajaxsender1("contentdata/" ,"post" ,function(dt){
    			console.log(dt);
    		},function(){
    			alert("error request");
    			
    		},obj);
             
        }

        
        function ajaxsender1(URL ,Method , success , error , data){
        	$.ajax({
				url : urlprefix+ URL,
				method : Method,
				contentType : "application/json",
				data : JSON.stringify(data),
				dataType : "json",
				success : success,
				error : error,
			});
        }
        
        function ajaxsender(URL ,Method , success , error , data , id){
        	if(Method == "get"){
        		$.ajax({
					url : urlprefix+ URL,
					method : Method,
					contentType : "application/json",
					//data : JSON.stringify(data),
					dataType : "json",
					success : success,
					error : error,
				});
        	}else {
        		$.ajax({
					url : urlprefix+ URL+ ((id != null && id != undefined && id != "") ? id : ""),
					method : Method,
					contentType : "application/json",
					data : JSON.stringify(data),
					dataType : "json",
					success : success,
					error : error,
				});
        	}

        }
        
		
	</script>
	
	
</body>

</html>