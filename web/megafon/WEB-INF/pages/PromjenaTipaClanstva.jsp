<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Promjena tipa članstva</title>
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
							<li class="last"><a href="/megafon/servleti/user">Postavke</a></li>
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
				<h2>Promjena tipa članstva</h2>
				<p>
					Promjena tipa članstva oglašivača <br> Informirajte se o
					pogodnostima različitih tipova članstva te odaberite željeni
				</p>
			</div>

			<div id="site_main">

				<p>Trenutni tip članstva: ${trenutniTip}</p>
				<p>Datum isteka članstva: ${datumIsteka}</p>
				<p class=fusnota>*Ukoliko se ne obnovi članarina do datuma
					isteka, tip računa se prebacuje u Besplatni tip</p>
				<p class=fusnota>*Ukoliko već imate neki od plačenih modela
					clanstva, te odlučite odabrati i platiti članarinu za isti tip u
					tom slucaju vam se postoječi datum isteka produzi za dodatnih
					mjesec dana</p>
				<form action="/megafon/servleti/promjenaTipa" method="post">

					<select name="odabraniTip" size="1">
						<c:forEach var="tip" items="${tipoviRacuna}">
							<option value='${tip.naziv}'>${tip.naziv}-${tip.clanarina}
								kn/mjesec
						</c:forEach>
					</select>
					<table>
						<tr>
							<td></td>
							<td class=botuni><input type="submit" name="metoda"
								value="Promjeni tip"></td>
						</tr>
					</table>

				</form>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
