<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Tables</title>

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
			<li class="active"><a href="campaign"><svg class="glyph stroked hourglass"><use xlink:href="#stroked-hourglass"/></svg> campaign</a></li>
			<li><a href="companies"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> companies</a></li>
			<li><a data-toggle="collapse" data-target="#demo"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg>edmessagemanager<span class="caret"></span></a>
               <ul id="demo" class="collapse" style="list-style-type: none;">
			     <li><a href="edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
				 <li><a href="edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>
			    </ul>
			  </li>
			<li><a href="person"><svg class="glyph stroked male user "><use xlink:href="#stroked-male-user"/></svg>Add Person</a></li>
			<li><a href="message"><svg class="glyph stroked empty message"><use xlink:href="#stroked-empty-message"/></svg>Add Message</a></li>			
			<li><a href="forms"><svg class="glyph stroked pencil">
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
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Record Table</h1>
			</div>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-lg-6">Campaign Search</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-2">
								<input class="form-control" type="text" name="title"
									placeholder="campaign name here" onclick="" />
							</div>
							<div class="col-lg-2">
								<input class="form-control" type="text" name="count"
									placeholder="record count" disabled="disabled" />
							</div>
							<div class="col-lg-2">
								<select class="form-control" name="medium"
									onchange="page.searchType()">
									<option value="">None</option>
									<option value="sms">SMS</option>
									<option value="whatsapp">Whats App</option>
									<option value="email">Email</option>
									<option value="">All</option>
								</select>
							</div>
							<div class="col-lg-2"><input class="form-control" type="text" id="fpage" name="pageLength" placeholder="page size"/></div>
							<div class="col-lg-2"><input class="form-control" type="text" id="spage" name="pageTo" placeholder="page count"/></div>
							<div class="col-lg-2">
							<button class="btn btn-success btn-sm" id="ongo" style="margin-left: -20px">GO</button>
							<input class="btn btn-warning btn-sm" style="margin-left: -1px" type="button" value="Update" onclick="page.searchUpdate()" />
							<button type="button" class="btn btn-primary btn-sm" onclick="myFunction()">clear</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
					    <div class="col-lg-4">Campaign Data's : Page <label id="count1">${currentPage}</label> of ${totalPages} </div>
					        <div class="col-lg-5"><label id="result"></label></div>
							<div class="btn pull-right">Total Results : ${totalCount}</div>
					</div>
					<div class="panel-body">
						<table id="resultTable" class="table table-bordered table-hover"
							width="100%">
							<thead>
								<tr>
									<th style="max-width: 10%;">Mark</th>
									<th style="max-width: 10%;">Campaign Id</th>
									<th style="max-width: 20%;">Campaign Title</th>
									<th style="max-width: 20%;">Type</th>
									<th style="max-width: 20%;">Created On</th>
									<th style="max-width: 20%;">Events</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="i" items="${contentDatas}">
									<tr>
										<td style="max-width: 10%;"><input type="checkbox" value="<c:out value="${i.id}" />" /></td>
										<td style="max-width: 10%;"><c:out value="${i.id}" /></td>
										<td style="max-width: 20%;"><c:out value="${i.title}" /></td>
										<td style="max-width: 20%;"><c:out value="${i.st3}" /></td>
										<td style="max-width: 20%;"><c:out value="${i.receivedDate}" /></td>
										<td style="max-width: 20%;"><a class="btn btn-info" href="campaignedit/<c:out value="${i.id}" />">Edit</a> 
											<a class="btn btn-success" href="campaignview/<c:out value="${i.id}" />">View</a>
											<a class="btn btn-warning" href="/crmsoft/edmessagemanager?type=email&amp;campaign=<c:out value="${i.id}" />">Email</a>
											<a class="btn btn-primary" href="/crmsoft/edmessagemanager?type=sms&amp;campaign=<c:out value="${i.id}" />">Sms</a> 
											<a class="btn btn-danger" onclick="page.deleteL(<c:out value="${i.id}" />)">Del</a></td>
											
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--/.row-->
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



	</div>
	<!--/.main-->

	<script
		src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap-datepicker.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/campaign.js"/>'></script>
</body>

</html>
