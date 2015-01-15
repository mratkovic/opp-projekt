<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Uredi</title>

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
					</c:choose>
				</ul>
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Uređivanje podataka</h2>
				<p>
					Stvaranje novog oglašivača. <br> Potrebno je ispuniti zadani
					obrazac za izmjenu korisnickih podataka.
				</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/uredi/korisnik?id=${id}"
					method="post" style="padding-bottom: 30px;">

					<input type="hidden" name="odabraniTip"
						value='${jePravna ? "po" : "fo"}' size=10>


					<table class="fo box"
						${!jePravna ? 'style="display: table;"':'style="display: none;"'}>
						<tr>
							<td class=firstCol><label for="ID_fo"></label></td>
							<td><input type="hidden" name="id" value='${zapisFO.id}'
								size=5> <c:if test="${zapisFO.hasError('id')}">
									<div class="greska">${zapisFO.getError('id')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="ime">Ime:</label></td>
							<td><input type="text" name="ime" value='${zapisFO.ime}'
								size=40> <c:if test="${zapisFO.hasError('ime')}">
									<div class="greska">${zapisFO.getError('ime')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="prezime">Prezime:</label></td>
							<td><input type="text" name="prezime"
								value='${zapisFO.prezime}' size=40> <c:if
									test="${zapisFO.hasError('prezime')}">
									<div class="greska">${zapisFO.getError('prezime')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="oib_fo">OIB:</label></td>
							<td><input type="text" name="oib_fo" value='${zapisFO.oib}'
								size=40> <c:if test="${zapisFO.hasError('oib_fo')}">
									<div class="greska">${zapisFO.getError('oib_fo')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="adresa_fo">Adresa:</label></td>
							<td><input type="text" name="adresa_fo"
								value='${zapisFO.adresa}' size=40> <c:if
									test="${zapisFO.hasError('adresa_fo')}">
									<div class="greska">${zapisFO.getError('adresa_fo')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="telefon_fo">Telefon:</label></td>
							<td><input type="text" name="telefon_fo"
								value='${zapisFO.telefon}' size=40> <c:if
									test="${zapisFO.hasError('telefon_fo')}">
									<div class="greska">${zapisFO.getError('telefon_fo')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="email_fo">E-Mail:</label></td>
							<td><input type="text" name="email_fo"
								value='${zapisFO.email}' size=40> <c:if
									test="${zapisFO.hasError('email_fo')}">
									<div class="greska">${zapisFO.getError('email_fo')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td><input type="hidden" name="prihvacam_fo" value="true"
								size=10></td>
						</tr>
						<tr>

							<td></td>
							<td class=botuni><input type="submit" name="metoda"
								value="Pohrani"> <input type="submit" name="metoda"
								value="Odustani"></td>

						</tr>

					</table>

					<table class="po box"
						${jePravna ? 'style="display: table;"':'style="display: none;"'}>
						<tr>
							<td class=firstCol><label for="id_po"></label></td>
							<td><input type="hidden" name="id" value='${zapisPO.id}'
								size=5> <c:if test="${zapisPO.hasError('id')}">
									<div class="greska">${zapisPO.getError('id')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="naziv">Naziv tvrtke:</label></td>
							<td><input type="text" name="naziv" value='${zapisPO.naziv}'
								size=40> <c:if test="${zapisPO.hasError('naziv')}">
									<div class="greska">${zapisPO.getError('naziv')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="oib_po">OIB:</label></td>
							<td><input type="text" name="oib_po" value='${zapisPO.oib}'
								size=40> <c:if test="${zapisPO.hasError('oib_po')}">
									<div class="greska">${zapisPO.getError('oib_po')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="adresa_po">Adresa
									sjedišta:</label></td>
							<td><input type="text" name="adresa_po"
								value='${zapisPO.adresa}' size=40> <c:if
									test="${zapisPO.hasError('adresa_po')}">
									<div class="greska">${zapisPO.getError('adresa_po')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="telefon_po">Telefon:</label></td>
							<td><input type="text" name="telefon_po"
								value='${zapisPO.telefon}' size=40> <c:if
									test="${zapisPO.hasError('telefon_po')}">
									<div class="greska">${zapisPO.getError('telefon_po')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td class=firstCol><label for="fax">FAX:</label></td>
							<td><input type="text" name="fax" value='${zapisPO.fax}'
								size=40> <c:if test="${zapisPO.hasError('fax')}">
									<div class="greska">${zapisPO.getError('fax')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td class=firstCol><label for="email">E-Mail:</label></td>
							<td><input type="text" name="email_po"
								value='${zapisPO.email}' size=40> <c:if
									test="${zapisPO.hasError('email_po')}">
									<div class="greska">${zapisPO.getError('email_po')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td><input type="hidden" name="prihvacam_fo" value="true"
								size=10></td>
						</tr>

						<tr>
							<td></td>
							<td class=botuni><input type="submit" name="metoda"
								value="Pohrani"> <input type="submit" name="metoda"
								value="Odustani"></td>
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
