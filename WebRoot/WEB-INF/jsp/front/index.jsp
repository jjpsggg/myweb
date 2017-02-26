<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>User Profile Page - Ace Admin</title>

	<meta name="description" content="3 styles with inline editable feature" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="/static/ace/css/bootstrap.css" />
	<link rel="stylesheet" href="/static/ace/css/font-awesome.css" />

	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="/static/ace/css/jquery-ui.custom.css" />
	<link rel="stylesheet" href="/static/ace/css/jquery.gritter.css" />
	<link rel="stylesheet" href="/static/ace/css/select2.css" />
	<link rel="stylesheet" href="/static/ace/css/datepicker.css" />
	<link rel="stylesheet" href="/static/ace/css/bootstrap-editable.css" />

	<!-- text fonts -->
	<link rel="stylesheet" href="/static/ace/css/ace-fonts.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="/static/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="/static/ace/css/ace-part2.css" class="ace-main-stylesheet" />
	<![endif]-->

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="/static/ace/css/ace-ie.css" />
	<![endif]-->

	<!-- inline styles related to this page -->

	<!-- ace settings handler -->
	<script src="/static/ace/js/ace-extra.js"></script>

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="/static/ace/js/html5shiv.js"></script>
	<script src="/static/ace/js/respond.js"></script>
	<![endif]-->
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
	<div id="sidebar"> </div>
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<!-- #section:basics/sidebar.mobile.toggle -->
		<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>
		</button>


		<!-- #section:basics/navbar.dropdown -->
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="grey">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-tasks"></i>
						<span class="badge badge-grey">4</span>
					</a>

					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="ace-icon fa fa-check"></i>
							4 Tasks to complete
						</li>

						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Software Update</span>
											<span class="pull-right">65%</span>
										</div>

										<div class="progress progress-mini">
											<div style="width:65%" class="progress-bar"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Hardware Upgrade</span>
											<span class="pull-right">35%</span>
										</div>

										<div class="progress progress-mini">
											<div style="width:35%" class="progress-bar progress-bar-danger"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Unit Testing</span>
											<span class="pull-right">15%</span>
										</div>

										<div class="progress progress-mini">
											<div style="width:15%" class="progress-bar progress-bar-warning"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Bug Fixes</span>
											<span class="pull-right">90%</span>
										</div>

										<div class="progress progress-mini progress-striped active">
											<div style="width:90%" class="progress-bar progress-bar-success"></div>
										</div>
									</a>
								</li>
							</ul>
						</li>

						<li class="dropdown-footer">
							<a href="#">
								See tasks with details
								<i class="ace-icon fa fa-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>

				<li class="purple">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-bell icon-animated-bell"></i>
						<span class="badge badge-important">8</span>
					</a>

					<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="ace-icon fa fa-exclamation-triangle"></i>
							8 Notifications
						</li>

						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar navbar-pink">
								<li>
									<a href="#">
										<div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
														New Comments
													</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<i class="btn btn-xs btn-primary fa fa-user"></i>
										Bob just signed up as an editor ...
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
														New Orders
													</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
														Followers
													</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li>
							</ul>
						</li>

						<li class="dropdown-footer">
							<a href="#">
								See all notifications
								<i class="ace-icon fa fa-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>

				<li class="green">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
						<span class="badge badge-success">5</span>
					</a>

					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="ace-icon fa fa-envelope-o"></i>
							5 Messages
						</li>

						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="#" class="clearfix">
										<img src="/static/ace/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
									</a>
								</li>

								<li>
									<a href="#" class="clearfix">
										<img src="/static/ace/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Susan:</span>
														Vestibulum id ligula porta felis euismod ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>20 minutes ago</span>
													</span>
												</span>
									</a>
								</li>

								<li>
									<a href="#" class="clearfix">
										<img src="/static/ace/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Bob:</span>
														Nullam quis risus eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>3:15 pm</span>
													</span>
												</span>
									</a>
								</li>

								<li>
									<a href="#" class="clearfix">
										<img src="/static/ace/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Kate:</span>
														Ciao sociis natoque eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>1:33 pm</span>
													</span>
												</span>
									</a>
								</li>

								<li>
									<a href="#" class="clearfix">
										<img src="/static/ace/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Fred:</span>
														Vestibulum id penatibus et auctor  ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>10:09 am</span>
													</span>
												</span>
									</a>
								</li>
							</ul>
						</li>

						<li class="dropdown-footer">
							<a href="inbox.html">
								See all messages
								<i class="ace-icon fa fa-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>

				<!-- #section:basics/navbar.user_menu -->
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="/static/ace/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>Welcome,</small>
									Jason
								</span>

						<i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="#">
								<i class="ace-icon fa fa-cog"></i>
								Settings
							</a>
						</li>

						<li>
							<a href="index.jsp">
								<i class="ace-icon fa fa-user"></i>
								Profile
							</a>
						</li>

						<li class="divider"></li>

						<li>
							<a href="#">
								<i class="ace-icon fa fa-power-off"></i>
								Logout
							</a>
						</li>
					</ul>
				</li>

				<!-- /section:basics/navbar.user_menu -->
			</ul>
		</div>

		<!-- /section:basics/navbar.dropdown -->
	</div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">

			<!-- /section:basics/content.breadcrumbs -->
			<div class="page-content">
				<!-- #section:settings.box -->
				<div class="ace-settings-container " id="ace-settings-container"  >
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="ace-icon fa fa-cog bigger-130"></i>
					</div>

					<div class="ace-settings-box clearfix" id="ace-settings-box">
						<div class="pull-left width-50">
							<!-- #section:settings.skins -->
							<div class="ace-settings-item">
								<div class="pull-left">
									<select id="skin-colorpicker" class="hide">
										<option data-skin="no-skin" value="#438EB9">#438EB9</option>
										<option data-skin="skin-1" value="#222A2D">#222A2D</option>
										<option data-skin="skin-2" value="#C6487E">#C6487E</option>
										<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
									</select>
								</div>
								<span>&nbsp; Choose Skin</span>
							</div>

							<!-- /section:settings.skins -->

							<!-- #section:settings.navbar -->
							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
								<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
							</div>

							<!-- /section:settings.navbar -->

							<!-- #section:settings.sidebar -->
							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
								<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
							</div>

							<!-- /section:settings.sidebar -->

							<!-- #section:settings.breadcrumbs -->
							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
								<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
							</div>

							<!-- /section:settings.breadcrumbs -->

							<!-- #section:settings.rtl -->
							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
								<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
							</div>

							<!-- /section:settings.rtl -->

							<!-- #section:settings.container -->
							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
								<label class="lbl" for="ace-settings-add-container">
									Inside
									<b>.container</b>
								</label>
							</div>

							<!-- /section:settings.container -->
						</div><!-- /.pull-left -->

						<div class="pull-left width-50">
							<!-- #section:basics/sidebar.options -->
							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
								<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
							</div>

							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
								<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
							</div>

							<div class="ace-settings-item">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
								<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
							</div>

							<!-- /section:basics/sidebar.options -->
						</div><!-- /.pull-left -->
					</div><!-- /.ace-settings-box -->
				</div><!-- /.ace-settings-container -->

				<div class="row">
					<div class="col-xs-12">

						<div class="hr dotted"></div>

						<div>
							<div id="user-profile-1" class="user-profile row">
								<div class="col-xs-12 col-sm-3 center">
									<div>
										<!-- #section:pages/profile.picture -->
												<span class="profile-picture">
													<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="/static/ace/avatars/profile-pic.jpg" />
												</span>

										<!-- /section:pages/profile.picture -->
										<div class="space-4"></div>

										<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
											<div class="inline position-relative">
												<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
													<i class="ace-icon fa fa-circle light-green"></i>
													&nbsp;
													<span class="white">Alex M. Doe</span>
												</a>

												<ul class="align-left dropdown-menu dropdown-caret dropdown-lighter">
													<li class="dropdown-header"> Change Status </li>

													<li>
														<a href="#">
															<i class="ace-icon fa fa-circle green"></i>
															&nbsp;
															<span class="green">Available</span>
														</a>
													</li>

													<li>
														<a href="#">
															<i class="ace-icon fa fa-circle red"></i>
															&nbsp;
															<span class="red">Busy</span>
														</a>
													</li>

													<li>
														<a href="#">
															<i class="ace-icon fa fa-circle grey"></i>
															&nbsp;
															<span class="grey">Invisible</span>
														</a>
													</li>
												</ul>
											</div>
										</div>
									</div>

									<div class="space-6"></div>

									<!-- #section:pages/profile.contact -->
									<div class="profile-contact-info">
										<div class="profile-contact-links align-left">
											<a href="#" class="btn btn-link">
												<i class="ace-icon fa fa-plus-circle bigger-120 green"></i>
												Add as a friend
											</a>

											<a href="#" class="btn btn-link">
												<i class="ace-icon fa fa-envelope bigger-120 pink"></i>
												Send a message
											</a>

											<a href="#" class="btn btn-link">
												<i class="ace-icon fa fa-globe bigger-125 blue"></i>
												www.alexdoe.com
											</a>
										</div>

										<div class="space-6"></div>

										<div class="profile-social-links align-center">
											<a href="#" class="tooltip-info" title="" data-original-title="Visit my Facebook">
												<i class="middle ace-icon fa fa-facebook-square fa-2x blue"></i>
											</a>

											<a href="#" class="tooltip-info" title="" data-original-title="Visit my Twitter">
												<i class="middle ace-icon fa fa-twitter-square fa-2x light-blue"></i>
											</a>

											<a href="#" class="tooltip-error" title="" data-original-title="Visit my Pinterest">
												<i class="middle ace-icon fa fa-pinterest-square fa-2x red"></i>
											</a>
										</div>
									</div>

									<!-- /section:pages/profile.contact -->
									<div class="hr hr12 dotted"></div>

									<!-- #section:custom/extra.grid -->
									<div class="clearfix">
										<div class="grid2">
											<span class="bigger-175 blue">25</span>

											<br />
											Followers
										</div>

										<div class="grid2">
											<span class="bigger-175 blue">12</span>

											<br />
											Following
										</div>
									</div>

									<!-- /section:custom/extra.grid -->
									<div class="hr hr16 dotted"></div>
								</div>

								<div class="col-xs-12 col-sm-9">
									<div class="center">
										<a href="/front/goApply">
												<span class="btn btn-app btn-sm btn-light no-hover">
													<span class="line-height-1 bigger-170 blue"> 1,411 </span>

													<br />
													<span class="line-height-1 smaller-90"> Views </span>
												</span>
										</a>
										<a href="/front/order">
												<span class="btn btn-app btn-sm btn-yellow no-hover">
													<span class="line-height-1 bigger-170"> 32 </span>

													<br />
													<span class="line-height-1 smaller-90"> Followers </span>
												</span>
										</a>
												<span class="btn btn-app btn-sm btn-pink no-hover">
													<span class="line-height-1 bigger-170"> 4 </span>

													<br />
													<span class="line-height-1 smaller-90"> Projects </span>
												</span>

												<span class="btn btn-app btn-sm btn-grey no-hover">
													<span class="line-height-1 bigger-170"> 23 </span>

													<br />
													<span class="line-height-1 smaller-90"> Reviews </span>
												</span>

												<span class="btn btn-app btn-sm btn-success no-hover">
													<span class="line-height-1 bigger-170"> 7 </span>

													<br />
													<span class="line-height-1 smaller-90"> Albums </span>
												</span>

												<span class="btn btn-app btn-sm btn-primary no-hover">
													<span class="line-height-1 bigger-170"> 55 </span>

													<br />
													<span class="line-height-1 smaller-90"> Contacts </span>
												</span>
									</div>

									<div class="space-12"></div>

									<!-- #section:pages/profile.info -->
									<div class="profile-user-info profile-user-info-striped">
										<div class="profile-info-row">
											<div class="profile-info-name"> Username </div>

											<div class="profile-info-value">
												<span class="editable" id="username">alexdoe</span>
											</div>
										</div>

										<div class="profile-info-row">
											<div class="profile-info-name"> Location </div>

											<div class="profile-info-value">
												<i class="fa fa-map-marker light-orange bigger-110"></i>
												<span class="editable" id="country">Netherlands</span>
												<span class="editable" id="city">Amsterdam</span>
											</div>
										</div>

										<div class="profile-info-row">
											<div class="profile-info-name"> Age </div>

											<div class="profile-info-value">
												<span class="editable" id="age">38</span>
											</div>
										</div>

										<div class="profile-info-row">
											<div class="profile-info-name"> Joined </div>

											<div class="profile-info-value">
												<span class="editable" id="signup">2010/06/20</span>
											</div>
										</div>

										<div class="profile-info-row">
											<div class="profile-info-name"> Last Online </div>

											<div class="profile-info-value">
												<span class="editable" id="login">3 hours ago</span>
											</div>
										</div>

										<div class="profile-info-row">
											<div class="profile-info-name"> About Me </div>

											<div class="profile-info-value">
												<span class="editable" id="about">Editable as WYSIWYG</span>
											</div>
										</div>
									</div>



									<div class="hr hr2 hr-double"></div>

									<div class="space-6"></div>

									<div class="center">
										<button type="button" class="btn btn-sm btn-primary btn-white btn-round">
											<i class="ace-icon fa fa-rss bigger-150 middle orange2"></i>
											<span class="bigger-110">View more activities</span>

											<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
										</button>
									</div>

									<div id="user-profile-2" class="user-profile">
										<div class="tabbable">
											<ul class="nav nav-tabs padding-18">
												<li class="active">
													<a data-toggle="tab" href="#home">
														<i class="green ace-icon fa fa-user bigger-120"></i>
														Profile
													</a>
												</li>

												<li>
													<a data-toggle="tab" href="#feed">
														<i class="orange ace-icon fa fa-rss bigger-120"></i>
														Activity Feed
													</a>
												</li>

												<li>
													<a data-toggle="tab" href="#friends">
														<i class="blue ace-icon fa fa-users bigger-120"></i>
														Friends
													</a>
												</li>

												<li>
													<a data-toggle="tab" href="#pictures">
														<i class="pink ace-icon fa fa-picture-o bigger-120"></i>
														Pictures
													</a>
												</li>
											</ul>

											<div class="tab-content no-border padding-24">
												<div id="home" class="tab-pane in active">
													<div class="row">
														<div class="col-xs-12 col-sm-3 center">
															<span class="profile-picture">
																<img class="editable img-responsive" alt="Alex's Avatar" id="avatar2" src="/static/ace/avatars/profile-pic.jpg" />
															</span>

															<div class="space space-4"></div>

															<a href="#" class="btn btn-sm btn-block btn-success">
																<i class="ace-icon fa fa-plus-circle bigger-120"></i>
																<span class="bigger-110">Add as a friend</span>
															</a>

															<a href="#" class="btn btn-sm btn-block btn-primary">
																<i class="ace-icon fa fa-envelope-o bigger-110"></i>
																<span class="bigger-110">Send a message</span>
															</a>
														</div><!-- /.col -->

														<div class="col-xs-12 col-sm-9">
															<h4 class="blue">
																<span class="middle">Alex M. Doe</span>

																<span class="label label-purple arrowed-in-right">
																	<i class="ace-icon fa fa-circle smaller-80 align-middle"></i>
																	online
																</span>
															</h4>

															<div class="profile-user-info">
																<div class="profile-info-row">
																	<div class="profile-info-name"> Username </div>

																	<div class="profile-info-value">
																		<span>alexdoe</span>
																	</div>
																</div>

																<div class="profile-info-row">
																	<div class="profile-info-name"> Location </div>

																	<div class="profile-info-value">
																		<i class="fa fa-map-marker light-orange bigger-110"></i>
																		<span>Netherlands</span>
																		<span>Amsterdam</span>
																	</div>
																</div>

																<div class="profile-info-row">
																	<div class="profile-info-name"> Age </div>

																	<div class="profile-info-value">
																		<span>38</span>
																	</div>
																</div>

																<div class="profile-info-row">
																	<div class="profile-info-name"> Joined </div>

																	<div class="profile-info-value">
																		<span>2010/06/20</span>
																	</div>
																</div>

																<div class="profile-info-row">
																	<div class="profile-info-name"> Last Online </div>

																	<div class="profile-info-value">
																		<span>3 hours ago</span>
																	</div>
																</div>
															</div>

															<div class="hr hr-8 dotted"></div>

															<div class="profile-user-info">
																<div class="profile-info-row">
																	<div class="profile-info-name"> Website </div>

																	<div class="profile-info-value">
																		<a href="#" target="_blank">www.alexdoe.com</a>
																	</div>
																</div>

																<div class="profile-info-row">
																	<div class="profile-info-name">
																		<i class="middle ace-icon fa fa-facebook-square bigger-150 blue"></i>
																	</div>

																	<div class="profile-info-value">
																		<a href="#">Find me on Facebook</a>
																	</div>
																</div>

																<div class="profile-info-row">
																	<div class="profile-info-name">
																		<i class="middle ace-icon fa fa-twitter-square bigger-150 light-blue"></i>
																	</div>

																	<div class="profile-info-value">
																		<a href="#">Follow me on Twitter</a>
																	</div>
																</div>
															</div>
														</div><!-- /.col -->
													</div><!-- /.row -->

													<div class="space-20"></div>

													<div class="row">
														<div class="col-xs-12 col-sm-6">
															<div class="widget-box transparent">
																<div class="widget-header widget-header-small">
																	<h4 class="widget-title smaller">
																		<i class="ace-icon fa fa-check-square-o bigger-110"></i>
																		Little About Me
																	</h4>
																</div>

																<div class="widget-body">
																	<div class="widget-main">
																		<p>
																			My job is mostly lorem ipsuming and dolor sit ameting as long as consectetur adipiscing elit.
																		</p>
																		<p>
																			Sometimes quisque commodo massa gets in the way and sed ipsum porttitor facilisis.
																		</p>
																		<p>
																			The best thing about my job is that vestibulum id ligula porta felis euismod and nullam quis risus eget urna mollis ornare.
																		</p>
																		<p>
																			Thanks for visiting my profile.
																		</p>
																	</div>
																</div>
															</div>
														</div>

														<div class="col-xs-12 col-sm-6">
															<div class="widget-box transparent">
																<div class="widget-header widget-header-small header-color-blue2">
																	<h4 class="widget-title smaller">
																		<i class="ace-icon fa fa-lightbulb-o bigger-120"></i>
																		My Skills
																	</h4>
																</div>

																<div class="widget-body">
																	<div class="widget-main padding-16">
																		<div class="clearfix">
																			<div class="grid3 center">
																				<!-- #section:plugins/charts.easypiechart -->
																				<div class="easy-pie-chart percentage" data-percent="45" data-color="#CA5952">
																					<span class="percent">45</span>%
																				</div>

																				<!-- /section:plugins/charts.easypiechart -->
																				<div class="space-2"></div>
																				Graphic Design
																			</div>

																			<div class="grid3 center">
																				<div class="center easy-pie-chart percentage" data-percent="90" data-color="#59A84B">
																					<span class="percent">90</span>%
																				</div>

																				<div class="space-2"></div>
																				HTML5 & CSS3
																			</div>

																			<div class="grid3 center">
																				<div class="center easy-pie-chart percentage" data-percent="80" data-color="#9585BF">
																					<span class="percent">80</span>%
																				</div>

																				<div class="space-2"></div>
																				Javascript/jQuery
																			</div>
																		</div>

																		<div class="hr hr-16"></div>

																		<!-- #section:pages/profile.skill-progress -->
																		<div class="profile-skills">
																			<div class="progress">
																				<div class="progress-bar" style="width:80%">
																					<span class="pull-left">HTML5 & CSS3</span>
																					<span class="pull-right">80%</span>
																				</div>
																			</div>

																			<div class="progress">
																				<div class="progress-bar progress-bar-success" style="width:72%">
																					<span class="pull-left">Javascript & jQuery</span>

																					<span class="pull-right">72%</span>
																				</div>
																			</div>

																			<div class="progress">
																				<div class="progress-bar progress-bar-purple" style="width:70%">
																					<span class="pull-left">PHP & MySQL</span>

																					<span class="pull-right">70%</span>
																				</div>
																			</div>

																			<div class="progress">
																				<div class="progress-bar progress-bar-warning" style="width:50%">
																					<span class="pull-left">Wordpress</span>

																					<span class="pull-right">50%</span>
																				</div>
																			</div>

																			<div class="progress">
																				<div class="progress-bar progress-bar-danger" style="width:38%">
																					<span class="pull-left">Photoshop</span>

																					<span class="pull-right">38%</span>
																				</div>
																			</div>
																		</div>

																		<!-- /section:pages/profile.skill-progress -->
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div><!-- /#home -->

												<div id="feed" class="tab-pane">
													<div class="profile-feed row">
														<div class="col-sm-6">
															<div class="profile-activity clearfix">
																<div>
																	<img class="pull-left" alt="Alex Doe's avatar" src="/static/ace/avatars/avatar5.png" />
																	<a class="user" href="#"> Alex Doe </a>
																	changed his profile photo.
																	<a href="#">Take a look</a>

																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		an hour ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<img class="pull-left" alt="Susan Smith's avatar" src="/static/ace/avatars/avatar1.png" />
																	<a class="user" href="#"> Susan Smith </a>

																	is now friends with Alex Doe.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		2 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<i class="pull-left thumbicon fa fa-check btn-success no-hover"></i>
																	<a class="user" href="#"> Alex Doe </a>
																	joined
																	<a href="#">Country Music</a>

																	group.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		5 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<i class="pull-left thumbicon fa fa-picture-o btn-info no-hover"></i>
																	<a class="user" href="#"> Alex Doe </a>
																	uploaded a new photo.
																	<a href="#">Take a look</a>

																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		5 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<img class="pull-left" alt="David Palms's avatar" src="/static/ace/avatars/avatar4.png" />
																	<a class="user" href="#"> David Palms </a>

																	left a comment on Alex's wall.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		8 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>
														</div><!-- /.col -->

														<div class="col-sm-6">
															<div class="profile-activity clearfix">
																<div>
																	<i class="pull-left thumbicon fa fa-pencil-square-o btn-pink no-hover"></i>
																	<a class="user" href="#"> Alex Doe </a>
																	published a new blog post.
																	<a href="#">Read now</a>

																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		11 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<img class="pull-left" alt="Alex Doe's avatar" src="/static/ace/avatars/avatar5.png" />
																	<a class="user" href="#"> Alex Doe </a>

																	upgraded his skills.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		12 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																	<a class="user" href="#"> Alex Doe </a>

																	logged in.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		12 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<i class="pull-left thumbicon fa fa-power-off btn-inverse no-hover"></i>
																	<a class="user" href="#"> Alex Doe </a>

																	logged out.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		16 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>

															<div class="profile-activity clearfix">
																<div>
																	<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																	<a class="user" href="#"> Alex Doe </a>

																	logged in.
																	<div class="time">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		16 hours ago
																	</div>
																</div>

																<div class="tools action-buttons">
																	<a href="#" class="blue">
																		<i class="ace-icon fa fa-pencil bigger-125"></i>
																	</a>

																	<a href="#" class="red">
																		<i class="ace-icon fa fa-times bigger-125"></i>
																	</a>
																</div>
															</div>
														</div><!-- /.col -->
													</div><!-- /.row -->

													<div class="space-12"></div>

													<div class="center">
														<button type="button" class="btn btn-sm btn-primary btn-white btn-round">
															<i class="ace-icon fa fa-rss bigger-150 middle orange2"></i>
															<span class="bigger-110">View more activities</span>

															<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
														</button>
													</div>
												</div><!-- /#feed -->

												<div id="friends" class="tab-pane">
													<!-- #section:pages/profile.friends -->
													<div class="profile-users clearfix">
														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar4.png" alt="Bob Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-online"></span>
																			Bob Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">Content Editor</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 orange"></i>
																			<span class="green"> 20 mins ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar1.png" alt="Rose Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-offline"></span>
																			Rose Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">Graphic Designer</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 grey"></i>
																			<span class="grey"> 30 min ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar.png" alt="Jim Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-busy"></span>
																			Jim Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">SEO &amp; Advertising</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 red"></i>
																			<span class="grey"> 1 hour ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar5.png" alt="Alex Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-idle"></span>
																			Alex Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">Marketing</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 orange"></i>
																			<span class=""> 40 minutes idle </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar2.png" alt="Phil Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-online"></span>
																			Phil Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">Public Relations</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 orange"></i>
																			<span class="green"> 2 hours ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar3.png" alt="Susan Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-online"></span>
																			Susan Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">HR Management</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 orange"></i>
																			<span class="green"> 20 mins ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar1.png" alt="Jennifer Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-offline"></span>
																			Jennifer Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">Graphic Designer</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 grey"></i>
																			<span class="grey"> 2 hours ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="itemdiv memberdiv">
															<div class="inline pos-rel">
																<div class="user">
																	<a href="#">
																		<img src="/static/ace/avatars/avatar3.png" alt="Alexa Doe's avatar" />
																	</a>
																</div>

																<div class="body">
																	<div class="name">
																		<a href="#">
																			<span class="user-status status-offline"></span>
																			Alexa Doe
																		</a>
																	</div>
																</div>

																<div class="popover">
																	<div class="arrow"></div>

																	<div class="popover-content">
																		<div class="bolder">Accounting</div>

																		<div class="time">
																			<i class="ace-icon fa fa-clock-o middle bigger-120 grey"></i>
																			<span class="grey"> 4 hours ago </span>
																		</div>

																		<div class="hr dotted hr-8"></div>

																		<div class="tools action-buttons">
																			<a href="#">
																				<i class="ace-icon fa fa-facebook-square blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-google-plus-square red bigger-150"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>

													<!-- /section:pages/profile.friends -->
													<div class="hr hr10 hr-double"></div>

													<ul class="pager pull-right">
														<li class="previous disabled">
															<a href="#">&larr; Prev</a>
														</li>

														<li class="next">
															<a href="#">Next &rarr;</a>
														</li>
													</ul>
												</div><!-- /#friends -->

												<div id="pictures" class="tab-pane">
													<ul class="ace-thumbnails">
														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-1.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-2.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-3.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-4.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-5.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-6.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-1.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>

														<li>
															<a href="#" data-rel="colorbox">
																<img alt="150x150" src="/static/ace/images/gallery/thumb-2.jpg" />
																<div class="text">
																	<div class="inner">Sample Caption on Hover</div>
																</div>
															</a>

															<div class="tools tools-bottom">
																<a href="#">
																	<i class="ace-icon fa fa-link"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-paperclip"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-pencil"></i>
																</a>

																<a href="#">
																	<i class="ace-icon fa fa-times red"></i>
																</a>
															</div>
														</li>
													</ul>
												</div><!-- /#pictures -->
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>


						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->

	<div class="footer">
		<div class="footer-inner">
			<!-- #section:basics/footer -->
			<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

				&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
			</div>

			<!-- /section:basics/footer -->
		</div>
	</div>

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='/static/ace/js/jquery.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
	window.jQuery || document.write("<script src='/static/ace/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='/static/ace/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
<script src="/static/ace/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/static/ace/js/excanvas.js"></script>
<![endif]-->
<script src="/static/ace/js/jquery-ui.custom.js"></script>
<script src="/static/ace/js/jquery.ui.touch-punch.js"></script>
<script src="/static/ace/js/jquery.gritter.js"></script>
<script src="/static/ace/js/bootbox.js"></script>
<script src="/static/ace/js/jquery.easypiechart.js"></script>
<script src="/static/ace/js/date-time/bootstrap-datepicker.js"></script>
<script src="/static/ace/js/jquery.hotkeys.js"></script>
<script src="/static/ace/js/bootstrap-wysiwyg.js"></script>
<script src="/static/ace/js/select2.js"></script>
<script src="/static/ace/js/fuelux/fuelux.spinner.js"></script>
<script src="/static/ace/js/x-editable/bootstrap-editable.js"></script>
<script src="/static/ace/js/x-editable/ace-editable.js"></script>
<script src="/static/ace/js/jquery.maskedinput.js"></script>

<!-- ace scripts -->
<script src="/static/ace/js/ace/elements.scroller.js"></script>
<script src="/static/ace/js/ace/elements.colorpicker.js"></script>
<script src="/static/ace/js/ace/elements.fileinput.js"></script>
<script src="/static/ace/js/ace/elements.typeahead.js"></script>
<script src="/static/ace/js/ace/elements.wysiwyg.js"></script>
<script src="/static/ace/js/ace/elements.spinner.js"></script>
<script src="/static/ace/js/ace/elements.treeview.js"></script>
<script src="/static/ace/js/ace/elements.wizard.js"></script>
<script src="/static/ace/js/ace/elements.aside.js"></script>
<script src="/static/ace/js/ace/ace.js"></script>
<script src="/static/ace/js/ace/ace.ajax-content.js"></script>
<script src="/static/ace/js/ace/ace.touch-drag.js"></script>
<script src="/static/ace/js/ace/ace.sidebar.js"></script>
<script src="/static/ace/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="/static/ace/js/ace/ace.submenu-hover.js"></script>
<script src="/static/ace/js/ace/ace.widget-box.js"></script>
<script src="/static/ace/js/ace/ace.settings.js"></script>
<script src="/static/ace/js/ace/ace.settings-rtl.js"></script>
<script src="/static/ace/js/ace/ace.settings-skin.js"></script>
<script src="/static/ace/js/ace/ace.widget-on-reload.js"></script>
<script src="/static/ace/js/ace/ace.searchbox-autocomplete.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
	jQuery(function($) {
		if(!$("#ace-settings-add-container").is(':checked'))
			$("#ace-settings-add-container").click();
	});
</script>

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="/static/ace/css/ace.onpage-help.css" />

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="/static/ace/js/ace/elements.onpage-help.js"></script>
<script src="/static/ace/js/ace/ace.onpage-help.js"></script>

</body>
</html>
