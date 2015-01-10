<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Rezultati</title>
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
					<li><a href="/megafon/servleti/login" class="current">Prijava</a></li>
					<li class="last"><a href="/megafon/servleti/register">Registracija</a></li>
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
				<h2>Rezultati</h2>
				<p>
					Prikaz rezultata pretrazivanja oglasa.<br> ${kriteriji}
				</p>
			</div>

			<div id="site_main">
				<div class="col_w960">


					<c:choose>
						<c:when test="${!premium.isEmpty()}">
							<h3>Izdvojeni oglasi ove kategorije</h3>
							<c:forEach var="oglas" items="${premium}">
								<!-- nestooooo -->
							</c:forEach>
						</c:when>

					</c:choose>

					<div class="cleaner"></div>
					<br>
					<c:choose>
						<c:when test="${!rezultati.isEmpty()}">
							<h3>Rezultati pretrage</h3>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="oglas" items="${rezultati}">
								<c:choose>
									<c:when test="${count % 2 == 0}">
										<div class="col_w450 float_l">
											<div class="wwd_box">

												<img
													src="/megafon/servleti/prikaziSliku?id=${oglas.slike[0].id}&x=400&y=300"
													alt="Work One" />
												<h3>
													<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
												</h3>
												<p>${oglas.opis}</p>
												<p>Cijena: ${oglas.cijena}</p>
												<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}"
													class="more float_r">Više</a>
												<div class="cleaner"></div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col_w450 float_r">
											<div class="wwd_box">

												<img
													src="/megafon/servleti/prikaziSliku?id=${oglas.slike[0].id}&x=400&y=300"
													alt="Work One" />
												<h3>
													<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
												</h3>
												<p>${oglas.opis}</p>
												<p>Cijena: ${oglas.cijena}</p>
												<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}"
													class="more float_r">Više</a>
												<div class="cleaner"></div>

											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:set var="count" value="${count + 1}" scope="page" />
								<!-- nestooooo -->
							</c:forEach>
						</c:when>
					</c:choose>
				</div>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />
</body>
