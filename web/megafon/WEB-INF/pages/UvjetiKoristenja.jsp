<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/megafon/css/siteStyle.css" />
<script type="text/javascript" src="/megafon/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/megafon/js/jquery.als-1.7.min.js"></script>

<title>Uvjeti korištenja</title>
</head>

<body class="subpage">

	<div id="site_wrapper">
		<jsp:include page="Header.jsp" />

		<div id="site_menu">
			<ul>
				<li><a href="/megafon/servleti/pocetna">Početna</a></li>

				<c:choose>
					<c:when test='${sessionScope["admin"] != null}'>
						<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
						<li class="last"><a href="/megafon/servleti/postavkeRacuna">Upravljanje</a></li>
					</c:when>
					<c:when test='${sessionScope["user"] != null}'>
						<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
						<li><a href="/megafon/servleti/dodajOglas">Dodaj oglas</a></li>
						<li class="last"><a href="/megafon/servleti/postavkeRacuna">Moji
								podaci</a></li>
					</c:when>

					<c:when
						test='${sessionScope["user"] == null && sessionScope["admin"] == null}'>
						<li><a href="/megafon/servleti/info/onama" class="current">O
								nama</a></li>
						<li><a href="/megafon/servleti/pretraga">Pretraživanje</a></li>
						<li class="last"><a href="/megafon/servleti/login">Prijava</a></li>
					</c:when>

				</c:choose>
			</ul>
			<jsp:include page="MaliSearchbar.jsp" />

			<div class="cleaner"></div>
		</div>
		<!-- end of site_menu -->

		<div id="site_middle_subpage">

			<h2>Pravila i uvjeti korištenja</h2>
			<p>Stranice portala za oglašavanje Megafon u vlasništvu su
				društva Megafon d.o.o.</p>

		</div>

		<div id="site_main"></div>
		<ol>
			<li>Pristupanjem ovim stranicama, prihvaćate sva pravila i
				uvjete korištenja stranica. Zadržavamo pravo promjene i modifikacije
				uvjeta korištenja ovih stranica u bilo kojem trenutku. Promjene
				stupaju na snagu u trenutku objave. Korisnik je dužan redovito
				čitati pravila i uvjete korištenja te se smatra da je pristupanjem
				stranicama Megafon.hr upoznat s aktualnim pravilima i uvjetima
				njihova korištenja.</li>
			<li>U svrhu oglašavanja na Megafon.hr, korisnik se obvezuje
				registrirati te u registracijskom obrascu popuniti svoje osobne
				podatke i podatke za kontakt. Prikupljanje traženih podataka
				utemeljeno je Zakonom o zabrani i sprečavanju obavljanja
				neregistrirane djelatnosti. Korisnik za točnost podataka jamči
				zakonskom odgovornošću. Sukladno Zakonu o zaštiti osobnih podataka,
				prikupljeni osobni podaci pohranjuju se u zbirci osobnih podataka
				koje je voditelj Megafon d.o.o.</li>
			<li>Svaki korisnik prihvaćanjem ovih pravila dopušta uporabu i
				obradu navedenih podataka u svrhu djelovanja sustava te za slanje
				elektroničke pošte, u skladu s 20. točkom ovih pravila, a Megafon.hr
				se obvezuje da će te podatke koristiti samo u svrhe za koje su
				dobiveni.</li>
			<li>Sve objave, oglasi, poruke, tekstovi, prikazi, fotografije,
				videosnimke i drugi materijali (u daljnjem tekstu: sadržaj)
				objavljeni na stranicama, preneseni preko stranica ili linkani sa
				stranica isključiva su odgovornost osobe od koje je takav sadržaj
				potekao. Megafon.hr ne daje nikakva jamstva glede točnosti,
				potpunosti ili autentičnosti takvog sadržaja. U slučaju bilo kakvih
				zahtjeva trećih osoba s osnovom naknade štete prema izdavaču
				internet portala Megafon.hr, a po bilo kojoj osnovi vezanoj uz
				dostavljeni sadržaj, korisnik preuzima odgovornost, kao i obvezu
				poduzeti sve radnje kako bi obranio i zaštitio izdavača Megafon
				d.o.o. od svih takvih zahtjeva. Korisnik se obvezuje nadoknaditi
				Megafonu d.o.o. svaku štetu koja je nastala temeljem zahtjeva trećih
				osoba, po bilo kojoj osnovi vezanoj uz dostavljeni materijal.</li>
			<li>Zabranjeno je naručivanje i objavljivanje sadržaja koji su
				nezakoniti, štetni, prijeteći, koji zlostavljaju, uznemiravaju,
				kleveću ili su na bilo koji način štetni malodobnim osobama.
				Nedopušteni su sadržaji koji sadrže osobne podatke drugih osoba bez
				njihova izričitog odobrenja. Korisnik se obvezuje da neće
				objavljivati oglase koji su u suprotnosti sa zakonom, ostalim
				propisima i Ustavom Republike Hrvatske. Korisnik se posebno obvezuje
				da neće prodavati i oglašavati prodaju robe ili pružanje usluga
				kojima bi se kršila autorska i srodna prava trećih osoba.</li>
			<li>Svi eventualni sporovi nastali iz odnosa korisnika i
				oglašivača (nastali zbog istinitosti podataka o korisniku ili
				oglašivaču, sadržaju ponude, o predmetu i cijeni ponude i sl.)
				rješavaju se isključivo između korisnika i oglašivača. Megafon.hr
				nije odgovoran ni za kakvu štetu bilo koje vrste, nastale kao
				posljedica takvih odnosa.</li>
			<li>Oglasi moraju biti napisani na hrvatskom jeziku.</li>
			<li>Oglas mora sadržavati specifične podatke o predmetu koji se
				prodaje. Opis se mora odnositi samo na predmet prodaje. U oglasima
				nije dopušteno oglašavanje ostalih predmeta, usluga, potražnje ili
				bilo čega drugog što nije direktno predmet prodaje. Korisnici koji
				na sustavu Megafon.hr imaju otvorenu Megafon trgovinu ili Megafon
				uslugu mogu upisati dodatne podatke o svojoj fizičkoj ili web
				trgovini u polja koja su za to namijenjena.</li>
			<li>Slike u oglasu moraju biti slike predmeta koji se prodaje.
				Nije dopuštena objava logotipa, banera i sl.</li>
			<li>Liste proizvoda nisu dopuštene. U jedan oglas dopušteno je
				upisati samo jedan predmet prodaje, osim ako ne predstavlja komplet.
				(Primjer: u jednom oglasu dopušteno je prodavati "stol i četiri
				stolice", ali nije dopušteno zajedno prodavati "skije i motocikl".)</li>
			<li>Oglas mora sadržajno pripadati u primjerenu rubriku.</li>
			<li>Naslov oglasa mora opisivati predmet prodaje. U naslov nije
				dopušteno upisivati naslove drugih web stranica ili više jednakih
				znakova zaredom (npr.: *** motor za čamac ***).</li>
			<li>Oglasi koji pozivaju na sudjelovanje u raznim mrežnim
				marketinzima i piramidalnim igrama nisu dopušteni. Jednako tako,
				nisu dopušteni oglasi koji pozivaju na igranje nedopuštenih igara na
				sreću.</li>
			<li>Korisnik može imati samo jedno korisničko ime i za jedan
				predmet može predati samo jedan oglas. Jednako tako, nije dopuštena
				objava oglasa za isti predmet u dvije različite rubrike.</li>
			<li>Pravne osobe, obrtnici, udruge i agencije mogu putem
				Megafon.hr prodavati i predavati oglase tako da otvore Megafon
				trgovinu. Ako obavljaju uslužnu djelatnost mogu je predstaviti u
				Megafon uslugama.</li>
			<li>Internetske veze (linkovi) sa stranica Megafon.hr: dopušteno
				je jedino povezivanje na dodatni opis predmeta. Linkovi na stranice
				koje oglašavaju razne usluge nisu dopušteni. Samo oni korisnici koji
				na Megafon.hr imaju otvorenu Megafon trgovinu mogu stavljati linkove
				i na vlastitu web trgovinu koju imaju još dodatno postavljenu izvan
				okvira Megafon.hr. Veze na stranice koje su konkurentske Megafonu.hr
				nisu dopuštene. Megafon.hr zadržava pravo procjene hoće li neki link
				odobriti ili neće.</li>
			<li>Megafon.hr zadržava pravo brisanja, odnosno neobjavljivanja
				oglasa koji ne zadovoljavaju određeni kriterij.</li>
			<li>Megafon.hr ima pravo urediti, ili izmijeniti ili ne objaviti
				oglase i to bez najave, a to će se dogoditi u sljedećim okolnostima:
				<ul style="list-style-type: disc !important;">
					<li>kad je oglas predan u pogrešnu rubriku</li>
					<li>kad proizvod i/ili oglašivač krši autorska prava i/ili
						druga prava intelektualnog vlasništva</li>
					<li>kad proizvod može uzrokovati štetu i/ili prekršiti prava
						intelektualnog vlasništva nekom drugom, na bilo koji način</li>
					<li>kad proizvod i/ili sadržaj oglasa sadrži diskriminirajuće
						aspekte, (dječje) pornografske materijale ili po hrvatskom zakonu
						ilegalne materijale</li>
					<li>kad sadržaj jednog oglasa sadrži više proizvoda ili usluga
						koji nisu raspoređeni u ispravne kategorije</li>
					<li>kad je oglas u bilo kojem smislu uvredljiv</li>
					<li>kad je oglas sumnjiv u smislu oglašavanja ukradene ili
						ilegalne robe</li>
					<li>kad oglas na bilo koji način narušava kvalitetu sadržaja</li>
					<li>kad se oglašava potražnja</li>
					<li>Megafon.hr zadržava pravo izmjene i dopune pojedinog
						oglasa, a da se pri tome ne mijenja sadržaj predmetnog oglasa
						(lektorske intervencije, dopuna podataka i slično).</li>
				</ul>
			</li>
			<li>Korisnik je dužan brinuti se o sigurnosti svoje korisničke
				lozinke i povremeno je mijenjati. Megafon.hr ne odgovara za
				slučajeve zlouporabe korisničke lozinke, ali će odmah nakon što
				korisnik javi o vjerojatnoj zlouporabi odgovarajuće postupati.</li>
			<li>Objavom oglasa na Megafon.hr korisnik se obvezuje da će
				savjesno odgovarati na upite ostalih korisnika (na telefon ili
				e-mail). Korisnik mora imati aktivnu e-mail adresu gdje povremeno
				pregledava poštu.</li>
			<li>Korisnik se slaže s povremenim primanjem elektroničke pošte
				koja je povezana s aktivnostima Megafon.hr. Elektronička pošta može
				povremeno sadržavati reklamne poruke. Korisnik u bilo koje vrijeme
				može pismeno zahtijevati da Megafon.hr trajno ili privremeno
				prestane koristiti njegove osobne podatke za slanje poruka. U tom
				razdoblju Megafon.hr korisniku zamrzava njegovu registraciju (dakle,
				to znači da korisnik više neće moći upotrebljavati funkcije
				Megafon.hr koje su namijenjene samo registriranim korisnicima).</li>
			<li>Korisnici svojim zahtjevom za objavom oglasa neopozivo
				ovlašćuju Megafon.hr da taj sadržaj koristi, kopira, objavljuje te
				distribuira i u drugim medijima. Megafon.hr u drugim medijima neće
				objaviti korisnikove osobne podatke, nego samo sadržaj oglasa i link
				na stranicu Megafon.hr.</li>
			<li>Nije dopušteno kopiranje i uporaba bilo kojeg dijela
				stranice Megafon.hr, uključivo sa svim podacima koje daju korisnici,
				bez prethodnog pismenog dopuštenja Megafona d.o.o.</li>
			<li>Megafon.hr zadržava pravo trenutno s popisa korisnika
				brisati one korisnike koji bi kršili ova pravila i uvjete ili na
				neki drugi način usporavali ili ometali rad stranica. Megafon d.o.o.
				zadržava pravo poduzeti odgovarajuće mjere protiv takvih korisnika.
				Uvjeti i odnosi između korisnika i društva Megafon d.o.o. podliježu
				zakonima Republike Hrvatske. U slučaju spora nadležan je stvarno
				nadležni sud u Zagrebu.</li>
			<li>Megafon d.o.o. ne odgovara za bilo kakvu štetu koja bi mogla
				nastati zbog prekida dostupnosti, neobjavljivanja, odnosno brisanja
				oglasa ili pogrešaka u djelovanju web stranica.</li>
			<li>Megafon.hr ne jamči da će korisnik moći pristupati do
				sadržaja svojeg oglasa kad on jednom istekne. Stoga se preporučuje
				da korisnik sam kod sebe pohrani sadržaj i slike oglasa, ako će ih
				htjeti kasnije upotrijebiti u nekom drugom kontekstu.</li>
			<li>Za analizu svoje internetske stranice koristimo software za
				analizu pomoću kojeg analiziramo korištenje internetske stranice te
				time stječemo dragocjena saznanja o potrebama svojih korisnika, a s
				ciljem poboljšavanja kvalitete naše ponude. S tim u vezi koriste se
				i tzv. kolačići. Kolačići su tekstovne datoteke koje se pohranjuju
				na računalu posjetitelja internetske stranice. Oni omogućavaju
				prepoznavanje korisnika prigodom ponovnog posjeta stranici. To nam
				omogućava provođenje gore opisane analize korištenja. Kolačići se
				inače mogu, putem odgovarajućih postavki na pregledniku, i odbiti
				ili izbrisati. Međutim, to može dovesti do toga da se neke funkcije
				naše internetske stranice više ne mogu u potpunosti koristiti.
				Navedeni software, osim toga, prikuplja i pohranjuje i neke tehničke
				podatke, uključujući IP adresu korisnika. Naglašavamo da, ni u kom
				slučaju, ne dolazi do identifikacije osoba koje stoje iza tih
				podataka ni do prikupljanja osobnih podataka. Ti podaci se također
				ne povezuju s osobnim podacima koje je korisnik eventualno obznanio.
				Korištenjem ove internetske stranice dajete svoju suglasnost za gore
				opisani način provođenja analize korištenja ove internetske
				stranice.</li>
			<li>Sve primjedbe uz 8. članak Zakona o zaštiti potrošača možete
				dostaviti u pisanom obliku na adresu sjedišta društva, Blabla 3 d,
				Zagreb.</li>
		</ol>
		<h4>Osnovni podaci o tvrtci</h4>

		<table>
			<tr>
				<td><label>Naziv tvrtke: </label></td>
				<td>${pravna.naziv}</td>
			</tr>

			<tr>
				<td><label>Tvrtka: </label></td>
				<td>Megafon d.o.o.</td>
			</tr>
			<tr>
				<td><label>Sjedište: </label></td>
				<td>Izmišljena Adresa 1024, 10000 Zagreb</td>
			</tr>
			<tr>
				<td><label>Sud registracije: </label></td>
				<td>Društvo nije registrirano</td>
			</tr>

			<tr>
				<td><label>MBS: </label></td>
				<td>31415926</td>
			</tr>

			<tr>
				<td><label>OIB: </label></td>
				<td>27182818284</td>
			</tr>

			<tr>
				<td><label>Broj računa: </label></td>
				<td>3141592-2718281828, račun otvoren kod Izmišljena Banka d.d.</td>
			</tr>

			<tr>
				<td><label>IBAN: </label></td>
				<td>HR1234567891011121314</td>
			</tr>

			<tr>
				<td><label>Temeljni kapital: </label></td>
				<td>6.384,00 kn, uplaćen u cijelosti</td>
			</tr>

			<tr>
				<td><label>Vlasnik: </label></td>
				<td>Random Ime</td>
			</tr>
		</table>


		<!-- end of main -->

	</div>
	<!-- end of wrapper -->
	<jsp:include page="Footer.jsp" />

</body>
</html>
