<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Oglašivač</title>
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

					<c:choose>

						<c:when test='${sessionScope["admin"] == null}'>
							<li class="last"><a href="/megafon/servleti/admin"
								class="current">Postavke</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
							<li class="last"><a
								href="/megafon/servleti/prikaziOglaseOglasivaca">Moji oglasi</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna">Postavke</a></li>
						</c:otherwise>
					</c:choose>
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
				<h2>Prikaz podataka korisnika ${korisnik.username}</h2>
				<p>Prikaz detalja korisnika........</p>
			</div>

			<div id="site_main">
				<c:choose>
					<c:when test='${pravna != null}'>
						<p>Pravna osoba</p>
						<p>Naziv firme: ${pravna.naziv}</p>
					</c:when>
					<c:otherwise>
						<p>Fizička osoba</p>
						<p>Ime: ${fizicka.ime}</p>
						<p>Prezime: ${fizicka.prezime}</p>
					</c:otherwise>
				</c:choose>
				<p>Korisničko ime: ${korisnik.username}</p>
				<p>Trenutni tip članstva: ${korisnik.tipClanstva.naziv}</p>
				<p>Datum isteka članstva: ${datumIsteka}</p>
				<p>Datum registracije: ${datumRegistracije}</p>
				<p>Adresa: ${korisnik.adresa}</p>
				<p>EMail: ${korisnik.email}</p>
				<p>Telefon: ${korisnik.telefon}</p>

				<c:choose>
					<c:when test='${pravna != null}'>
						<p>Fax: ${pravna.fax}</p>
					</c:when>
				</c:choose>

				<br>
				<table>
					<tr>
						<td><a
							href="/megafon/servleti/prikaziOglaseOglasivaca?id=${korisnik.id}">[Prikazi
								sve oglase]</a></td>
						<td><c:choose>
								<c:when test='${korisnik.id == sessionScope["id"] }'>
									<td><a
										href="/megafon/servleti/uredi/korisnik?id=${korisnik.id}">[Uredi
											moje podatke]</a>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test='${sessionScope["admin"] != null}'>
											<a href="/megafon/servleti/uredi/korisnik?id=${korisnik.id}">[Uredi
												podatke o korisniku]</a>
										</c:when>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
					</tr>


				</table>

			</div>

			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
