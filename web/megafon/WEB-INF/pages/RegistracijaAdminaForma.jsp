<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Registracija</title>
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
					<li class="last"><a href="/megafon/servleti/admin"
						class="current">Postavke</a></li>
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
				<h2>Stvaranje novog administratora</h2>
				<p>
					Stvaranje novog administratora portala za oglašavanje. <br>
					Potrebno je ispuniti zadanu formu za registraciju administratora.
				</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/createAdmin" method="post">
					<table>
						<tr>
							<td class=firstCol><label for="ID"></label></td>
							<td><input type="hidden" name="id" value='${zapis.id}'
								size=5> <c:if test="${zapis.hasError('id')}">
									<div class="greska">${zapis.getError('id')}</div>
								</c:if></td>
						</tr>

						<tr>
							<td class=firstCol><label for="username">Korisnicko
									ime:</label></td>
							<td><input type="text" name="username"
								value='${zapis.username}' size=40> <c:if
									test="${zapis.hasError('username')}">
									<div class="greska">${zapis.getError('username')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="password">Lozinka:</label></td>
							<td><input type="password" name="password"
								value='${zapis.password}' size=40> <c:if
									test="${zapis.hasError('password')}">
									<div class="greska">${zapis.getError('password')}</div>
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
