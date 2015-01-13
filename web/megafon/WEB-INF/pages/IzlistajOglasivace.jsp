<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Svi oglašivači</title>
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
				<h2>Prikaz svih oglasivaca</h2>
				<p>
					Prikaz svih korisničkih računa ovog portala. <br> Uređivanje i
					brisanje omogućeno!
				</p>

			</div>

			<div id="site_main">
				<c:choose>
					<c:when test="${oglasivaci.isEmpty()}">
						<p>Nema registriranih oglašivača!</p>
					</c:when>

					<c:otherwise>
						<div class=listaj>
							<table>
								<tr>
									<td class=title>Username</td>
									<td class=title>Tip članstva</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<c:forEach var="zapis" items="${oglasivaci}">
									<tr>
										<td>${zapis.username}</td>
										<td>${zapis.tipClanstva.naziv}</td>
										<td></td>
										<td><a
											href="/megafon/servleti/prikaz/oglasivac?id=${zapis.id}"
											target="_blank">[Prikaži]</a></td>
										<td><a
											href="/megafon/servleti/uredi/korisnik?id=${zapis.id}"
											target="_blank">[Uredi podatke]</a></td>
										<td><a
											href="/megafon/servleti/izbrisi/oglasivac?id=${zapis.id}">[Izbriši]</a></td>
										<td><a
											href="/megafon/servleti/prikaz/oglasi_oglasivaca?id=${zapis.id}"
											target="_blank">[Prikaži sve oglase]</a></td>

										<td></td>
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
