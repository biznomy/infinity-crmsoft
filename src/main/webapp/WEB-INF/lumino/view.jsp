<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Forms</title>

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
			<li><a href="/crmsoft/campaign"><svg class="glyph stroked hourglass"><use xlink:href="#stroked-hourglass"/></svg> campaign</a></li>
			<li><a href="/crmsoft/companies"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> companies</a></li>
			<li><a data-toggle="collapse" data-target="#demo"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg>edmessagemanager<span class="caret"></span></a>
               <ul id="demo" class="collapse" style="list-style-type: none;">
			     <li style="width: 100%; margin-top: 10px"><a href="/crmsoft/edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
				 <li style="width: 100%; margin-top: 10px"><a href="/crmsoft/edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>
			    </ul>
			  </li>
			<li><a href="/crmsoft/person"><svg class="glyph stroked male user "><use xlink:href="#stroked-male-user"/></svg>Add Person</a></li>
			<li><a href="/crmsoft/message"><svg class="glyph stroked empty message"><use xlink:href="#stroked-empty-message"/></svg>Add Message</a></li>
			<li class="active"><a href="forms"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
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
				<div class="panel panel-default">
					<div class="panel-heading">${edContentData.id}</div>
					<div class="panel-body">
						<div class="row table">
							<div class="col-md-4">Data Scrap Status</div>
							<div class="col-md-8">${edContentData.ln6}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Data Updated Status</div>
							<div class="col-md-8">${edContentData.ln7}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Remark 1</div>
							<div class="col-md-8">${edContentData.lt8}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Remark 2</div>
							<div class="col-md-8">${edContentData.lt9}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Remark 3</div>
							<div class="col-md-8">${edContentData.lt10}</div>
						</div>
						
					</div>					
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
		<div class="row">
			<div class="col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">General Info</div>
					<div class="panel-body">
						<div class="row table">
							<div class="col-md-4">Title</div>
							<div class="col-md-8">${edContentData.title}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Address</div>
							<div class="col-md-8">${edContentData.detail}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Address 2</div>
							<div class="col-md-8">${edContentData.lt5}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">City</div>
							<div class="col-md-8">${edContentData.st1}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">State</div>
							<div class="col-md-8">${edContentData.st2}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Country</div>
							<div class="col-md-8">${edContentData.st3}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Website</div>
							<div class="col-md-8"><a href="${edContentData.st4}" target="_blank">${edContentData.st4}</a></div>
						</div>
						<div class="row table">
							<div class="col-md-4">Category1</div>
							<div class="col-md-8">${edContentData.category1}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Category2</div>
							<div class="col-md-8">${edContentData.category2}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Category3</div>
							<div class="col-md-8">${edContentData.category3}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Source</div>
							<div class="col-md-8">${edContentData.st10}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Latitude</div>
							<div class="col-md-8">${edContentData.st11}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Longitude</div>
							<div class="col-md-8">${edContentData.st12}</div>
						</div>
						
					</div>
				</div>
			</div><!-- /.col-->
			<div class="col-lg-8">
				<div class="panel panel-default">
					<div class="panel-heading">Campain Info</div>
					<div class="panel-body">
						<div class="row table">
							<div class="col-md-4">Email(s)</div>
							<div class="col-md-8">${edContentData.st5}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Landline(s)</div>
							<div class="col-md-8">${edContentData.st6}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Mobile(s)</div>
							<div class="col-md-8">${edContentData.st7}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Link(Comp.)</div>
							<div class="col-md-8">${edContentData.st8}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Twitter</div>
							<div class="col-md-8">${edContentData.lt1}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Facebook</div>
							<div class="col-md-8">${edContentData.lt2}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">LinkedIn</div>
							<div class="col-md-8">${edContentData.lt3}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">WhatsApp</div>
							<div class="col-md-8">${edContentData.lt4}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Twitter Status</div>
							<div class="col-md-8">${edContentData.ln1}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Facebook Status</div>
							<div class="col-md-8">${edContentData.ln2}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">Linked Status</div>
							<div class="col-md-8">${edContentData.ln3}</div>
						</div>
						<div class="row table">
							<div class="col-md-4">WhatsApp Status</div>
							<div class="col-md-8">${edContentData.ln4}</div>
						</div>
					</div>
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row table">
							<div class="col-md-3">Campany Persons</div>
							<div class="col-md-8">Total Count = ${personDatas.size()}</div>
						</div>						
					</div>
					<c:forEach var="personData" items="${personDatas}">
						<div class="panel-body" style="border:1px solid #ccc;">
						<div class="row table">
							<div class="col-md-3"><strong>Person Id</strong></div>
							<div class="col-md-8"><b>${personData.id}</b></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>First Name</strong></div>
							<div class="col-md-3">${personData.st1}</div>
							<div class="col-md-3"><strong>Last Name</strong></div>
							<div class="col-md-3">${personData.st2}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Gender</strong></div>
							<div class="col-md-3">${personData.st3}</div>
							<div class="col-md-3"><strong>Designation</strong></div>
							<div class="col-md-3">${personData.st4}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Email(s)</strong></div>
							<div class="col-md-3">${personData.st5}</div>
							<div class="col-md-3"><strong>Mobile No.(s)</strong></div>
							<div class="col-md-3">${personData.st7}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Landline(s)</strong></div>
							<div class="col-md-8">${personData.st6}</div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Twitter</strong></div>
							<div class="col-md-5">${personData.lt1}</div>
							<div class="col-md-3">${personData.ln1}</div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Facebook</strong></div>
							<div class="col-md-5">${personData.lt2}</div>
							<div class="col-md-3">${personData.ln2}</div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>LinkedIn</strong></div>
							<div class="col-md-5">${personData.lt3}</div>
							<div class="col-md-3">${personData.ln3}</div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Whats App</strong></div>
							<div class="col-md-5">${personData.lt4}</div>
							<div class="col-md-3">${personData.ln4}</div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 1</strong></div>
							<div class="col-md-8">${personData.lt8}</div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 2</strong></div>
							<div class="col-md-8">${personData.lt9}</div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 3</strong></div>
							<div class="col-md-8">${personData.lt10}</div>														
						</div>																							
					</div>
					</c:forEach>
					
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
						</div>						
					</div>
					<c:forEach var="messageData" items="${messageDatas}">
						<div class="panel-body" style="border:1px solid #ccc;">
						<div class="row table">
							<div class="col-md-3"><strong>Message Id</strong></div>
							<div class="col-md-8"><b>${messageData.id}</b></div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Status</strong></div>
							<div class="col-md-3">${messageData.st2}</div>
							<div class="col-md-3"><strong>Medium</strong></div>
							<div class="col-md-3">${messageData.st3}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Contact Date</strong></div>
							<div class="col-md-3">${messageData.date2}</div>
							<div class="col-md-3"><strong>Ref Type</strong></div>
							<div class="col-md-3">${messageData.st5}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Touch Again</strong></div>
							<div class="col-md-3">${messageData.date1}</div>
							<div class="col-md-3"><strong>Aproach By</strong></div>
							<div class="col-md-3">${messageData.st4}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Text 1</strong></div>
							<div class="col-md-3">${messageData.lt1}</div>
							<div class="col-md-3"><strong>Text 2</strong></div>
							<div class="col-md-3">${messageData.lt2}</div>
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Media 1</strong></div>
							<div class="col-md-8">${messageData.media1}</div>							
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Media 2</strong></div>
							<div class="col-md-8">${messageData.media2}</div>
														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 1</strong></div>
							<div class="col-md-8">${messageData.lt8}</div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 2</strong></div>
							<div class="col-md-8">${messageData.lt9}</div>														
						</div>
						<div class="row table">
							<div class="col-md-3"><strong>Remark 3</strong></div>
							<div class="col-md-8">${messageData.lt10}</div>														
						</div>
																													
					</div>
					</c:forEach>
					
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->	
		
	</div><!--/.main-->

	<script src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/chart.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/chart-data.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/easypiechart.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/easypiechart-data.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/bootstrap-datepicker.js"/>'></script>
	<script>
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	
</body>

</html>