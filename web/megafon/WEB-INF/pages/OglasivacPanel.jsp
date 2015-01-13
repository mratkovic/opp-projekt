<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Moji podaci</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="/megafon/servleti/pocetna">Početna</a></li>
					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna"
						class="current">Moji podaci</a></li>
				</ul>
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Korisničke opcije</h2>
				<p>Prikaz podataka korisnika '${korisnik.username}'</p>
			</div>

			<table class='info'>
				<c:choose>
					<c:when test='${pravna != null}'>
						<tr>
							<td><label>Naziv tvrtke: </label></td>
							<td>${pravna.naziv}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td><label>Ime: </label></td>
							<td>${fizicka.ime}</td>
						</tr>
						<tr>
							<td><label>Prezime: </label></td>
							<td>${fizicka.prezime}</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td><label>Korisničko ime: </label></td>
					<td>${korisnik.username}</td>
				</tr>
				<tr>
					<td><label>Trenutni tip članstva: </label></td>
					<td>${korisnik.tipClanstva.naziv}
					<a href="/megafon/servleti/uredi/tip" style='padding-left: 5px'>[Promjeni tip]</a>
					</td>
				</tr>
				<tr>
					<td><label>Datum isteka članstva: </label></td>
					<td>${datumIsteka}</td>
				</tr>
				<tr>
					<td><label>Datum registracije: </label></td>
					<td>${datumRegistracije}</td>
				</tr>
				<tr>
					<td><label>Adresa: </label></td>
					<td>${korisnik.adresa}</td>
				</tr>
				<tr>
					<td><label>Email: </label></td>
					<td>${korisnik.email}</td>
				</tr>
				<tr>
					<td><label>Telefon: </label></td>
					<td>${korisnik.telefon}</td>
				</tr>


				<c:choose>
					<c:when test='${pravna != null}'>
						<tr>
							<td><label>Fax: </label></td>
							<td>${korisnik.fax}</td>
						</tr>

					</c:when>
				</c:choose>
			</table>
			<br>
			<table>
				<tr>
					<td><a
						href="/megafon/servleti/uredi/korisnik?id=${korisnik.id}">[Uredi
							podatke]</a></td>
					<td><a href="/megafon/servleti/uredi/lozinka">[Promjeni
							lozinku]</a></td>
				</tr>

			</table>


			<br>
			<h3>Moji oglasi</h3>
			<div class=listaj>
				<table>
					<c:choose>
						<c:when test="${oglasi.isEmpty()}">
							<tr>
								<td>Nema objavljenih oglasa</td>
							</tr>
						</c:when>

						<c:otherwise>
							<tr>
								<td class=title>Naziv oglasa</td>
								<td class=title>Kategorija</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<c:forEach var="zapis" items="${oglasi}">
								<tr>
									<td>${zapis.naslov}</td>
									<td>${zapis.pripadaKategoriji.naziv}</td>
									<td></td>
									<td><a
										href="/megafon/servleti/prikaz/oglas?id=${zapis.id}">[Prikaži]</a></td>
									<td><a href="/megafon/servleti/uredi/oglas?id=${zapis.id}">[Uredi]</a></td>
									<td><a
										href="/megafon/servleti/izbrisi/oglas?id=${zapis.id}">[Izbriši]</a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
				<div class="cleaner"></div>
				<br> <br>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>