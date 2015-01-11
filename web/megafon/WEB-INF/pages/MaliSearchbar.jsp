<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="search_box">
	<form action="/megafon/servleti/pretraga" method="post">
		<input type="text" value="Pretraga oglasa" name="naziv" size="16"
			id="searchfield" title="Naslov" 
			onfocus="clearText(this)"
			onblur="clearText(this)" 
			onblur="if (this.value == '') {this.value = 'Pretraži po naslovu';}"   
			onfocus="if (this.value == 'Pretraži po naslovu') {this.value = '';}"
			
			/> <input type="submit" name="Search"
			value="" id="searchbutton" title="Pretrazi" />
	</form>
</div>
