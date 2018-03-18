<html>
<head>
<meta http-equiv="Content-Type" content="text/html" ; charset=ISO-8859-1">
<title>Kalkulator Kredytowy</title>
</head>
<body>
	<form action="kalkulator" method="post">
		<label>Kwota kredytu:<input type="number" id="kwotakredytu" name="kwotakredytu" min="1000" /></label> 
		<label>Ilosc rat:<input type="number" id="iloscrat" name="iloscrat" min="2"/></label>
		<label>Oprocentowanie:<input type="number" id="oprocentowanie" name="oprocentowanie" min="0" step="0.05"/></label>
		<label>Oplata stala:<input type="number" id="oplatastala" name="oplatastala" min="0" step="0.01"/></label>
		<select id="rodzajrat" name="rodzajrat">
 		<option value="stala">Stala</option>
  		<option value="malejaca">Malejaca</option>
		</select> 
		<input type="submit" value="Przeslij">
		<button type="submit" formtarget="_blank" formaction="/pdf">Generuj PDF</button>
		<input type="reset"/>
	</form>
</body>
</html>