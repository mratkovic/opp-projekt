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

					<c:choose>
						<c:when test='${sessionScope["admin"] != null}'>
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna">Upravljanje</a></li>
						</c:when>
						<c:when test='${sessionScope["user"] != null}'>
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna">Moji
									podaci</a></li>
						</c:when>

						<c:when
							test='${sessionScope["user"] == null && sessionScope["admin"] == null}'>
							<li><a href="/megafon/servleti/info/onama">O nama</a></li>
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li class="last"><a href="/megafon/servleti/login">Prijava</a></li>
						</c:when>

					</c:choose>
				</ul>
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Prikaz podataka korisnika '${korisnik.username}'</h2>

			</div>

			<div id="site_main">
				<table>

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
						<td><label>OIB: </label></td>
						<td>${korisnik.oib}</td>
					</tr>
					<tr>
						<td><label>Korisničko ime: </label></td>
						<td>${korisnik.username}</td>
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

					<tr>
						<td><label>Trenutni tip članstva: </label></td>
						<td>${korisnik.tipClanstva.naziv}</td>
					</tr>
					<tr>
						<td><label>Datum isteka članstva: </label></td>
						<td>${datumIsteka}</td>
					</tr>
					<tr>
						<td><label>Datum registracije: </label></td>
						<td>${datumRegistracije}</td>
					</tr>
				</table>
				<br>
				<table>
					<tr>

						<c:choose>
							<c:when test='${korisnik.id == sessionScope["id"] }'>
								<td><a
									href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${korisnik.id}">[Prikaži
										sve moje oglase]</a></td>
								<td><a
									href="/megafon/servleti/uredi/korisnik?id=${korisnik.id}">[Uredi
										moje podatke]</a>
							</c:when>
							<c:otherwise>
								<td><a
									href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${korisnik.id}">[Prikaži
										sve oglase]</a></td>
								<c:choose>
									<c:when test='${sessionScope["admin"] != null}'>
										<td><a
											href="/megafon/servleti/uredi/korisnik?id=${korisnik.id}">[Uredi
												podatke o korisniku]</a></td>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
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
