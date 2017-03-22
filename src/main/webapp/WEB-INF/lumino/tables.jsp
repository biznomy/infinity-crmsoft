<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Tables</title>

<link href='<c:url value="/web-resources/lumino/css/bootstrap.min.css"/>' rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/datepicker3.css"/>' rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/styles.css"/>' rel="stylesheet">

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
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Profile</a></li>
							<li><a href="#"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Settings</a></li>
							<li><a href="#"><svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
							
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li><a href="index"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-dashboard-dial"></use></svg> Dashboard</a></li>
			<li><a href="widgets"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg> Widgets</a></li>
			<li><a href="charts"><svg class="glyph stroked line-graph"><use xlink:href="#stroked-line-graph"></use></svg> Charts</a></li>
			<li><a href="campaign"><svg class="glyph stroked hourglass"><use xlink:href="#stroked-hourglass"/></svg> campaign</a></li>
			<li class="active"><a href="companies"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> companies</a></li>
			<li><a data-toggle="collapse" data-target="#demo"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg>edmessagemanager<span class="caret"></span></a>
               <ul id="demo" class="collapse" style="list-style-type: none;">
			     <li style="margin-top: 10px"><a href="edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
				 <li style="margin-top: 23px"><a href="edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>
			    </ul>
			  </li>
			<li><a href="person"><svg class="glyph stroked male user "><use xlink:href="#stroked-male-user"/></svg>Add Person</a></li>
			<li><a href="message"><svg class="glyph stroked empty message"><use xlink:href="#stroked-empty-message"/></svg>Add Message</a></li>
			<li><a href="forms"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
			<li><a href="panels"><svg class="glyph stroked app-window"><use xlink:href="#stroked-app-window"></use></svg> Alerts &amp; Panels</a></li>
			<li><a href="icons"><svg class="glyph stroked star"><use xlink:href="#stroked-star"></use></svg> Icons</a></li>
			<li class="parent ">
				<!--  <a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down"><use xlink:href="#stroked-chevron-down"></use></svg></span> Dropdown 
				</a>-->
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 1
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 2
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 3
						</a>
					</li>
				</ul>
			</li>
			<li role="presentation" class="divider"></li>
			<li><a href="login"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Login Page</a></li>
		</ul>

	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Icons</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Record Table</h1>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-lg-4">Campaign Solution</div>
							<div class="col-lg-2"><label id="alert"></label></div>
							<div class="col-lg-6">
								<div class="row">
								 	<div class="col-lg-4"  onclick="page.makeCampaign()" style="z-index: 12">
								 		<a class="btn btn-info"  style="margin-left: 155px;">Create Campaign</a>	
								 	</div>
								 	<div class="col-lg-3" onclick="page.checkall()" style="z-index: 1">
								 		<a class="btn btn-info"   style="margin-left: 135px;">Mark All</a>	
								 	</div>
								 	<div class="col-lg-4">
								 		<a class="btn btn-info" onclick="page.uncheckall()" style="margin-left: 93px;">UnmarkAll</a>	
								 	</div>
								 </div>
							</div>
						</div>						
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-2"><input class="form-control" type="text" name="campaign" placeholder="campaign name here" /></div>
							<div class="col-lg-2"><input class="form-control" type="text" name="count" placeholder="record count" disabled="disabled"/></div>
							<div class="col-lg-2">
								<select class="form-control" name="medium" >
									<option value="sms">SMS</option>
									<option value="whatsapp">Whats App</option>
									<option value="email">Email</option>								
								</select>
							</div>
							<div class="col-lg-2"><input class="form-control" id="go2" type="text" name="pageTo" placeholder="page count"/></div>
							<div class="col-lg-2"><input class="form-control" id="go1" type="text" name="pageLength" placeholder="page size"/></div>
							<div class="col-lg-2"><button class="btn btn-success" id="Go">GO</button></div>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
								<div class="col-md-4"> Advance Filter </div>
								<div class="col-md-4">
									<div class="form-group">
										<form  id="simple_search_form" role="form" method="get" action="simplequery">
											<div class="row">
												<div class="col-md-8">
													<input type="text" class="form-control" name="search"/>
												</div>
												<div class="col-md-4">
													<button class="btn btn-success">search</button>
												</div>
											</div>
										</form>
									</div>
								</div>							
								<div class="col-md-2 pull-right"><a style="cursor: pointer; text-decoration:none; " onclick="page.ontoggle()">toggle</a></div>		
						</div><!-- row -->					
					</div>
					<div class="panel-body">
						<form role="form" id="advance_search_form" method="get" action="advancequery" style="display:none;">
						
							<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<button class="btn btn-info pull-right">Search</button>
										</div>
									</div>
							</div><!-- row -->							
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Title</label> <input class="form-control" name="titleS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Website</label> <input class="form-control"  name="webS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Email</label> <input class="form-control" name="emailS" />
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Address1</label> <input class="form-control" name="add1S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Address2</label> <input class="form-control" name="add2S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Country</label> <input class="form-control" name="countS"/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>City</label> <input class="form-control" name="cityS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>State</label> <input class="form-control" name="stateS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Source</label> <input class="form-control" name="sourcS"/>
										</div>
									</div>
							</div><!-- row -->
							
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Category 1</label> <input class="form-control" name="cat1S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Category 2</label> <input class="form-control" name="cat2S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Category 3</label> <input class="form-control" name="cat3S"/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>WhatsApp</label> <input class="form-control" name="whatS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Landline</label> <input class="form-control" name="landS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Mobile</label> <input class="form-control" name="mobiS"/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Twitter</label> <input class="form-control" name="twittS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Facebook</label> <input class="form-control" name="faceS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>LinkedIn</label> <input class="form-control" name="linkeS"/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Remark 1</label> <input class="form-control" name="rema1S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Remark 2</label> <input class="form-control" name="rema2S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Remark 3</label> <input class="form-control" name="rema3S"/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>On Twitter</label> <input name="twitterStateS" type="checkbox" class="form-control"  name="onTwit" value="1"/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>On Facebook</label> <input name="facebookStateS" type="checkbox" class="form-control" name="onFace" value="1"/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>On LinkedIn</label> <input name="linkedInStateS" type="checkbox" class="form-control" name="onLink" value="1"/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>On WhatsApp</label> <input name="whatsAppStateS" type="checkbox" class="form-control" name="onWhats" value="1"/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Source</label> <input class="form-control" name="sourceS"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Status1</label> <input class="form-control"  name="status1S"/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>ln6</label> <input class="form-control" name="ln6S" />
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>LANDLINE IS BLANK</label> <input type="checkbox" class="form-control" name="lanLineBlank" value=""/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>MOBILE IS BLANK</label> <input type="checkbox" class="form-control"  name="mobileBlank" value=""/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>EMAIL IS BLANK</label> <input type="checkbox" class="form-control" name="emailBlank" value=""/>
										</div>
									</div>
							</div><!-- row -->
							<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>LANDLINE IS NOT BLANK</label> <input type="checkbox" class="form-control" name="lanLineNotBlank" value=""/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>MOBILE IS NOT BLANK</label> <input type="checkbox" class="form-control"  name="mobileNotBlank" value=""/>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>EMAIL IS NOT BLANK</label> <input type="checkbox" class="form-control" name="emailNotBlank" value=""/>
										</div>
									</div>
							</div><!-- row -->
						</form>
					
					</div>
				</div>
			</div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">  
						<div class="row">
							<div class="col-lg-4">Company Count  :  Page ${currentPage} of ${totalPages} </div>
							<div class="col-lg-5"><label id="result"></label></div>
							<div class="col-lg-3"><div style="margin-left: -78px;">Total Results : ${totalCount}</div><a  class="btn btn-primary" href="/crmsoft/company/new" style="margin-top: -40px; float: right;" >Add Company</a></div>	
						</div>
					</div>
					
					
					
					<div class="panel-body">
						 <table id="resultTable" class="table table-bordered table-hover" width="100%">
						    <thead>
						    <tr>
						    	<th style="max-width: 10%;">Mark</th>
						        <th style="max-width: 10%;">Data Id</th>
						        <th style="max-width: 20%;">Data Title</th>
						        <th style="max-width: 20%;">Website</th>
						        <th style="max-width: 20%;">City</th>
						        <th style="max-width: 25%;">Events</th>
						    </tr>
						    </thead>
						    <tbody>
							    <c:forEach var="i" items="${contentDatas}">
							    	<tr>
							    		<td style="max-width: 10%;"><input type="checkbox" value="<c:out value="${i.id}" />"/></td>
								        <td style="max-width: 10%;"><c:out value="${i.id}" /></td>
								        <td style="max-width: 20%;"><c:out value="${i.title}" /></td>
								        <td style="max-width: 20%;"><a href="<c:out value="${i.st4}" />" target="_blank">${ i.st4 } </a></td>
								        <td style="max-width: 20%;"><c:out value="${i.st1}" /></td>
										<td style="max-width: 20%;">
										    <a class="btn-info btn-sm" style="padding-top:9px;padding-bottom:9px;text-decoration: none;" href=edit/<c:out value="${i.id}" />>Edit</a>
											<a class="btn-sm btn btn-success " href=view/<c:out value="${i.id}" />>View</a>
											<a class="btn-sm btn btn-warning " href="/crmsoft/edmessagemanager?type=email&company=<c:out value="${i.id}" />">Email</a>
											<a class="btn-sm btn btn-primary " href="/crmsoft/edmessagemanager?type=sms&company=<c:out value="${i.id}" />">Sms</a>
										</td>
									</tr>
							    </c:forEach>
						    </tbody>
						</table> 
					</div>
				</div>
			</div>
		</div><!--/.row-->	
		<div>
			<ul class="pager">
				<c:if test="${currentPage > 1}">
				   <li class="previous"><a onclick="previous()">Previous</a></li>
				</c:if>					
				<c:if test="${currentPage < totalPages}">
				   <li class="next"><a onclick="next(event)">Next</a></li>
				</c:if>
			 </ul>			 
		</div>		
		
		
		
	</div><!--/.main-->

	<script src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/bootstrap-datepicker.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/tables.js"/>'></script>
	
</body>

</html>
