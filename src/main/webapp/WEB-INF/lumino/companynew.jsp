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
			<li><a href="campaign"><svg
						class="glyph stroked hourglass">
						<use xlink:href="#stroked-hourglass" /></svg> campaign</a></li>
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
			<li class="parent ">
				<!--  <a href="#"> <span
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
				</ul>
			</li>
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
				<h3 class="page-header">Add New Company</h3>
			</div>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<form role="form" id="form1">
							<input type="hidden" name="language" class="form-control"
								value="en"> <input type="hidden" name="type"
								class="form-control" value="Company">
							<div class="col-md-6">
								<div class="form-group">
									<label>Title <span id="titleError"></span></label> <input name="title" class="form-control"
										placeholder="Title" value="" required="true" onchange="myvalidation.updateView($('#titleError'))" />																				
								</div>
								<div class="form-group">
									<label>Website</label> <input name="st4" class="form-control"
										placeholder="website" value="">
								</div>
								<div class="form-group">
									<label>Data Source<span id="DataError"></label> <input name="st10"
										class="form-control" placeholder="Data Source" value="">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Category 1<span id="CategoryError"></span></label> <input name="category1"
										class="form-control" placeholder="Category1"
										value="${edContentData.category1}" onchange="myvalidation.updateView($('#CategoryError'))">
								</div>
								<div class="form-group">
									<label>Category 2</label> <input name="category2"
										class="form-control" placeholder="Sub Category"
										value="${edContentData.category2}">
								</div>
								<div class="form-group">
									<label>Category 3</label> <input name="category3"
										class="form-control" placeholder="Sub Sub Category"
										value="${edContentData.category3}">
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label>Emails<span id="EmailError"></span></label>
									<textarea name="st5" class="form-control" placeholder="Emails"
										cols="20" required="true" validType="email" onchange="myvalidation.updateView($('#EmailError'))"> ${ edContentData.st5} </textarea>
								</div>
								<div id="myemail"></div>
								<div class="form-group">
									<label>Landline<span id="LandlineError"></span></label>
									<textarea name="st6" class="form-control"
										placeholder="Landline" cols="20" required="true" onchange="myvalidation.updateView($('#LandlineError'))"> ${ edContentData.st6} </textarea>
								</div>
								<div class="form-group">
									<label>Mobiles<span id="MobilesError"></span></label>
									<textarea name="st7" class="form-control" placeholder="Mobile"
										cols="20"  onchange="myvalidation.updateView($('#MobilesError'))"> ${ edContentData.st7} </textarea>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Address 1<span id="Add1Error"></span></label>
									<textarea name="detail" class="form-control"
										placeholder="Landline" cols="20" onchange="myvalidation.updateView($('#AddressError'))"> ${ edContentData.detail} </textarea>
								</div>
								<div class="form-group">
									<label>Address 2</label>
									<textarea name="lt5" class="form-control" placeholder="Mobile"
										cols="20" required> ${ edContentData.lt5} </textarea>
								</div>
								<div class="form-group">
									<label>City</label>
									<textarea name="st1" type="text" class="form-control"
										placeholder="City" cols="20" onchange="myvalidation.updateView($('#CityError'))">${ edContentData.st1} </textarea>
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label>Twitter</label>
									<textarea name="lt1" class="form-control" placeholder="twitter"
										cols="20"> ${ edContentData.lt1} </textarea>
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label>Facebook</label>
									<textarea name="lt2" class="form-control"
										placeholder="facebook" cols="20"> ${ edContentData.lt2} </textarea>
								</div>
							</div>

							<div class="col-md-12">
								<button type="submit" id="btn" class="btn btn-primary" value="Submit">Save</button>
							</div>

						</form>
					</div>
				</div>
			</div>
			<!-- /.col-->
		</div>
		<!-- /.row -->





		<!--/.main-->

		<script
			src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
		<script
			src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
		<script src='<c:url value="/web-resources/lumino/js/validation.js"/>'></script>
		<script src='<c:url value="/web-resources/lumino/js/validationforms.js"/>'></script>
		<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
		<script src='<c:url value="/web-resources/lumino/js/companynew.js"/>'></script>
		
</body>

</html>