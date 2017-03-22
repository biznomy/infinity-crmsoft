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
<link
	href='<c:url value="/web-resources/lumino/css/bootstrap-table.css"/>'
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
			<li class="active"><a href="tables"><svg
						class="glyph stroked table">
						<use xlink:href="#stroked-table"></use></svg> Tables</a></li>
			<li><a href="/crmsoft/campaign?type=campaign"><svg class="glyph stroked hourglass"><use xlink:href="#stroked-hourglass"/></svg> campaign</a></li>
			<li><a href="/crmsoft/companies?type=companies"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg> companies</a></li>
			<li><a href="/crmsoft/edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
			<li><a href="/crmsoft/edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>			
			<li><a href="forms"><svg class="glyph stroked pencil">
						<use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
			<li><a href="panels"><svg class="glyph stroked app-window">
						<use xlink:href="#stroked-app-window"></use></svg> Alerts &amp; Panels</a></li>
			<li><a href="icons"><svg class="glyph stroked star">
						<use xlink:href="#stroked-star"></use></svg> Icons</a></li>
			<li class="parent "><a href="#"> <span
					data-toggle="collapse" href="#sub-item-1"><svg
							class="glyph stroked chevron-down">
							<use xlink:href="#stroked-chevron-down"></use></svg></span> Dropdown
			</a>
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
				<h1 class="page-header">${contentData.title}</h1>
			</div>
		</div>
		<!--/.row-->


		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<form method="post" class="update" role="form">
						<c:if test="${not empty contentData}">
							<div class="panel-heading">Advanced Info for :
								${contentData.title}</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-3">Data Id :</div>
									<div class="col-lg-9">
										<input name="id" type="text" value="${contentData.id}"
											readonly="readonly">
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">City :</div>
									<div class="col-lg-9">${contentData.st1}</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Country :</div>
									<div class="col-lg-9">${contentData.st3}</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Category 1 :</div>
									<div class="col-lg-9">${contentData.category1}</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Category 2 :</div>
									<div class="col-lg-9">
										<input name="category2" type="text"
											value="${contentData.category2}"
											placeholder="Subject Category">
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Web Address:</div>
									<div class="col-lg-6">
										<input name="st4" type="url" value="${contentData.st4}"
											placeholder="Website Address">
									</div>
									<div class="col-lg-3">
										<a href="${contentData.st4}" target="_blank">Open Web</a>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Emails :</div>
									<div class="col-lg-9">
										<input name="st5" type="text" value="${contentData.st5}"
											placeholder="Email Address">
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Searched Emails :</div>
									<div class="col-lg-9">
										<textarea name="lt13" cols="60" rows="2"
											placeholder="Search Emails">${contentData.lt13}</textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Landline Number :</div>
									<div class="col-lg-9">
										<textarea name="st6" cols="60" rows="2" placeholder="Landline Number">${contentData.st6}</textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Mobile Number :</div>
									<div class="col-lg-9">
										<textarea name="st7" cols="60" rows="2" placeholder="Mobile Number">${contentData.st7}</textarea>
									</div>
								</div>
								<%-- <div class="row">
									<div class="col-lg-3">Searched Contacts :</div>
									<div class="col-lg-9">
										<textarea name="lt14" cols="60" rows="5"
											placeholder="Search Contacts">${contentData.lt14}</textarea>
									</div>
								</div> --%>
								<div class="row">
									<div class="col-lg-3">Searched Landline:</div>
									<div class="col-lg-9"><textarea cols="60" rows="2">,<c:forEach var="item" items="${landLine}"><c:out value="${item}"/>,</c:forEach></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Searched Mobile:</div>
									<div class="col-lg-9">
										<textarea cols="60" rows="2">,<c:forEach var="item" items="${mobile}"><c:out value="${item}"/>,</c:forEach></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Twitter :</div>
									<div class="col-lg-9">
										<textarea name="lt1" cols="60" rows="2" placeholder="Twitter Address">${contentData.lt1}</textarea>										
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Facebook :</div>
									<div class="col-lg-9">										
										<textarea name="lt2" cols="60" rows="2" placeholder="Facebook Address">${contentData.lt2}</textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">LinkedIn :</div>
									<div class="col-lg-9">
										<textarea name="lt3" cols="60" rows="2" placeholder="linked Address">${contentData.lt3}</textarea>		
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Youtube :</div>
									<div class="col-lg-9">
										<textarea name="lt15" cols="60" rows="6" placeholder="Youtube Address">${contentData.lt15}</textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Google Plus :</div>
									<div class="col-lg-9">
										<textarea name="lt16" cols="60" rows="2" placeholder="Google Plus Address">${contentData.lt16}</textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">Suggested Social:</div>
									<div class="col-lg-9">
										<c:forEach var="item" items="${sociallinks}"><div><c:out value="${item}"/></div></c:forEach>
									</div>
								</div>
							</div>
						</c:if>
					</form>
					<div class="row">
						<div class="col-lg-3"></div>
						<div class="col-lg-3">
							<button class="btn btn-info" id="update">Update</button>
						</div>
						<div class="col-lg-3">
							<button class="btn btn-info" id="next">Next</button>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!--/.row-->



	</div>
	<!--/.main-->

	<script
		src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/chart.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/chart-data.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/easypiechart.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/easypiechart-data.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap-datepicker.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap-table.js"/>'></script>
	<script>
		!function($) {
			$(document)
					.on(
							"click",
							"ul.nav li.parent > a > span.icon",
							function() {
								$(this).find('em:first').toggleClass(
										"glyphicon-minus");
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

		$(document).on(
				"click",
				"#update",
				function() {
					$.post("http://192.168.1.224:9090/crmsoft/update", $(".update")
							.serialize());
				});

		$(document).on("click", "#next", function() {
			var next = location.search.split('next=')[1];
			var a = parseInt(next);
			window.location.search = "?next=" + (++a);
		});
	</script>
</body>

</html>
