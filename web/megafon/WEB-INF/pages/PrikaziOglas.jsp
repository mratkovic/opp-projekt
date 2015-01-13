
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
				<h2>Prikaz oglasa: ${zapis.naslov}</h2>
				<p>
					${zapis.opis} <br>
				</p>
			</div>
			<div class='detaljiOglas'>
				<table>
					<tr>
						<td><label>Kategorija:</label></td>
						<td>${zapis.kategorija.naziv}</td>
					</tr>

					<tr>
						<td><label>Cijena (HRK)</label>:</td>
						<td>${zapis.cijena}</td>
					</tr>

					<tr>
						<td><label>Video:</label></td>
						<td><a href='${zapis.videoURL}'> link</a></td>
					</tr>


					<c:forEach var="stavka" items="${zapis.dodatneStavke}">
						<tr>
							<td><label>${stavka.first}</label></td>
							<td>${stavka.second}</td>
						</tr>
					</c:forEach>
					<c:choose>
						<c:when test='${autorPO != null}'>
							<tr>
								<td><label>Oglašivač</label></td>
								<td>${autorPO.username}</td>
							</tr>

							<tr>
								<td><label>Kontakt broj:</label></td>
								<td>${autorPO.telefon}</td>
							</tr>
							<tr>
								<td><label>Kontakt mail:</label></td>
								<td>${autorPO.email}</td>
							</tr>
							<tr>
								<td><label>Fax:</label></td>
								<td>${autorPO.fax}</td>
							</tr>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test='${autorFO != null}'>
							<tr>
								<td><label>Oglašivač</label></td>
								<td>${autorFO.username}</td>

							</tr>
							<tr>
								<td><label>Kontakt broj:</label></td>
								<td>${autorFO.telefon}</td>
							</tr>
							<tr>
								<td><label>Kontakt mail:</label></td>
								<td>${autorFO.email}</td>
							</tr>
						</c:when>
					</c:choose>
					<tr>
						<td><label>Datum objave:</label></td>
						<td>${zapis.datum}</td>
					</tr>
				</table>
				<c:choose>
					<c:when test='${autorPO != null}'>
						<c:choose>
							<c:when test='${autorPO.id == sessionScope["id"] }'>
								<a
									href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${autorPO.id}"
									target="_blank">[Prikazi sve moje oglase]</a>
							</c:when>
							<c:otherwise>
								<p>
									<a
										href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${autorPO.id}"
										target="_blank">[Prikazi sve oglase ovog oglašivača]</a>
								</p>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test='${autorFO != null}'>
						<c:choose>
							<c:when test='${autorPO.id == sessionScope["id"] }'>
								<a
									href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${autorFO.id}"
									target="_blank">[Prikazi sve moje oglase]</a>
							</c:when>
							<c:otherwise>
								<p>
									<a
										href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${autorFO.id}"
										target="_blank">[Prikazi sve oglase ovog oglašivača]</a>
								</p>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>
			</div>
			<div class="galerija">
				<div class='bigpic'></div>
				<div class='rightbar'>
					<c:forEach var="slika" items="${zapis.slikeID}">
						<div class='littlepic'>
							<a href="/megafon/servleti/prikaz/slika?id=${slika}"
								data-lightbox="image-1" data-title="${zapis.naslov}-id:${slika}">
								<img
								src="/megafon/servleti/prikaz/slika?id=${slika}&x=400&y=300">
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

