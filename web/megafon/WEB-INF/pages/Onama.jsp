<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.als-1.7.min.js"></script>

<title>O nama</title>
</head>

<body class="subpage">

	<div id="site_wrapper">
		<jsp:include page="Header.jsp" />

		<div id="site_menu">
			<ul>
				<li><a href="/megafon/servleti/pocetna">Početna</a></li>

				<c:choose>
					<c:when test='${sessionScope["admin"] != null}'>
						<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
						<li class="last"><a href="/megafon/servleti/postavkeRacuna">Upravljanje</a></li>
					</c:when>
					<c:when test='${sessionScope["user"] != null}'>
						<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
						<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
						<li class="last"><a href="/megafon/servleti/postavkeRacuna">Moji
								podaci</a></li>
					</c:when>

					<c:when
						test='${sessionScope["user"] == null && sessionScope["admin"] == null}'>
						<li><a href="/megafon/servleti/info/onama" class="current">O
								nama</a></li>
						<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
						<li class="last"><a href="/megafon/servleti/login">Prijava</a></li>
					</c:when>

				</c:choose>
			</ul>

			<jsp:include page="MaliSearchbar.jsp" />

			<div class="cleaner"></div>
		</div>
		<!-- end of site_menu -->

		<div id="site_middle_subpage">

			<h2>O nama</h2>
			<h3 class='podnaslov'>Megafon - portal za oglašavanje</h3>

		</div>

		<div id="site_main"></div>

		<h4>Osnovni podaci o tvrtci</h4>

		<table>
			<tr>
				<td><label>Naziv tvrtke: </label></td>
				<td>${pravna.naziv}</td>
			</tr>

			<tr>
				<td><label>Tvrtka: </label></td>
				<td>Megafon d.o.o.</td>
			</tr>
			<tr>
				<td><label>Sjedište: </label></td>
				<td>Izmišljena Adresa 1024, 10000 Zagreb</td>
			</tr>
			<tr>
				<td><label>Sud registracije: </label></td>
				<td>Društvo nije registrirano</td>
			</tr>

			<tr>
				<td><label>MBS: </label></td>
				<td>31415926</td>
			</tr>

			<tr>
				<td><label>OIB: </label></td>
				<td>27182818284</td>
			</tr>

			<tr>
				<td><label>Broj računa: </label></td>
				<td>3141592-2718281828, račun otvoren kod Izmišljena Banka d.d.</td>
			</tr>

			<tr>
				<td><label>IBAN: </label></td>
				<td>HR1234567891011121314</td>
			</tr>

			<tr>
				<td><label>Temeljni kapital: </label></td>
				<td>6.384,00 kn, uplaćen u cijelosti</td>
			</tr>

			<tr>
				<td><label>Vlasnik: </label></td>
				<td>Random Ime</td>
			</tr>
		</table>

		<br> <br>
		<h4>Kontakt podaci</h4>

		<table>
			<tr>
				<td><label>Email:</label></td>
				<td><a href="mailto:kontakt@megafon.hr">kontakt@megafon.hr</a></td>
			</tr>
			<tr>
				<td><label>Telefon:</label></td>
				<td>(01)1234 - 567</td>
			</tr>
			<tr>
				<td><label>Faks</label></td>
				<td>(01)5678 - 910</td>
			</tr>
		</table>
		<br> <br>
		<p>Stranice portala za oglašavanje Megafon u vlasništvu su društva
			Megafon d.o.o.</p>
		<!-- end of main -->

	</div>
	<!-- end of wrapper -->
	<jsp:include page="Footer.jsp" />

</body>
</html>