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
					<li><a href="/megafon/servleti/pocetna">Početna</a></li>
					<li><a href="/megafon/servleti/">Pretraga</a></li>
					<li><a href="/megafon/servleti/">O nama</a></li>

					<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
					<li><a
						href="/megafon/servleti/prikaziOglaseOglasivaca">Moji oglasi</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna">Postavke</a></li>

				</ul>

				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Postavke računa</h2>
				<p>nest nesto nesto nesto</p>
			</div>
			<p>
				Promjena lozine <a href="/megafon/servleti/uredi/lozinka">ovdje</a>
			</p>

			<p>
				Promjena podataka <a
					href="/megafon/servleti/uredi/korisnik?id=${id}">ovdje</a>
			</p>
			
			<p>
				Moji oglasi <a
					href="/megafon/servleti/prikaziOglaseOglasivaca">ovdje</a>
			</p>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>