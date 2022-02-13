$(document).ready(function()
{
	var urlParams = new URLSearchParams(window.location.search);
	
	$.ajax({
			url : "http://localhost:8080/clienti/visualizzaclientejson?idcliente=" + urlParams.get('idcliente'),
			success : function(result) {
				var elemento = JSON.parse(result);
				var ris = "";
				
				ris += "<tr>";
				ris += "<td>Nome</td>" +
					   "<td>Cognome</td>" +
					   "<td>Data di nascita</td>";
				ris += "</tr>";
				
				
				ris += "<tr>" +
						   "<td>" + elemento.nome + "</td>" +
						   "<td>" + elemento.cognome + "</td>" +
						   "<td>" + elemento.dob + "</td>" +
						   "</tr>";
			
						   
				$("#elenco").html(ris);
			}
	});
	
});