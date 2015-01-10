
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>Prikaz oglasa</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<link href="/megafon/css/jquery.lightbox.css" rel="stylesheet" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.lightbox.js"></script>

</head>
<script type="text/javascript">
	$(document).ready(function() {

		$('.rightbar .littlepic:first-child a').clone().appendTo('.bigpic');
		$('.littlepic a').on('click', function() {
			$('.bigpic').html('');
			$(this).clone().appendTo('.bigpic');
			return false;
		});//end click

	});//end ready
</script>

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
						<c:when test='${sessionScope["logged"] == null}'>
							<li><a href="/megafon/servleti/login">Prijava</a></li>
							<li class="last"><a
								href="/megafon/servleti/registracija/oglasivac">Registracija</a></li>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test='${sessionScope["admin"] == null}'>
									<li class="last"><a href="/megafon/servleti/admin">Postavke</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
									<li class="last"><a
										href="/megafon/servleti/prikaziOglaseOglasivaca">Moji
											oglasi</a></li>
									<li class="last"><a href="/megafon/servleti/user">Postavke</a></li>
								</c:otherwise>
							</c:choose>
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
				<h2>Prikaz oglasa: ${zapis.naslov}</h2>
				<p>
					${zapis.opis} <br>
				</p>
			</div>
			<div class='detaljiOglas'>
				<table>
					<tr>
						<td>Kategorija:</td>
						<td>${zapis.kategorija.naziv}</td>
					</tr>

					<tr>
						<td>Cijena (HRK):</td>
						<td>${zapis.cijena}</td>
					</tr>

					<tr>
						<td>Video:</td>
						<td><a href='${zapis.videoURL}'> link</a></td>
					</tr>


					<c:forEach var="stavka" items="${zapis.dodatneStavke}">
						<tr>
							<td>${stavka.first}</td>
							<td>${stavka.second}</td>
						</tr>
					</c:forEach>
					<c:choose>
						<c:when test='${autorPO != null}'>
							<tr>
								<td>Oglašivač</td>
								<td>${autorPO.username}</td>
							</tr>

							<tr>
								<td>Kontakt broj:</td>
								<td>${autorPO.telefon}</td>
							</tr>
							<tr>
								<td>Kontakt mail:</td>
								<td>${autorPO.email}</td>
							</tr>
							<tr>
								<td>Fax:</td>
								<td>${autorPO.fax}</td>
							</tr>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test='${autorFO != null}'>
							<tr>
								<td>Oglašivač</td>
								<td>${autorFO.username}</td>

							</tr>
							<tr>
								<td>Kontakt broj:</td>
								<td>${autorFO.telefon}</td>
							</tr>
							<tr>
								<td>Kontakt mail:</td>
								<td>${autorFO.email}</td>
							</tr>
						</c:when>
					</c:choose>
					<tr>
						<td>Datum objave:</td>
						<td>${zapis.datum}</td>
					</tr>
				</table>

				<c:choose>
					<c:when test='${autorPO != null}'>
						<p>
							<a
								href="/megafon/servleti/prikaziOglaseOglasivaca?id=${autorPO.id}"
								target="_blank">[Prikazi sve oglase ovog oglašivača]</a>
						</p>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test='${autorFO != null}'>

						<p>
							<a
								href="/megafon/servleti/prikaziOglaseOglasivaca?id=${autorFO.id}"
								target="_blank">[Prikazi sve oglase ovog oglašivača]</a>
						</p>
					</c:when>
				</c:choose>
			</div>
			<div class="galerija">
				<div class='bigpic'></div>
				<div class='rightbar'>
					<c:forEach var="slika" items="${zapis.slikeID}">
						<div class='littlepic'>
							<a href="/megafon/servleti/prikaziSliku?id=${slika}"
								data-lightbox="image-1" data-title="${zapis.naslov}-id:${slika}">
								<img
								src="/megafon/servleti/prikaziSliku?id=${slika}&x=400&y=300">
							</a>
						</div>
					</c:forEach>
				</div>
			</div>


			<div id="site_main"></div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />
</body>

