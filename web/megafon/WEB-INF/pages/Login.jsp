<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.als-1.7.min.js"></script>
<title>Prijava</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="/megafon/servleti/pocetna">Početna</a></li>
					<li><a href="/megafon/servleti/info">O nama</a></li>
					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li class="last"><a href="/megafon/servleti/login"
						class="current">Prijava</a></li>
				</ul>
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Prijava</h2>
				<p>
					Prijavite se na sustav s vasim korisnickim imenom i zaporkom. <br>
					Ukoliko nemate korisnicki racun na portalu registrirajte se <a
						href="/megafon/servleti/registracija/oglasivac">ovdje</a>
				</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/login" method="post">
					<table>
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
							<td class=botuni><input type="submit" name="metoda"
								value="Pohrani"> <input type="submit" name="metoda"
								value="Odustani"></td>
						</tr>
					</table>
					<div class="greska">${msg}</div>
				</form>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
