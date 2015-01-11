<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="search_box">
	<form action="/megafon/servleti/pretraga" method="post">
		<input type="text" value="Pretraga oglasa" name="naziv" size="16"
			id="searchfield" title="Naslov" onfocus="clearText(this)"
			onblur="clearText(this)" /> <input type="submit" name="Search"
			value="" id="searchbutton" title="Pretrazi" />
	</form>
</div>
