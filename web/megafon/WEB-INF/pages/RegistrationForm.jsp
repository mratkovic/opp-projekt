<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Registracja</title>
<style type="text/css">
.box {
	display: none;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('input[type="radio"]').change(function() {
			if ($(this).attr("value") == "po") {
				$(".box").hide();
				$(".po").show();
			}
			if ($(this).attr("value") == "fo") {
				$(".box").hide();
				$(".fo").show();
			}
		});
	});
</script>
</head>

<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="gallery.html" class="current">Gallery</a></li>
					<li><a href="news.html">News</a></li>
					<li><a href="blog.html">Blog</a></li>
					<li class="last"><a href="contact.html">Contact</a></li>
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
				<h2>Stvaranje novog oglašivača</h2>
				<p>
					Stvaranje novog oglasivaca. <br> Potrebno je ispuniti zadanu
					formu za registraciju oglasivaca.
				</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/register" method="post"
					style="padding-bottom: 30px;">
					<div class="tipOglasivacaRadio">
						<label> <input id="odabraniTip" type="radio"
							name="odabraniTip" value="fo" ${jeFizicka ? 'checked':''} />Fizička
							osoba
						</label> <label><input id="odabraniTip" type="radio"
							name="odabraniTip" value="po" ${jePravna ? 'checked':''} />Pravna
							osoba</label>
					</div>
					<table class="fo box">
						<tr>
							<td class=firstCol><label for="ID_fo"></label></td>
							<td><input type="hidden" name="id_fo" value='${zapisFO.id}'
								size=5> <c:if test="${zapisFO.hasError('id_fo')}">
									<div class="greska">${zapisFO.getError('id_fo')}</div>
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
							<td class=firstCol><label for="username_fo">Korisničko
									ime:</label></td>
							<td><input type="text" name="username_fo"
								value='${zapisFO.username}' size=40> <c:if
									test="${zapisFO.hasError('username_fo')}">
									<div class="greska">${zapisFO.getError('username_fo')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="password_fo">Lozinka:</label></td>
							<td><input type="password" name="password_fo"
								value='${zapisFO.password}' size=40> <c:if
									test="${zapisFO.hasError('password_fo')}">
									<div class="greska">${zapisFO.getError('password_fo')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td></td>
							<td><input type="checkbox" name="prihvacam_fo" value="true">
								U potpunosti se slažem s <a href="ovdeIdeLink" target="_blank">
									Uvjetima korištenja *</a> <c:if
									test="${zapisFO.hasError('prihvacam_fo')}">
									<div class="greska">${zapisFO.getError('prihvacam_fo')}</div>
								</c:if></td>
						</tr>
						<tr>

							<td></td>
							<td class=botuni><input type="submit" name="metoda" value="Pohrani">
								<input type="submit" name="metoda" value="Odustani"></td>

						</tr>

					</table>

					<table class="po box">
						<tr>
							<td class=firstCol><label for="id_po"></label></td>
							<td><input type="hidden" name="id_po" value='${zapisPO.id}'
								size=5> <c:if test="${zapisPO.hasError('id_po')}">
									<div class="greska">${zapisPO.getError('id_po')}</div>
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
							<td class=firstCol><label for="username_po">Korisničko
									ime:</label></td>
							<td><input type="text" name="username_po"
								value='${zapisPO.username}' size=40> <c:if
									test="${zapisPO.hasError('username_po')}">
									<div class="greska">${zapisPO.getError('username_po')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="password_po">Lozinka:</label></td>
							<td><input type="password" name="password_po"
								value='${zapisPO.password}' size=40> <c:if
									test="${zapisPO.hasError('password_po')}">
									<div class="greska">${zapisPO.getError('password_po')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="checkbox" name="prihvacam_po" value="true">
								U potpunosti se slažem s <a href="ovdeIdeLink" target="_blank">
									Uvjetima korištenja *</a> <c:if
									test="${zapisPO.hasError('prihvacam_po')}">
									<div class="greska">${zapisFO.getError('prihvacam_po')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td></td>
							<td class=botuni><input type="submit" name="metoda" value="Pohrani">
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
