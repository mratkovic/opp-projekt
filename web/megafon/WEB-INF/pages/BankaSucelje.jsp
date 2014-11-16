<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Uplata</title>
<link rel="stylesheet" href='/megafon/css/style.css' />
</head>

<body>
	<div class=content>
		<h1>Uplata</h1>
		<form action="/megafon/servleti/bankaUplata" method="post">
		
			<input type="hidden" name="id_oglasivac" value= '${user.id}' size=10>
			<input type="hidden" name="id_novi_tip" value= '${id_novi_tip}' size=10>
			
			<p>Informacije o prodavaču: ${seller}</p>
			<p>Informacije o kupcu: ${buyer}</p>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				style="clear: both; color: #666666 !important; font-family: arial, helvetica, sans-serif; font-size: 18px"
				width="100%">
				<tbody>
					<tr>
						<td
							style="border: 1px solid #ccc; border-right: none; border-left: none; padding: 5px 10px 5px 10px !important; color: #333333 !important"
							width="350" align="left">Stavka</td>
						<td
							style="border: 1px solid #ccc; border-right: none; border-left: none; padding: 5px 10px 5px 10px !important; color: #333333 !important"
							width="100" align="right">Cijena po komadu</td>
						<td
							style="border: 1px solid #ccc; border-right: none; border-left: none; padding: 5px 10px 5px 10px !important; color: #333333 !important"
							width="50" align="right">Količina</td>
						<td
							style="border: 1px solid #ccc; border-right: none; border-left: none; padding: 5px 10px 5px 10px !important; color: #333333 !important"
							width="80" align="right">Iznos</td>
					<tr>
						<td style="padding: 10px" align="left">${item}<br>
						</td>
						<td style="padding: 10px" align="right">${price}</td>
						<td style="padding: 10px" align="right">1</td>
						<td style="padding: 10px" align="right">${total}</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td></td>
					<td><input type="submit" name="metoda" value="Potvrdi uplatu">
						<input type="submit" name="metoda" value="Odustani"></td>
				</tr>
			</table>

		</form>


	</div>
</body>
</html>