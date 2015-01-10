<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.als-1.7.min.js"></script>
<link href="/megafon/css/jquery.lightbox.css" rel="stylesheet" />
<script type="text/javascript" src="/megafon/js/jquery.lightbox.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#lista1").als({
			visible_items : 3,
			scrolling_items : 1,
			orientation : "horizontal",
			circular : "yes",
			autoscroll : "yes",
			interval : 4500,
			speed : 1250,
			direction : "left",
			start_from : 0
		});

	});
</script>

<title>Rezultati</title>
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
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna">Upravljanje</a></li>
						</c:when>
						<c:when test='${sessionScope["user"] != null}'>
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
							<li class="last"><a href="/megafon/servleti/postavkeRacuna">Moji
									podaci</a></li>
						</c:when>

						<c:when
							test='${sessionScope["user"] == null && sessionScope["admin"] == null}'>
							<li><a href="/megafon/servleti/info">O nama</a></li>
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li class="last"><a href="/megafon/servleti/login">Prijava</a></li>
						</c:when>

					</c:choose>
				</ul>


				<div id="search_box">
					<form action="/megafon/servleti/pretraga" method="post">
						<input type="text" value="Pretraga oglasa" name="naziv" size="16"
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
													src="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=400&y=300"
													alt="Work One" />
												<h3>
													<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
												</h3>
												<p>${oglas.opis}</p>
												<p>
													<span class="oglasLabela">Cijena: </span>${oglas.cijena}
												</p>
												<p>
													<span class="oglasLabela">Datum objave: </span>
													${oglas.datum}
												</p>
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
													src="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=400&y=300"
													alt="Work One" />
												<h3>
													<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
												</h3>
												<p>${oglas.opis}</p>

												<p>
													<span class="oglasLabela">Cijena: </span>${oglas.cijena}
												</p>
												<p>
													<span class="oglasLabela">Datum objave: </span>
													${oglas.datum}
												</p>
												<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}"
													class="more float_r">Više</a>
												<div class="cleaner"></div>

											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:set var="count" value="${count + 1}" scope="page" />

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
