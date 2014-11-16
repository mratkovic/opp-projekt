<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Promjena lozinke</title>
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
				<h2>Promjena lozinke</h2>
				<p>
					Promjena pristupne lozinke <br> Potrebno je ispuniti zadanu
					formu za promjenu lozinke.
				</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/uredi/lozinka" method="post">
					<table>
						<tr>
							<td class=firstCol><label for="oldPass">Trenutna
									lozinka:</label></td>
							<td><input type="password" name="oldPass"
								value='${zapis.oldPass}' size=40> <c:if
									test="${zapis.hasError('oldPass')}">
									<div class="greska">${zapis.getError('oldPass')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="password1">Nova
									ozinka:</label></td>
							<td><input type="password" name="password1"
								value='${zapis.password1}' size=40> <c:if
									test="${zapis.hasError('password1')}">
									<div class="greska">${zapis.getError('password1')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="password2">Potvrda
									nove lozinke:</label></td>
							<td><input type="password" name="password2"
								value='${zapis.password2}' size=40> <c:if
									test="${zapis.hasError('password2')}">
									<div class="greska">${zapis.getError('password2')}</div>
								</c:if></td>
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
