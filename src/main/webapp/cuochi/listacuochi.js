$(document).ready(function()
{
	$.ajax({
			url : "http://localhost:8080/cuochi/listacuochijson",
			success : function(result) {
				var lista = JSON.parse(result);
				var ris = "";
				
				ris += "<tr>";
				ris += "<td>Nome</td>" +
					   "<td>Cognome</td>" +
					   "<td>Città</td>" +
					   "<td>Data di nascita</td>" +
					   "<td>Costo orario</td>" +
					   "<td>Valutazione</td>";	
				ris += "</tr>";
				
				for(var i in lista)
					ris += "<tr>" +
						   "<td>" + lista[i].nome + "</td>" +
						   "<td>" + lista[i].cognome + "</td>" +
						   "<td>" + lista[i].citta + "</td>" +
						   "<td>" + lista[i].dob + "</td>" +
						   "<td>" + lista[i].costoOrario + "€</td>" +
						   "<td>" + lista[i].valutazione + "</td>" +
						   "<td>" + "<button class='piatti' id='0" + lista[i].id + "'" + ">Visualizza Piatti</button>" + "</td>" +
					       "<td>" + "<button class='prenotazione' id='" + lista[i].id + "'" + ">Prenota</button>" + "</td>" +
						   "</tr>";
						   
				$("#elenco").html(ris);
				
				$(".prenotazione").click(function() {
					var idcuoco = $(this).attr("id");
					
					$.ajax({
						url : "http://localhost:8080/prenotati/idcliente",
						success : function(result) {
							var idcliente = result;
							
							window.location.href = '/prenotati/formnuovaprenotazione?idcuoco=' + idcuoco + '&idcliente=' + idcliente;
						}
					})
				});
				
				$(".piatti").click(function() {
					var id = $(this).attr("id");
					var idcuoco = id.substring(1,id.length);
					
					window.location.href = '/piatti/visualizzapiatti?idcuoco=' + idcuoco;
				});
			}
	})
	
});