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
					<li><a href="/megafon/servleti/pocetna" class="current">Početna</a></li>

					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna"
						class="current">Upravljanje</a></li>

				</ul>
<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Uređivanje podataka</h2>
				<p>
					Uredivanje podataka administratora. <br> Potrebno je ispuniti
					zadani obrazac za izmjenu podataka.
				</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/uredi/korisnik?id=${id}"
					method="post" style="padding-bottom: 30px;">


					<table>
						<tr>
							<td class=firstCol><label for="ID"></label></td>
							<td><input type="hidden" name="id" value='${zapis.id}'
								size=5> <c:if test="${zapis.hasError('id')}">
									<div class="greska">${zapis.getError('id')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="ime">Ime:</label></td>
							<td><input type="text" name="ime" value='${zapis.ime}'
								size=40> <c:if test="${zapis.hasError('ime')}">
									<div class="greska">${zapis.getError('ime')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="prezime">Prezime:</label></td>
							<td><input type="text" name="prezime"
								value='${zapis.prezime}' size=40> <c:if
									test="${zapis.hasError('prezime')}">
									<div class="greska">${zapis.getError('prezime')}</div>
								</c:if></td>
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
