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
				<jsp:include page="MaliSearchbar.jsp" />
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
						<c:when test="${not empty premium}">
							<h3>Izdvojeni oglasi ove kategorije</h3>
							<div id="lista1" class="als-container">

								<div class="als-viewport">
									<div class="als-wrapper">

										<c:forEach var="oglas" items="${premium}">
											<div class="als-item">
												<div class="lp_box lp_box_last">

													<h6>
														<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
													</h6>
													<a
														href="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=400&y=300"
														data-lightbox="image-1"
														data-title="${zapis.naslov}-id:${slika}"> <img
														src="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=290&y=140">
													</a>


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

											<div class="als-item">
												<div class="lp_box lp_box_last">

													<h6>
														<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
													</h6>
													<a
														href="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=400&y=300"
														data-lightbox="image-1"
														data-title="${zapis.naslov}-id:${slika}"> <img
														src="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=290&y=140">
													</a>


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
											<div class="als-item">
												<div class="lp_box lp_box_last">

													<h6>
														<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
													</h6>
													<a
														href="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=400&y=300"
														data-lightbox="image-1"
														data-title="${zapis.naslov}-id:${slika}"> <img
														src="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=290&y=140">
													</a>


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
										</c:forEach>

									</div>
								</div>
								<span class="als-next"><img
									src="/megafon/images/thin_right_arrow_333.png" alt="next"
									title="next" /></span> <span class="als-prev"><img
									src="/megafon/images/thin_left_arrow_333.png" alt="prev"
									title="previous" /></span>
							</div>
							<div class="cleaner"></div>
						</c:when>
					</c:choose>



					<div class="cleaner"></div>
					<br>
					<c:choose>
						<c:when test="${!rezultati.isEmpty()}">
							<h3 id='naslovVeci'>Rezultati pretrage</h3>
							<br>
							<c:forEach var="oglas" items="${rezultati}">


								<div class="col_w960 col_w960_last">
									<div class="col_w600 float_l">
										<div class="news_box">
											<a href="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}"
												data-lightbox="image-1"
												data-title="${zapis.naslov}-id:${slika}"> <img
												src="/megafon/servleti/prikaziSliku?id=${oglas.slikaID}&x=140&y=100">
											</a>

											<h3>
												<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}">${oglas.naslov}</a>
											</h3>
											<div class='oglasCijena'>
												<span class="oglasLabela">Cijena: </span> <span
													class="oglasIznos">${oglas.cijena}</span> <br> <span
													class="oglasLabela">Datum objave: </span> <span
													class="oglasIznos">${oglas.datum}</span>
											</div>
											<p>${oglas.opis}</p>

											<a href="/megafon/servleti/prikaziOglas?id=${oglas.id}"
												class="more">Više</a>

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
