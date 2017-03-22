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
		<!--container-fluid -->
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
			 <li class="active"><a data-toggle="collapse" data-target="#demo"><svg class="glyph stroked chain"><use xlink:href="#stroked-chain"/></svg>edmessagemanager<span class="caret"></span></a>
               <ul id="demo" class="collapse" style="list-style-type: none;">
			     <li><a href="edmessagemanager?type=email&amp"><svg class="glyph stroked email"><use xlink:href="#stroked-email"></use></svg> Email</a></li>
				 <li><a href="edmessagemanager?type=sms&amp"><svg class="glyph stroked two messages"><use xlink:href="#stroked-two-messages"/></svg> SMS</a></li>
			    </ul>
			  </li>
			<li><a href="person"><svg class="glyph stroked male user "><use xlink:href="#stroked-male-user"/></svg>Add Person</a></li>
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
	<!--sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">Its Message Manager</h3>
			</div>
		</div>
		<!--/.row-->
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<label class="form-control" id="typ">Type</label>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<select name="type" class="form-control" id="mySelect"
						onchange="adfe()">
						<option value="2">Choose</option>
						<option value="0">EMAIL</option>
						<option value="1">SMS</option>
					</select>
				</div>
			</div>
		</div>
		<!--row-->



		<div id="mymess" style="display: none;">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body" id="mess">
							<div class="col-md-12">
								<div class="form-group">

									<label style="margin-top: 10px">Event Information<span id="InfoError"></span></label>
									<button type="button" class="btn btn-primary" style="float: right; margin-bottom: 10px" data-toggle="modal" data-target="#myModal1">SETTINGS</button>
									 <input name="eventInfo" class="form-control" placeholder="event info" value="" onchange="myvalidation.updateView($('#InfoError'))">

								</div>
								<div class="form-group">
									<label>Sender Email (From)<span id="SenderError"></span></label> <input name="sender"
										class="form-control" placeholder="sender mail" value="" onchange="myvalidation.updateView($('#SenderError'))">
								</div>
								<div class="form-group">
									<label>Sender Name<span id="NameError"></span></label> <input name="senderName"
										class="form-control" placeholder="sender name" value="" onchange="myvalidation.updateView($('#NameError'))">
								</div>
								<div class="form-group">
									<label>Receiver Email (To)<span id="ReceiverError"></span></label> <input name="receiver"
										class="form-control" placeholder="receiver mail" value="" onchange="myvalidation.updateView($('#EmailError'))">
								</div>
								<div class="form-group">
									<label>Title (subject)<span id="titleError"></span></label> <input name="title"
										class="form-control" placeholder="Subject" value="" onchange="myvalidation.updateView($('#titleError'))">
								</div>
								<div class="form-group">

									<label>Content (body)<span id="bodyError"></span></label>
									<button type="button" id="bzn" class="btn btn-primary" style="float: right;" data-toggle="modal" data-target="#myModal">ADD Template</button>


									<div id="hfu" style="width: 100%; height: 200px; display: none;"></div>
									<textarea col="10" rows="10" name="content"
										class="form-control" placeholder="Body" onchange="myvalidation.updateView($('#bodyError'))"></textarea>
								</div>
								<div class="form-group row">
									<div class="col-lg-10">
										<label class="">Parameters (USE title.XXX and
											body.XXX)<span id="ParaError"></span></label>
									</div>
									<div class="col-lg-2">
										<button class="btn btn-success" id="addbtn">ADD</button>
									</div>
								</div>

								<div id="add455">
									<div class="form-group row">
										<div class="col-lg-5">
											<input class="form-control" name="key" placeholder="key" / onchange="myvalidation.updateView($('#ParaError'))">
										</div>
										<div class="col-lg-5">
											<input class="form-control" name="value" placeholder="value" / onchange="myvalidation.updateView($('#ParaError'))">
										</div>

										<div class="col-lg-2">
											<button class="btn btn-danger" onclick="mypage.deleteL(this)">DEL</button>
										</div>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-lg-10">
										<label class="">Send type<span id="typeError"></span></label>
									</div>
								</div>
								<div class="form-group row" id="myradio">
									<label class="radio-inline" id="now" style="margin-left: 12px">
										<input type="radio" name="isScheduled" value="0" onchange="myvalidation.updateView($('#typeError'))">Now
									</label> <label class="radio-inline" id="pick"
										style="margin-left: 12px" onchange="ademail()"> <input
										type="radio" name="isScheduled" value="1" onchange="myvalidation.updateView($('#typeError'))">Later
									</label>
									<div id="displaypick">
										<div class="form-group row" id="datapicker"
											style="margin-left: 18px; margin-top: 16px; display: none;">
											<div class="input-group date form_datetime col-md-5"
												data-date="1979-09-16T05:25:07Z"
												data-date-format="dd MM yyyy - HH:ii p"
												data-link-field="dtp_input1">
												<input class="form-control" size="16" type="text"
													name="deliveryDate" value="" readonly /> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
											<input type="hidden" id="dtp_input1" value="" /><br />
										</div>
									</div>
								</div>
								<div>&nbsp;</div>
								<div class="form-group">
									<label>USER ID*<span id="IdError"></span></label> <input type="number" name="userId"
										class="form-control" placeholder="USER ID" value=""
										placeholder="123456" onchange="myvalidation.updateView($('#IdError'))">
								</div>
								<div class="form-group">
									<label>USER NAME*<span id="NameError"></span></label> <input type="text" name="userName"
										class="form-control" placeholder="USER NAME" value="" 
										placeholder="NEERAJ" onchange="myvalidation.updateView($('#NameError'))">
								</div>
							</div>

							<div class="col-md-12">
								<button type="submit" id="sbtn" class="btn btn-primary">Send</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>






		<div id="mymess1" style="display: none;">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body" id="mess1">
							<div class="col-md-12">
								<div class="form-group">

									<label style="margin-top: 10px">Event Information<span id="InfoError1"></span></label> 
									<button type="button" class="btn btn-primary" style="float: right; margin-bottom: 10px" data-toggle="modal" data-target="#myModal2">SETTINGS</button>
									<input name="eventInfo"
										class="form-control" placeholder="event info" value="" >

								</div>
								<div class="form-group">
									<label>Receiver Number (To)<span id="NumberError"></span></label> <input type="text"
										name="receiver" class="form-control" placeholder="receiver #"
										value="">
								</div>
								<div class="form-group">
									<label>Content (text)<span id="textError"></span></label>
									<textarea col="10" rows="10" name="content"
										class="form-control" placeholder="Text"></textarea>
								</div>
								<div class="form-group row">
									<div class="col-lg-10">
										<label class="">Parameters (USE body.XXX)<span id="ParaError1"></span></label>
									</div>
									<div class="col-lg-2">
										<button class="btn btn-success" id="addbtn123">ADD</button>
									</div>
								</div>


								<div id="add123">
									<div class="form-group row">
										<div class="col-lg-5">
											<input class="form-control" name="key" placeholder="key" />
										</div>
										<div class="col-lg-5">
											<input class="form-control" name="value" placeholder="value" />
										</div>

										<div class="col-lg-2">
											<button class="btn btn-danger" onclick="mypage.deleteL(this)">DEL</button>
										</div>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-lg-10">
										<label class="">SMS type<span id="typeError1"></span></label>
									</div>
								</div>
								<div class="form-group row" id="myradio1">
									<label class="radio-inline" id="now1" style="margin-left: 12px">
										<input type="radio" name="isScheduled" value="0">Now
									</label> <label class="radio-inline" id="pick1"
										style="margin-left: 12px" onchange="ademail()"> <input
										type="radio" name="isScheduled" value="1">Later
									</label>
									<div id="displaypick1">
										<div class="form-group row" id="datapicker1"
											style="margin-left: 18px; margin-top: 16px; display: none;">
											<div class="input-group date form_datetime col-md-5"
												data-date="1979-09-16T05:25:07Z"
												data-date-format="dd MM yyyy - HH:ii p"
												data-link-field="dtp_input1">
												<input class="form-control" size="16" type="text"
													name="deliveryDate" value="" readonly /> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
											<input type="hidden" id="dtp_input1" value="" /><br />
										</div>
									</div>
								</div>

								<div>&nbsp;</div>
								<div class="form-group">
									<label>USER ID*<span id="IdError1"></span></label> <input type="number" name="userId"
										class="form-control" placeholder="USER ID" value=""
										placeholder="123456">
								</div>
								<div class="form-group">
									<label>USER NAME*<span id="NameError1"></span></label> <input type="text" name="userName"
										class="form-control" placeholder="USER NAME" value=""
										placeholder="NEERAJ">
								</div>

								<div class="col-md-12">
									<button type="submit" id="sbtn123" class="btn btn-primary">Send</button>
								</div>
							</div>
						</div>
					</div>
					<!--col-->
				</div>
				<!--row -->

			</div>
		</div>
		<!-- modal -->
		<!-- 	<div class="modal fade" id="myModal" data-backdrop="false"
			role="dialog">
			<div class="modal-dialog">
				<div class="modal-header"></div>
			</div>
		</div>
	</div> -->


		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Template</h4>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>


	<!-- modal email settings -->
	<div class="modal fade" id="myModal1" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Email Settings</h4>
				</div>
				<div class="modal-body" id="idemail">
					<div class="form-group">
						<label>HOST NAME</label> <input type="text" name="host"
							class="form-control" placeholder="Host Name" value="">
					</div>
					<div class="form-group">
						<label>PORT NUMBER</label> <input type="number" name="port"
							class="form-control" placeholder="Port number" value="">
					</div>
					<div class="form-group">
						<label>USER NAME</label> <input type="text" name="userName"
							class="form-control" placeholder="User Name" value="">
					</div>
					<div class="form-group">
						<label>PASSWORD*</label> <input type="password" name="password"
							class="form-control" placeholder="Password" value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="ebzn" class="btn btn-primary"
						style="float: left;" data-dismiss="modal">save</button>
				</div>
			</div>

		</div>
	</div>


	<!-- modal sms setting -->
	<div class="modal fade" id="myModal2" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">SMS Settings</h4>
				</div>
				<div class="modal-body" id="idsms">
					<div class="form-group">
						<label>HOST NAME</label> <input type="text" name="host"
							class="form-control" placeholder="Host Name" value="">
					</div>
					<div class="form-group">
						<label>POST NAME</label> <input type="text" name="post"
							class="form-control" placeholder="Post name" value="">
					</div>
					<div class="form-group">
						<label>USER NAME</label> <input type="text" name="username"
							class="form-control" placeholder="User Name" value="">
					</div>
					<div class="form-group">
						<label>PASSWORD*</label> <input type="password" name="password"
							class="form-control" placeholder="Password" value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="bznmodal2" class="btn btn-primary"
						style="float: left;" data-dismiss="modal">save</button>
				</div>
			</div>

		</div>
	</div>

	<!--main-->

	<script
		src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/validation.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/validationforms.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/edmessagemanagers.js"/>'></script>
</body>

</html>