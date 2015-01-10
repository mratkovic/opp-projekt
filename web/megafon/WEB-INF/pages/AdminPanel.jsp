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
					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna"
						class="current">Upravljanje</a></li>
				</ul>
				<div id="search_box">
					<form action="/megafon/servleti/pretraga" method="post">
						<input type="text" value="Pretraga oglasa" name="naziv" size="16"
							id="searchfield" title="searchfield" onfocus="clearText(this)"
							onblur="clearText(this)" /> <input type="submit" name="Search"
							value="" id="searchbutton" title="Search" />
					</form>
				</div>
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Administratorske postavke</h2>
				<p>Prijavljen administrator ${admin.ime} ${admin.prezime}
					'${admin.username}'</p>
			</div>

			<div id="site_main">
				<ul>
					<li>Promjena pristupne lozinke <a
						href="/megafon/servleti/uredi/lozinka">[link]</a>
					<li>
					<li>Promjena osobnih podataka <a
						href="/megafon/servleti/uredi/korisnik?id=${id}">[link]</a>
					</li>
					<li>Dodavanje novog administratora <a
						href="/megafon/servleti/registracija/admin">[link]</a>
					</li>
					<li>Prikaz svih registiranih oglašivača <a
						href="/megafon/servleti/prikazOglasivaca">[link]</a>
					</li>
				</ul>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
