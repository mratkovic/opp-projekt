<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Svi oglasi oglašivača</title>
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
							<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna"
								class="current">Upravljanje</a></li>
						</c:when>
						<c:when test='${sessionScope["user"] != null}'>
							<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
							<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna"
								class="current">Moji podaci</a></li>
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
				<h2>Prikaz svih oglasa oglasivaca ${autor}</h2>
				<p>
					Prikaz svih oglasa kreiranih od strane ovog autora <br>
					Brisanje oglasa omoguceno
				</p>

			</div>

			<div id="site_main">
				<c:choose>
					<c:when test="${oglasi.isEmpty()}">
						<p>Oglašivac nije objavio niti jedan oglas</p>
					</c:when>

					<c:otherwise>
						<div class=listaj>
							<table>
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
							</table>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
