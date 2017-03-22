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
			<li><a href="/crmsoft/widgets"><svg class="glyph stroked calendar">
						<use xlink:href="#stroked-calendar"></use></svg> Widgets</a></li>
			<li><a href="/crmsoft/charts"><svg class="glyph stroked line-graph">
						<use xlink:href="#stroked-line-graph"></use></svg> Charts</a></li>
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
			<li class="active"><a href="forms"><svg
						class="glyph stroked pencil">
						<use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
			<li><a href="/crmsoft/panels"><svg class="glyph stroked app-window">
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
				<h3 class="page-header">${edContentData.title}  - (${edContentData.id})</h3>
			</div>
		</div>
		<!--/.row-->

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
									<label>Website</label> <input name="st4" class="form-control" placeholder="website" value="${edContentData.st4}">
								</div>
								<div class="form-group">
									<label>Data Source</label> <input name="st10" class="form-control" placeholder="Data Source" value="${edContentData.st10}">
								</div>									
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Category 1</label> <input name="category1" class="form-control" placeholder="Category1" value="${edContentData.category1}">
								</div>
								<div class="form-group">
									<label>Category 2</label> <input name="category2" class="form-control" placeholder="Sub Category" value="${edContentData.category2}">
								</div>
								<div class="form-group">
									<label>Category 3</label> <input name="category3" class="form-control" placeholder="Sub Sub Category" value="${edContentData.category3}">
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
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">Emails</div>
					<div class="panel-body">
						<div class="col-md-12">
							<form role="form" id="form3">
								<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
								<div class="form-group">
									<label>Emails</label> 
									<textarea name="st5" class="form-control" placeholder="Emails" cols="20"> ${ edContentData.st5} </textarea>
								</div>
								<div class="form-group">
									<label>Searched Emails</label> 
									<textarea name="lt13" class="form-control" placeholder="Searched Emails" cols="20"> ${ edContentData.lt13} </textarea>
								</div>
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form3')">Save</button>								
							</form>
						</div>
					</div>
				</div>
			</div><!-- /.col-->
			
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">Contact Numbers</div>
					<div class="panel-body">
						<div class="col-md-12">
							<form role="form" id="form4">
								<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
								<div class="form-group">
									<label>Landline</label> 
									<textarea name="st6" class="form-control" placeholder="Landline" cols="20"> ${ edContentData.st6} </textarea>
								</div>
								<div class="form-group">
									<label>Mobiles</label> 
									<textarea name="st7" class="form-control" placeholder="Mobile" cols="20"> ${ edContentData.st7} </textarea>
								</div>
								<div class="form-group">
									<label>Searched Contacts</label> 
									<textarea name="lt14"class="form-control" placeholder="Contacts" cols="20"> ${ edContentData.lt14} </textarea>
								</div>
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form4')">Save</button>								
							</form>
						</div>
					</div>
				</div>
			</div><!-- /.col-->
			
		</div><!-- /.row -->
		<!-- Email and Contacts -->
		
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">Social</div>
					<div class="panel-body">
						<div class="col-md-12">
							<form role="form" id="form5">
								<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
								<div class="form-group">
									<label>Twitter</label> 
									<textarea name="lt1" class="form-control" placeholder="twitter" cols="20"> ${ edContentData.lt1} </textarea>
								</div>
								<div class="form-group">
									<label>Facebook</label> 
									<textarea name="lt2" class="form-control" placeholder="facebook" cols="20"> ${ edContentData.lt2} </textarea>
								</div>
								<div class="form-group">
									<label>LinkedIn</label> 
									<textarea name="lt3" class="form-control" placeholder="linkedIn" cols="20"> ${ edContentData.lt3} </textarea>
								</div>
								<div class="form-group">
									<label>Other Social</label> 
									<textarea name="lt14" class="form-control" placeholder="Searched Emails" cols="20"> ${ edContentData.lt14} </textarea>
								</div>
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form5')">Save</button>								
							</form>
						</div>
					</div>
				</div>
			</div><!-- /.col-->
			
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">Address</div>
					<div class="panel-body">
						<div class="col-md-12">
							<form role="form" id="form6">
								<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
								<div class="form-group">
									<label>Address 1</label> 
									<textarea name="detail" class="form-control" placeholder="Landline" cols="20"> ${ edContentData.detail} </textarea>
								</div>
								<div class="form-group">
									<label>Address 2</label> 
									<textarea name="lt5" class="form-control" placeholder="Mobile" cols="20"> ${ edContentData.lt5} </textarea>
								</div>
								<div class="form-group">
									<label>City</label> 
									<input name="st1" type="text" class="form-control" placeholder="City" value="${ edContentData.st1}"/>  
								</div>
								<div class="form-group">
									<label>State</label> 
									<input name="st2" type="text" class="form-control" placeholder="State" value="${ edContentData.st2}"/>  
								</div>
								<div class="form-group">
									<label>Country</label> 
									<input name="st3" type="text" class="form-control" placeholder="Country" value="${ edContentData.st3}"/>  
								</div>
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form6')">Save</button>								
							</form>
						</div>
					</div>
				</div>
			</div><!-- /.col-->
			
		</div><!-- /.row -->
		
		
		
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Extra Info for : ${ edContentData.id }</div>
					<div class="panel-body">
						<form role="form" id="form7">
							<input type="hidden" name="id" class="form-control" value="${edContentData.id}">
							<div class="col-md-6">
								<div class="form-group">
									<label>Latitude</label> <input name="st11" class="form-control" placeholder="Title" value="${edContentData.st11}">
								</div>
								<div class="form-group">
									<label>Longitude</label> <input name="st12" class="form-control" placeholder="website" value="${edContentData.st12}">
								</div>
								<div class="form-group">
									<label>Google Place Id</label> <input name="st13" class="form-control" placeholder="Data Source" value="${edContentData.st13}">
								</div>									
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Scrapping Status</label> <input name="ln6" class="form-control" placeholder=" 1 if done, 2, 3 if error and 4 id web not found" value="${edContentData.ln6}">
								</div>
								<div class="form-group">
									<label>Updation Status</label> <input name="ln7" class="form-control" placeholder=" 1 if done" value="${edContentData.ln7}">
								</div>
								<div class="form-group">
									<label>Belong to Group </label> <input name="ln8" class="form-control" placeholder="for future use" value="${edContentData.ln8}">
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form7')">Save</button>								
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
							<div class="col-md-3">Campany Persons</div>
							<div class="col-md-5">Total Count = ${personDatas.size()}</div>
							<div class="col-md-3"><a class="btn btn-info" href="/crmsoft/person/${edContentData.id}" target="_blank" style="float: right; margin-right: -112px;">Add Person</a></div>							
						</div>						
					</div>
					<c:forEach var="personData" items="${personDatas}">
						<div class="panel-body" style="border:1px solid #ccc;">
						<form role="form" id="form8">
							<input type="hidden" name="id" value="${personData.id}"/>
							<div class="row table">
								<div class="col-md-3"><strong>Person Id</strong></div>
								<div class="col-md-8"><b>${personData.id}</b></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>First Name</strong></div>
								<div class="col-md-3"><input type="text" name="st1" class="form-control" placeholder="FirstName" value="${personData.st1}"></div>
								<div class="col-md-3"><strong>Last Name</strong></div>
								<div class="col-md-3"><input type="text" name="st2" class="form-control" placeholder="LastName" value="${personData.st2}"></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Gender</strong></div>
								<div class="col-md-3"><input type="text" name="st3" class="form-control" placeholder="Gender" value="${personData.st3}"></div>
								<div class="col-md-3"><strong>Designation</strong></div>
								<div class="col-md-3"><input type="text" name="st4" class="form-control" placeholder="Designation" value="${personData.st4}"></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Email(s)</strong></div>
								<div class="col-md-3"><input type="text" name="st5" class="form-control" placeholder="Email" value="${personData.st5}"></div>
								<div class="col-md-3"><strong>Mobile No.(s)</strong></div>
								<div class="col-md-3"><input type="text" name="st7" class="form-control" placeholder="Mobile" value="${personData.st7}"></div>
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Landline(s)</strong></div>
								<div class="col-md-8"><input type="text" name="st6" class="form-control" placeholder="Landline" value="${personData.st6}"></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Twitter</strong></div>
								<div class="col-md-5"><input type="text" name="lt1" class="form-control" placeholder="Twitter" value="${personData.lt1}"></div>
								<div class="col-md-3"><input type="number" name="ln1" class="form-control" placeholder="Twitter" value="${personData.ln1}"></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Facebook</strong></div>
								<div class="col-md-5"><input type="text" name="lt2" class="form-control" placeholder="Facebook" value="${personData.lt2}"></div>
								<div class="col-md-3"><input type="number" name="ln2" class="form-control" placeholder="Facebook" value="${personData.ln2}"></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>LinkedIn</strong></div>
								<div class="col-md-5"><input type="text" name="lt3" class="form-control" placeholder="LinkedIn" value="${personData.lt3}"></div>
								<div class="col-md-3"><input type="number" name="ln3" class="form-control" placeholder="LinkedIn" value="${personData.ln3}"></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Whats App</strong></div>
								<div class="col-md-5"><input type="text" name="lt4" class="form-control" placeholder="Whats App" value="${personData.lt4}"></div>
								<div class="col-md-3"><input type="number" name="ln4" class="form-control" placeholder="Whats App" value="${personData.ln4}"></div>							
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Remark 1</strong></div>
								<div class="col-md-8"><input type="text" name="lt8" class="form-control" placeholder="Remark 1" value="${personData.lt8}"></div>														
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Remark 2</strong></div>
								<div class="col-md-8"><input type="text" name="lt9" class="form-control" placeholder="Remark 2" value="${personData.lt9}"></div>														
							</div>
							<div class="row table">
								<div class="col-md-3"><strong>Remark 3</strong></div>
								<div class="col-md-8"><input type="text" name="lt10" class="form-control" placeholder="Remark 3" value="${personData.lt10}"></div>														
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form8')">Save</button>								
							</div>
						</form>																							
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
							<div class="col-md-5">Total Count = ${messageDatas.size()}</div>
							<div class="col-md-3"><a class="btn btn-info" href="/crmsoft/message/${edContentData.id}" target="_blank" style="float: right; margin-right: -121px;">Add Message</a></div>
						</div>						
					</div>
					<c:forEach var="messageData" items="${messageDatas}">
						<div class="panel-body" style="border:1px solid #ccc;">
						<form role="form" id="form9">
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
								<button type="submit" class="btn btn-primary" onclick="objectmapper('#form9')">Save</button>								
							</div>
						</form>																							
					</div>
					</c:forEach>
					
				</div>
			</div><!-- /.col-->
		</div><!-- /.row -->
		
	</div>
	<!--/.main-->

	<script
		src='<c:url value="/web-resources/lumino/js/jquery-1.11.1.min.js"/>'></script>
	<script
		src='<c:url value="/web-resources/lumino/js/bootstrap.min.js"/>'></script>
	<script src='<c:url value="/web-resources/lumino/js/main.js"/>'></script>
	<script>
	 var obj ={};
	 var urlprefix = "http://localhost:9090/crmsoft/";
	function objectmapper(DomId){
	    var app = $(DomId).find("[name]");
		for(var i=0; i<app.length; i++){
			var name=$(app[i]).attr("name");
			var value=$(app[i]).val();
		    var id= $(DomId).find('[name="id"]').val();
				obj[name]=value;		
		}
		console.log(obj);
		ajaxsender("contentdata/", "PATCH", function(dt){
			console.log(dt);
		},function(){
			alert("error request");
			
		},obj,id);
		
	}
	
		 $("form").on("click", function(event){
			event.preventDefault();
			//var value = $(this).serialize();
			
			/*  $.ajax({
				url : "http://localhost:9090/crmsoft/contentdata/update",
				method:"PATCH",
				contentType : "application/json",
				data : JSON.stringify(obj),
				dataType : "json",
				success : function(dt){
					console.log(dt);

				}				
			}); */
			 
			//console.log(value);	
		}); 
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
		      }else{
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