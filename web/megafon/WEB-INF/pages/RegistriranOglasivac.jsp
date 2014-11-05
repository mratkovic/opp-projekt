<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="gallery.html" class="current">Gallery</a></li>
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
				<h2>Uspješna registracija</h2>
				<p>
					Čestitamo, uspješno ste otvorili račun na našem portalu. <br>
					Tim 'Megafon' želi Vam puno uspjeha u prodaji.
				</p>

			</div>

			<div id="site_main">
				<p>Uspjesno dodan racun ${oglasivac}.</p>

				<p>
					Ukoliko zelite se informirati o naprednijim tipovima racuna, te
					promjeniti vas tip kliknite 
					<a href="/megafon/servleti/promjenaTipa"> ovdje</a>
				</p>

			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
