
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>Prikaz oglasa</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
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
			<table>
				<tr>
					<td class=title>Kategorija</td>
					<td>${zapis.kategorijaID}</td>
				</tr>

				<tr>
					<td class=title>cijena</td>
					<td>${zapis.cijena}</td>
				</tr>

				<c:forEach var="stavka" items="${zapis.dodatneStavke}">
					<tr>
						<td class=title>${stavka.first}</td>
						<td>${stavka.second}</td>
					</tr>
				</c:forEach>
				
				<c:forEach var="slika" items="${zapis.slikeID}">
					<tr>
						<td class=title>${slika}</td>
						<td><a href="/megafon/servleti/prikaziSliku?id=${slika}">[]link]</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="site_main"></div>
		<!-- end of main -->
	</div>
	<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>

