$(document).ready(function()
{
	var urlParams = new URLSearchParams(window.location.search);
	
	$.ajax({
			url : "http://localhost:8080/cuochi/visualizzacuocojson?idcuoco=" + urlParams.get('idcuoco'),
			success : function(result) {
				var elemento = JSON.parse(result);
				var ris = "";
				
				ris += "<tr>";
				ris += "<td>Nome</td>" +
					   "<td>Cognome</td>" +
					   "<td>Città</td>" +
					   "<td>Data di nascita</td>" +
					   "<td>Costo orario</td>" +
					   "<td>Valutazione</td>";	
				ris += "</tr>";
				
				
				ris += "<tr>" +
						   "<td>" + elemento.nome + "</td>" +
						   "<td>" + elemento.cognome + "</td>" +
						   "<td>" + elemento.citta + "</td>" +
						   "<td>" + elemento.dob + "</td>" +
						   "<td>" + elemento.costoOrario + "€</td>" +
						   "<td>" + elemento.valutazione + "</td>" +
						   "</tr>";
			
						   
				$("#elenco").html(ris);
			}
	});
	
});