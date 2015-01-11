<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<link href="/megafon/css/jquery.lightbox.css" rel="stylesheet" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.lightbox.js"></script>
<title>Uredi oglas</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
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
				<h2>Uređivanje oglasa</h2>
				<p>
					Popunite sljedeći formular za izmjenu oglasa <br>
				</p>
			</div>

			<div id="site_main">

				<form action="/megafon/servleti/uredi/oglas" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="id" value='${zapis.id}' size=5>
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

							<td><select name="kategorijaID" size="1">
									<c:forEach var="tip" items="${kategorije}">
										<c:choose>
											<c:when
												test="${tip.nadkategorija==null && empty tip.podkategorije}">
												<option value='${tip.id}'
													${tip.id == zapis.kategorijaID ? selected : ''}>
													${tip.naziv}</option>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${not empty tip.podkategorije}">
														<optgroup label="${tip.naziv}">
															<c:forEach var="podtip" items="${tip.podkategorije}">
																<option value='${podtip.id}'
																	${(podtip.id == zapis.kategorijaID) ? selected : ''}>${podtip.naziv}</option>
															</c:forEach>
														</optgroup>
													</c:when>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select></td>
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
							<td class=firstCol><label for="slike">Nove slike:</label></td>
							<td><input type="file" name="slike" value='${slike}'
								multiple="multiple" size=180> <c:if
									test="${zapis.hasError('slike')}">
									<div class="greska">${zapis.getError('slike')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td class=firstCol><label for="sakrij">Sakrij oglas:
							</label></td>
							<td><input type="checkbox"
								${zapis.jeSkriven ?  'checked' : ''} name="jeSkriven"
								value="true"></td>
						</tr>

					</table>
					<br> <br>
					<h3>Slike</h3>
					<table>
						<tr>
							<td class=title>Naziv</td>
							<td class='title prikaziSliku'>Prikaži</td>
							<td class=title>Označi za brisanje</td>
						</tr>
						<c:forEach var="stavka" items="${zapis.slikeID}">
							<tr>
								<td class=firstCol>Slika${stavka}</td>

								<td class='prikaziSliku'><a
									href="/megafon/servleti/prikaziSliku?id=${stavka}"
									data-lightbox="image-1"
									data-title="${zapis.naslov}-id:${slika}"> [Prikaži] </a></td>
								<td><input type="checkbox" name="slika${stavka}"
									value="true"></td>

							</tr>
						</c:forEach>
					</table>
					<br>
					<table class="btns">
						<tr>

							<td><input type="hidden" name="kategorijaID"
								value='${zapis.kategorijaID}'></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" name="metoda" value="Pohrani">
								<input type="submit" name="metoda" value="Ukloni"> <input
								type="submit" name="metoda" value="Odustani"></td>
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
