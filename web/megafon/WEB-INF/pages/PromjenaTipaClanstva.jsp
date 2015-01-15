<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	pageContext.setAttribute("razmak", " - ");
%>
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
				<h2>Promjena tipa članstva</h2>
				<p>
					Promjena tipa članstva oglašivača <br> Informirajte se o
					pogodnostima različitih tipova članstva te odaberite željeni.
				</p>
			</div>

			<div id="site_main">
				<p>Trenutni tip članstva: ${trenutniTip}</p>
				<p>Datum isteka članstva: ${datumIsteka}</p>
				<p>
					<br>
				</p>
				<p>
					<br>
				</p>

				<h3>Odabir novog tipa članstva</h3>

				<form action="/megafon/servleti/uredi/tip" method="post">

					<select name="odabraniTip" size="1">
						<c:forEach var="tip" items="${tipoviRacuna}">
							<option value='${tip.naziv}'>${tip.naziv}${razmak}${tip.clanarina}
								kn/mjesec
						</c:forEach>

					</select> <input type="submit" name="metoda" value="Promjeni tip">

				</form>
				<p class=fusnota>*Ukoliko se ne obnovi članarina do datuma
					isteka, tip računa se prebacuje u Besplatni tip.</p>
				<p class=fusnota>*Ukoliko već imate neki od plaćenih modela
					članstva, te odlučite odabrati i platiti članarinu za isti tip u
					tom slučaju vam se postojeći datum isteka produži za dodatnih
					mjesec dana.</p>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
