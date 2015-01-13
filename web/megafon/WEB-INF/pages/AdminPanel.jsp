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
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Administratorske postavke</h2>
				<h3 class='podnaslov'>Prijavljen administrator ${admin.ime}
					${admin.prezime} '${admin.username}'</h3>
			</div>

			<div id="site_main">

				<h3>Moji podaci</h3>

				<table class='info'>

					<tr>
						<td><label>Ime: </label></td>
						<td>${admin.ime}</td>
					</tr>
					<tr>
						<td><label>Prezime: </label></td>
						<td>${admin.prezime}</td>
					</tr>

					<tr>
						<td><label>Korisničko ime: </label></td>
						<td>${admin.username}</td>
					</tr>

				</table>
				<br> <br>
				<table>
					<tr>
						<td><a href="/megafon/servleti/uredi/lozinka">[Promjena
								osobnih podataka]</a></td>

					</tr>
					<tr>
						<td><a href="/megafon/servleti/uredi/lozinka">[Promjeni
								lozinku]</a></td>
					</tr>
				</table>
				<br> <br>
				<h3>Administratorske opcije</h3>
				<ul class='opcije'>
					<li>Dodavanje novog administratora <a
						href="/megafon/servleti/registracija/admin">[link]</a>
					</li>
					<li>Dodavanje nove kategorije oglasa <a
						href="/megafon/servleti/kategorija">[link]</a>
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
