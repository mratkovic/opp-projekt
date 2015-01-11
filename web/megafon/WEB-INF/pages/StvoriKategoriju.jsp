<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<title>Stvaranje Kategorije</title>
<style type="text/css">
.box {
	display: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("select").change(function() {
			$(".box").hide();
			$("select option:selected").each(function() {
				for (i = 0; i <= 12; i++) {
					if (i <= $(this).attr("value")) {
						$(".stavka" + i).show();
					}

				}
			});
		}).change();
	});
</script>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />
			<div id="site_menu">
				<ul>
					<li><a href="/megafon/servleti/pocetna" class="current">Početna</a></li>

					<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
					<li class="last"><a href="/megafon/servleti/postavkeRacuna"
						class="current">Upravljanje</a></li>

				</ul>
				<jsp:include page="MaliSearchbar.jsp" />
				<div class="cleaner"></div>
			</div>
			<!-- end of site_menu -->

			<div id="site_middle_subpage">
				<h2>Nova kategorija</h2>
				<h3 class='podnaslov'>Za stvaranje nove kategorije potrebno je
					ispuniti sljedeću formu</h3>
			</div>

			<div id="site_main">

				<form action="/megafon/servleti/kategorija" method="post">
					<table>
						<tr>
							<td class=firstCol><label for="naziv">Naziv
									kategorije:</label></td>
							<td><input type="text" name="naziv" value='${zapis.naziv}'
								size=32> <c:if test="${zapis.hasError('naziv')}">
									<div class="greska">${zapis.getError('naziv')}</div>
								</c:if></td>
							<td></td>
						</tr>
						<tr>

							<td class=firstCol><label for="nadkategorije">Nadkategorija:</label></td>
							<td><select name="kategorija" size="1">
									<c:forEach var="tip" items="${kategorije}">
										<option value='kat${tip.id}'>${tip.naziv}
									</c:forEach>
							</select> <c:if test="${zapis.hasError('nadkategorija')}">
									<div class="greska">${zapis.getError('nadkategorija')}</div>
								</c:if></td>
							<td></td>
						</tr>


						<tr>
							<td class=firstCol><label for="jeBesplatna">Besplatna:</label></td>
							<td><input type="checkbox" name="jeBesplatna"
								${zapis.jeBesplatna ?  'checked' : ''} value="true"> <c:if
									test="${zapis.hasError('jeBesplatna')}">
									<div class="greska">${zapis.getError('jeBesplatna')}</div>
								</c:if></td>
							<td></td>
						</tr>

						<tr>
							<td class=firstCol><label for="brStavki">Broj
									dodatnih stavki:</label></td>
							<td><select name="brStavki" size="1">
									<option value='0' selected>0</option>
									<option value='2'>1</option>
									<option value='3'>2</option>
									<option value='4'>3</option>
									<option value='5'>4</option>
									<option value='6'>5</option>
									<option value='7'>6</option>
									<option value='8'>7</option>
									<option value='9'>8</option>
									<option value='10'>9</option>
									<option value='11'>10</option>
							</select></td>
							<td></td>
						</tr>
					</table>
					<div class='stavka2 box dodatneStavkeDiv'>
						<table>
							<tr>

								<td class='title'>Stavka</td>
								<td class='title'>Naziv stavke</td>
								<td class='title'>Tip vrijednosti</td>
							</tr>

							<c:set var="count" value="2" scope="page" />
							<c:set var="i" value="1" scope="page" />
							<c:forEach var="stavka" items="${zapis.dodatneStavke}">


								<tr class="stavka${count} box">
									<td><label>Stavka ${i}</label></td>
									<td><input type="text" name="stavka${i}"
										value='${stavka.first}' size=40></td>
									<td><select name="tipStavke${i}" size="1">
											<option class='tipovi' value='TXT'>tekst</option>
											<option class='tipovi' value='NUM'>broj</option>
									</select></td>
									<c:if test="${zapis.hasError('dodatneStavke')}">
										<div class="greska">${zapis.getError('dodatneStavke')}</div>
									</c:if>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page" />
								<c:set var="i" value="${i + 1}" scope="page" />
							</c:forEach>
							<tr>
								<td></td>
								<td><c:if test="${zapis.hasError('stavke')}">
										<div class="greska">${zapis.getError('stavke')}</div>
									</c:if></td>
							</tr>
						</table>
					</div>
					<table>
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
