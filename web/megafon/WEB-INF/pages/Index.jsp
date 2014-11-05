<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.als-1.7.min.js"></script>

<script type="text/javascript">
	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;
	}

	$(document).ready(function() {
		$("#lista1").als({
			visible_items : 3,
			scrolling_items : 1,
			orientation : "horizontal",
			circular : "yes",
			autoscroll : "yes",
			interval : 4500,
			speed : 1250,
			direction : "left",
			start_from : 0
		});

		//logo hover
		$("#logo_img").hover(function() {
			$(this).attr("src", "images/als_logo_hover212x110.png");
		}, function() {
			$(this).attr("src", "images/als_logo212x110.png");
		});

		//logo click
		$("#logo_img").click(function() {
			location.href = "http://als.musings.it/index.php";
		});

		$("a[href^='http://']").attr("target", "_blank");
		$("a[href^='http://als']").attr("target", "_self");
	});
</script>
<title>Početna</title>
</head>

<body class="subpage">

	<div id="site_wrapper">
		<jsp:include page="Header.jsp" />

		<div id="site_menu">
			<ul>
				<li><a href="index.html" class="current">Home</a></li>
				<li><a href="gallery.html">Gallery</a></li>
				<li><a href="news.html">News</a></li>
				<li><a href="blog.html">Blog</a></li>
				<li class="last"><a href="contact.html">Contact</a></li>
			</ul>

			<div id="search_box">
				<form action="#" method="get">
					<input type="text" value="Search" name="q" size="10"
						id="searchfield" title="searchfield" onfocus="clearText(this)"
						onblur="clearText(this)" /> <input type="submit" name="Search"
						value="" id="searchbutton" title="Search" />
				</form>
			</div>

			<div class="cleaner"></div>
		</div>
		<!-- end of site_menu -->

		<div id="site_middle_subpage">
			<h2>Dobrodosli na najbolji portal za oglasavanje ikad</h2>
			<p>Ovdje mozete pronaci sve, od igle do lokomotive</p>
		</div>

		<div id="site_main">

			<div id="lista1" class="als-container">

				<div class="als-viewport">
					<div class="als-wrapper">

						<div class="als-item">
							<div class="lp_box lp_box_last">
								<h6>1N1ullam ut neque neque</h6>
								<a href="#"><img src="/megafon/images/site_image_01.jpg"
									alt="Image 01" /></a>
								<p>Etiam ut nibh et urna cursus ultricies nec vel nunc. In
									hac habitasse platea dictumst.</p>
								<a href="#" class="more float_r">More</a>
								<div class="cleaner"></div>
							</div>
						</div>
						
						<div class="als-item">
							<div class="lp_box lp_box_last">
								<h6>2N1ullam ut neque neque</h6>
								<a href="#"><img src="/megafon/images/site_image_01.jpg"
									alt="Image 01" /></a>
								<p>Etiam ut nibh et urna cursus ultricies nec vel nunc. In
									hac habitasse platea dictumst.</p>
								<a href="#" class="more float_r">More</a>
								<div class="cleaner"></div>
							</div>
						</div>
						
						<div class="als-item">
							<div class="lp_box lp_box_last">
								<h6>3N1ullam ut neque neque</h6>
								<a href="#"><img src="/megafon/images/site_image_01.jpg"
									alt="Image 01" /></a>
								<p>Etiam ut nibh et urna cursus ultricies nec vel nunc. In
									hac habitasse platea dictumst.</p>
								<a href="#" class="more float_r">More</a>
								<div class="cleaner"></div>
							</div>
						</div>
						
						<div class="als-item">
							<div class="lp_box lp_box_last">
								<h6>4N1ullam ut neque neque</h6>
								<a href="#"><img src="/megafon/images/site_image_01.jpg"
									alt="Image 01" /></a>
								<p>Etiam ut nibh et urna cursus ultricies nec vel nunc. In
									hac habitasse platea dictumst.</p>
								<a href="#" class="more float_r">More</a>
								<div class="cleaner"></div>
							</div>
						</div>
					</div>
				</div>
				<span class="als-next"><img
					src="/megafon/images/thin_right_arrow_333.png" alt="next"
					title="next" /></span> <span class="als-prev"><img
					src="/megafon/images/thin_left_arrow_333.png" alt="prev"
					title="previous" /></span>
			</div>
			<div class="cleaner"></div>
			<p>
				Login <a href="/megafon/servleti/login">ovdje</a>
			</p>

			<p>
				Nemate korisnicki racun na portalu? Registrirajte se <a
					href="/megafon/servleti/register">ovdje</a>
			</p>
			
			<p>
				Promini pass <a
					href="/megafon/servleti/promjenaLozinke">ovdje</a>
			</p>
		</div>
		<!-- end of main -->

	</div>
	<!-- end of wrapper -->
	<jsp:include page="Footer.jsp" />

</body>
</html>
