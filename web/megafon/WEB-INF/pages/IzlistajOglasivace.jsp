<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<title>Svi oglašivači</title>
</head>


<body>
	<div class="subpage">
		<div id="site_wrapper">
			<jsp:include page="Header.jsp" />

			<div id="site_menu">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="gallery.html" class="current">Gallery</a></li>
					<li><a href="news.html">News</a></li>
					<li><a href="blog.html">Blog</a></li>
					<li class="last"><a href="contact.html">Contact</a></li>
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
				<h2>Prikaz svih oglasivaca</h2>
				<p>
					Prikaz svih korisnickih racuna ovog portala. <br> Uredivanje i
					brisaje omoguceno
				</p>

			</div>

			<div id="site_main">
				<c:choose>
					<c:when test="${oglasivaci.isEmpty()}">
						<p>Nema registriranih oglašivača!</p>
					</c:when>

					<c:otherwise>
						<div class=listaj>
							<table>
								<tr>
									<td class=title>Username</td>
									<td class=title>Tip računa</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<c:forEach var="zapis" items="${oglasivaci}">
									<tr>
										<td>${zapis.username}</td>
										<td>${zapis.tipRacuna.naziv}</td>
										<td></td>
										<td><a
											href="/megafon/servleti/prikaziOglasivaca?id=${zapis.id}"
											target="_blank">[Prikaži]</a></td>
										<td><a
											href="/megafon/servleti/izbrisiOglasivac?id=${zapis.id}">[Izbriši]</a></td>
										<td><a
											href="/megafon/servleti/prikaziOglaseOglasivaca?admin=true;id=${zapis.id}"
											target="_blank">[Prikazi sve oglase]</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
			<!-- end of main -->
		</div>
		<!-- end of wrapper -->
	</div>
	<!-- end of subpage -->
	<jsp:include page="Footer.jsp" />

</body>
