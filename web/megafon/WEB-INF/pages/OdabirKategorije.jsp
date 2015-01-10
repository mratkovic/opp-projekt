<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Stvaranje Kategorije</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />


			<div id="site_menu">
				<ul>
					<li><a href="/megafon/servleti/pocetna">Početna</a></li>
					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li><a href="/megafon/servleti/dodajOglas" class="current">Dodaj
							oglas</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna">Moji
							podaci</a></li>

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
				<h2>Dodavanje novog oglasa</h2>
				<p>
					Odaberite kategoriju u kojoj zelite stvoriti oglas. <br>
				</p>
			</div>

			<div id="site_main">

				<form action="/megafon/servleti/dodajOglas" method="get">
					<table>
						<tr>
							<td class=firstCol><label for="kategorija">Kategorija:</label>
							</td>
							<td><select name="kategorija" size="1">
									<c:forEach var="tip" items="${kategorije}">
										<option value='${tip.id}'>${tip.naziv}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" name="metoda" value="Nastavak">
							</td>
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
