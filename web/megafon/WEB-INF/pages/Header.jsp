<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="site_header">

	<div id="site_title">
		<h1>
			<a rel="nofollow" href="/megafon/">Megafon</a>
		</h1>
	</div>
	 <div class="userbar">
		<c:choose>
			<c:when test='${sessionScope["logged"] == null}'>
				<li><a class = 'userbarA' href="/megafon/servleti/registracija/oglasivac">Registracija</a></li>
				<li class=separator>|</li>
				<li><a class = 'userbarA'  href="/megafon/servleti/login">Prijava</a></li>
				<li class=separator>|</li>
				<li>Gost</li>
				<li class=separator>|</li>
			</c:when>
			<c:otherwise>
				<li><a class = 'userbarA' href="/megafon/logout">Odjava</a></li>
				<li class=separator>|</li>
				<li>${sessionScope["logged"]}</li>
				<li class=separator>|</li>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="cleaner"></div>
</div>