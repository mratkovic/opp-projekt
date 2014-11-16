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
				<h2>Stvaranje nove kategorije oglasa</h2>
				<p>
					Stvaranje nove kategorije oglasa. <br> Potrebno je ispuniti
					zadanu formu za stvaranje kategorije.
				</p>
			</div>

			<div id="site_main">

				<form action="/megafon/servleti/stvoriKategoriju" method="post">
					<table>
						<tr>
							<td class=firstCol><label for="naziv">Naziv
									kategorije:</label></td>
							<td><input type="text" name="naziv" value='${zapis.naziv}'
								size=40> <c:if test="${zapis.hasError('naziv')}">
									<div class="greska">${zapis.getError('naziv')}</div>
								</c:if></td>
						</tr>
						<tr>

							<td class=firstCol><label for="nadkategorije">Nadkategorija:</label></td>
							<td><select name="kategorija" size="1">
									<c:forEach var="tip" items="${kategorija}">
										<option value='${tip.id}'>${tip.naziv}
									</c:forEach>
							</select> <c:if test="${zapis.hasError('nadkategorija')}">
									<div class="greska">${zapis.getError('nadkategorija')}</div>
								</c:if></td>
						</tr>
						<tr>
							<td class=firstCol><label for="nadkategorije">Besplatna:</label></td>
							<td><input type="checkbox" name="jeBesplatna" value="true">
								<c:if test="${zapis.hasError('jeBesplatna')}">
									<div class="greska">${zapis.getError('jeBesplatna')}</div>
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
