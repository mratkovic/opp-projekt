<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Novi</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="/megafon/servleti/pocetna"> Početna</a></li>
					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li><a href="/megafon/servleti/dodajOglas" class="current">Dodaj
							oglas</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna">Moji
							podaci</a></li>

				</ul>
<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Dodavanje novog oglasa</h2>
				<p>
					Popunite sljedeći formular za dodavanje oglasa <br>
				</p>
			</div>

			<div id="site_main">

				<form action="/megafon/servleti/dodajOglas" method="post"
					enctype="multipart/form-data">
					<table>

						<tr>
							<td class=firstCol><label for="naslov">Naslov
									oglasa:</label></td>
							<td><input type="text" name="naslov" value='${zapis.naslov}'
								size=40> <c:if test="${zapis.hasError('naslov')}">
									<div class="greska">${zapis.getError('naslov')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td class=firstCol><label for="kategorija">Kategorija:</label></td>
							<td><input type="text" name="kategorija"
								value='${nazivKategorije}' readonly size=40></td>
						</tr>


						<tr>
							<td class=firstCol><label for="opis">Opis:</label></td>
							<td><textarea name="opis" rows="6" cols="40">${zapis.opis}</textarea>
								<c:if test="${zapis.hasError('opis')}">
									<div class="greska">${zapis.getError('opis')}</div>
								</c:if></td>
						</tr>



						<tr>
							<td class=firstCol><label for="videoURL">Video link:</label></td>
							<td><input type="text" name="videoURL"
								value='${zapis.videoURL}' size=40> <c:if
									test="${zapis.hasError('videoURL')}">
									<div class="greska">${zapis.getError('videoURL')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td class=firstCol><label for="cijena">Cijena (HRK):</label></td>
							<td><input type="text" step="any" name="cijena"
								value='${zapis.cijena}' size=40> <c:if
									test="${zapis.hasError('cijena')}">
									<div class="greska">${zapis.getError('cijena')}</div>
								</c:if></td>
						</tr>




						<c:forEach var="stavka" items="${zapis.dodatneStavke}">
							<tr>
								<td class=firstCol><label for="${stavka.first}">${stavka.first}</label></td>
								<td><input type="text" name="${stavka.first}"
									value='${stavka.second}' size=40> <c:if
										test="${zapis.hasError(stavka.first)}">
										<div class="greska">${zapis.getError(stavka.first)}</div>
									</c:if></td>
							</tr>

						</c:forEach>


						<tr>
							<td class=firstCol><label for="slike">Slike:</label></td>
							<td><input type="file" name="slike" value='${slike}'
								multiple="multiple" size=180> <c:if
									test="${zapis.hasError('slike')}">
									<div class="greska">${zapis.getError('slike')}</div>
								</c:if></td>
						</tr>
						<tr>

							<td><input type="hidden" name="kategorijaID"
								value='${zapis.kategorijaID}'></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" name="metoda" value="Pohrani">
								<input type="submit" name="metoda" value="Odustani"></td>
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
