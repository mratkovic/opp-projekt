<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.als-1.7.min.js"></script>
<link href="/megafon/css/jquery.lightbox.css" rel="stylesheet" />
<script type="text/javascript" src="/megafon/js/jquery.lightbox.js"></script>

<title>Oglasi</title>
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
							<li><a href="/megafon/servleti/info/onama">O nama</a></li>
							<li><a href="/megafon/servleti/pretraga" class="current">Pretraživanje</a></li>
							<li class="last"><a href="/megafon/servleti/login">Prijava</a></li>
						</c:when>

					</c:choose>
				</ul>
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Oglašivač '${autor}'</h2>
				<h3 class='podnaslov'>Prikaz svih oglasa ovog oglašivača</h3>
			</div>

			<div id="site_main">
				<div class="col_w960">
					<c:choose>
						<c:when test="${!oglasi.isEmpty()}">
							<h3 id='naslovVeci'>Svi oglasi</h3>
							<br>
							<c:forEach var="oglas" items="${oglasi}">


								<div class="col_w960 col_w960_last">
									<div class="col_w600 float_l">
										<div class="news_box">
											<a href="/megafon/servleti/prikaz/slika?id=${oglas.slikaID}"
												data-lightbox="image-1"
												data-title="${zapis.naslov}-id:${slika}"> <img
												src="/megafon/servleti/prikaz/slika?id=${oglas.slikaID}&x=140&y=100">
											</a>

											<h3>
												<a href="/megafon/servleti/prikaz/oglas?id=${oglas.id}">${oglas.naslov}</a>
											</h3>
											<div class='oglasCijena'>
												<span class="oglasLabela">Cijena: </span> <span
													class="oglasIznos">${oglas.cijena}</span> <br> <span
													class="oglasLabela">Datum objave: </span> <span
													class="oglasIznos">${oglas.datum}</span>
											</div>
											<p>${oglas.opis}</p>

											<a href="/megafon/servleti/prikaz/oglas?id=${oglas.id}"
												class="more">Prikaži</a>

											<div class="cleaner"></div>
										</div>
									</div>
								</div>
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
