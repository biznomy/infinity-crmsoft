<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Person</title>

<link
	href='<c:url value="/web-resources/lumino/css/bootstrap.min.css"/>'
	rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/datepicker3.css"/>'
	rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/styles.css"/>'
	rel="stylesheet">
<link href='<c:url value="/web-resources/lumino/css/select2.css"/>'
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
					<li class="dropdown pull-right"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><svg
								class="glyph stroked male-user">
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
			<li><a href="companies"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> companies</a></li>
			<li><a data-toggle="collapse" data-target="#demo"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg>edmessagemanager<span class="caret"></span></a>
               <ul id="demo" class="collapse" style="list-style-type: none;">
			     <li><a href="edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
				 <li><a href="edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>
			    </ul>
			  </li>
			<li class="active"><a href="person"><svg class="glyph stroked male user "><use xlink:href="#stroked-male-user"/></svg>Add Person</a></li>
			<li><a href="message"><svg class="glyph stroked empty message"><use xlink:href="#stroked-empty-message"/></svg>Add Message</a></li>			
			<li><a href="forms"><svg
						class="glyph stroked pencil">
						<use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
			<li><a href="panels"><svg class="glyph stroked app-window">
						<use xlink:href="#stroked-app-window"></use></svg> Alerts &amp; Panels</a></li>
			<li><a href="icons"><svg class="glyph stroked star">
						<use xlink:href="#stroked-star"></use></svg> Icons</a></li>
			<li class="parent "><!--  <a href="#"> <span
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
				<a href="#" value="Close this window" onclick="close_window();return false;"><svg class="glyph stroked cancel" style="float: right;width: 26px;height: 25px;color: red;"><use xlink:href="#stroked-cancel"/></svg></a>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header"></h3>
			</div>
		</div>
		<!--/.row-->

		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row table">
							<div class="col-md-3">Add Person Info</div>	
							<div class="col-md-9">
							<select id="selector1" class="js-data-example-ajax form-control"> <option id="serch" value="0" selected="selected">Company Id</option></select>
							</div>
						</div>						
					</div>
						<div class="panel-body">
						<form role="form" id="person">
							<div class="row table">
								<div class="col-md-3"><strong>Company Id</strong></div>
								<div class="col-md-3"><input type="text" id="cId" name="ref1" class="form-control" placeholder="Company Id" value="${companyId}"><span id="Id"></span></div>
								<div class="col-md-3"><strong>Type</strong></div>
								<div class="col-md-3"><input type="text" name="type" class="form-control" placeholder="Type" value="person" readonly="readonly"></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>First Name</strong></div>
								<div class="col-md-3"><input type="text" name="st1" class="form-control" placeholder="FirstName" value="" onchange="myvalidation.updateView($('#fName'))"><span id="fName"></span></div>
								<div class="col-md-3"><strong>Last Name</strong></div>
								<div class="col-md-3"><input type="text" name="st2" class="form-control" placeholder="LastName" value="" onchange="myvalidation.updateView($('#lName'))"><span id="lName"></span></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Gender<span></span></strong></div>
								<div class="col-md-3">
									<select name="st3" class="form-control">
										<option value="unknown">unknown</option>
										<option value="male">Male</option>
										<option value="female">Female</option>
									</select><span id="select"></span>
								</div>
								<div class="col-md-3"><strong>Designation</strong></div>
								<div class="col-md-3"><input type="text" name="st4" class="form-control" placeholder="Designation" value="" onchange="myvalidation.updateView($('#desiError'))"><span id="desiError"></span></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Email(s)</strong></div>
								<div class="col-md-3"><input type="text" name="st5" class="form-control" placeholder="Email" value="" onchange="myvalidation.updateView($('#emailError'))"><span id="emailError"></span></div>
								<div class="col-md-3"><strong>Mobile No.(s)</strong></div>
								<div class="col-md-3"><input type="text" name="st7" class="form-control" placeholder="Mobile" value="" onchange="myvalidation.updateView($('#mobileError'))"><span id="mobileError"></span></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Landline(s)</strong></div>
								<div class="col-md-8"><input type="text" name="st6" class="form-control" placeholder="Landline" value="" onchange="myvalidation.updateView($('#LandError'))"><span id="LandError"></span></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Twitter</strong></div>
								<div class="col-md-5"><input type="text" name="lt1" class="form-control" placeholder="Twitter" value=""></div>
								<div class="col-md-3"><input type="number" name="ln1" class="form-control" placeholder="Twitter" value=""></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Facebook</strong></div>
								<div class="col-md-5"><input type="text" name="lt2" class="form-control" placeholder="Facebook" value=""></div>
								<div class="col-md-3"><input type="number" name="ln2" class="form-control" placeholder="Facebook" value=""></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>LinkedIn</strong></div>
								<div class="col-md-5"><input type="text" name="lt3" class="form-control" placeholder="LinkedIn" value=""></div>
								<div class="col-md-3"><input type="number" name="ln3" class="form-control" placeholder="LinkedIn" value=""></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Whats App</strong></div>
								<div class="col-md-5"><input type="text" name="lt4" class="form-control" placeholder="Whats App" value=""></div>
								<div class="col-md-3"><input type="number" name="ln4" class="form-control" placeholder="Whats App" value=""></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Remark 1</strong></div>
								<div class="col-md-8"><input type="text" name="lt8" class="form-control" placeholder="Remark 1" value=""></div>														
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Remark 2</strong></div>
								<div class="col-md-8"><input type="text" name="lt9" class="form-control" placeholder="Remark 2" value=""></div>														
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Remark 3</strong></div>
								<div class="col-md-8"><input type="text" name="lt10" class="form-control" placeholder="Remark 3" value=""></div>														
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Language</strong></div>
								<div class="col-md-3"><input type="text" name="language" class="form-control" placeholder="Info Language" value="en"></div>
								<div class="col-md-3"><strong>Data Operator Id</strong></div>
								<div class="col-md-3"><input type="number" name="endUser.id" class="form-control" placeholder="End User Id" value="" onchange="myvalidation.updateView($('#dataError'))"><span id="dataError"></span></div>														
							</div>
							<div class="col-md-12">
								<button type="submit" id="btn" class="btn btn-primary">Save</button>								
							</div>
						
						</form>																							
					</div>					
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->		
	</div>
	<!--/.main-->

	<script
		src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap-datepicker.js"/>'></script>
		<script src='<c:url value="/web-resources/lumino/js/validation.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/validationforms.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/select2.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/person.js"/>'></script>
	
	
	
	
</body>

</html>