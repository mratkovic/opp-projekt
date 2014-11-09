
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>${title}</title>
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
						<c:when test='${sessionScope["logged"] == null}'>
							<li><a href="/megafon/servleti/login">Prijava</a></li>
							<li class="last"><a href="/megafon/servleti/register">Registracija</a></li>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test='${sessionScope["admin"] == null}'>
									<li class="last"><a href="/megafon/servleti/admin">Postavke</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
									<li class="last"><a
										href="/megafon/servleti/prikaziOglaseOglasivaca">Moji
											oglasi</a></li>
									<li class="last"><a href="/megafon/servleti/user">Postavke</a></li>
								</c:otherwise>
							</c:choose>
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
				<h2>${title}</h2>
				<p>${msg}</p>
			</div>
		</div>

		<div id="site_main"></div>
		<!-- end of main -->
	</div>
	<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
