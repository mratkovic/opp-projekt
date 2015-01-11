<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Pretraga</title>
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
				<h2>Pretraživanje oglasa</h2>
				<p>Pretražite bazu oglasa po željenim kriterijima. Potrebno je
					navesti minimalno jedan kriterij pretrage</p>
			</div>

			<div id="site_main">
				<form action="/megafon/servleti/pretraga" method="post">
					<table>
						<tr>
							<td><input id='main_search' type="text" value="Naziv oglasa" name="naziv"
								size="80" id="searchfield_large" title="searchfield"
								onfocus="clearText(this)" onblur="clearText(this)" /></td>
							<td><input id='main_search_btn' type="submit" name="Search" value=""
								id="searchbutton_large" title="Search" /></td>
						</tr>
					</table>
					<br>
					<table>
						<tr>
							<td class=firstCol><label for="cijena">Raspon
									cijena:</label></td>
							<td>od <input type="text" name="donjaCijena"
								value='${zapis.donjaCijena}' size=10> do <input
								type="text" name="gornjaCijena" value='${zapis.gornjaCijena}'
								size=10> (HRK)
							</td>
						</tr>

						<tr>
							<td class=firstCol><label for="kategorija">Kategorija:</label></td>
							<td><select name="kategorija" size="1">
									<option value=''>Sve kategorije</option>
									<c:forEach var="tip" items="${kategorije}">
										<option value='${tip.id}'>${tip.naziv}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
					<div class="greska">${zapis.getError('greska')}</div>

				</form>
			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
