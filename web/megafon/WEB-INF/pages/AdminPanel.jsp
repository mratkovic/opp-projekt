<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Postavke računa</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="/megafon/servleti/pocetna" class="current">Početna</a></li>
					<li><a href="/megafon/servleti/">Pretraga</a></li>
					<li><a href="/megafon/servleti/">O nama</a></li>
					<li class="last"><a class='current' href="/megafon/servleti/postavkeRacuna">Postavke</a></li>
				</ul>


				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Postavke računa</h2>
				<p>
					nest nesto nesto nesto
				</p>
			</div>
			
			<div id="site_main">

				<p>
					Dodavanje novog administratora<a
						href="/megafon/servleti/registracija/admin">ovdje</a>
				</p>

				<p>
					Promjena lozine <a href="/megafon/servleti/uredi/lozinka">ovdje</a>
				</p>

				<p>
					Promjena podataka <a href="/megafon/servleti/uredi/korisnik?id=${id}">ovdje</a>
				</p>
				
				<p>
					Izlistaj sve oglasivace
					<a href="/megafon/servleti/prikazOglasivaca">ovdje</a>
				</p>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
