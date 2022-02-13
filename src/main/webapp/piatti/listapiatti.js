$(document).ready(function()
{
    $.ajax({
            url : "http://localhost:8080/piatti/listapiattijson",
            success : function(result) {
                var lista = JSON.parse(result);
                var ris = "";

                ris += "<tr>";
                ris += "<td>Nome</td>" +
                       "<td>Tipo</td>" +
                       "<td>Cucina</td>" +
                       "<td>Allergeni</td>";
                ris += "</tr>";

                for(var i in lista)
                    ris += "<tr>" +
                           "<td>" + lista[i].nome + "</td>" +
                           "<td>" + lista[i].tipo + "</td>" +
                           "<td>" + lista[i].cucina + "</td>" +
                           "<td>" + lista[i].allergeni + "</td>" +
						   "<td>" + "<button class='elimina' id='" + lista[i].id + "'>Elimina</button>" + "</td>" +
						   "</tr>";
			
				$("#elenco").html(ris);
				
				$(".elimina").click(function() {
					var idpiatto = $(this).attr("id");
					
					window.location.href = '/piatti/eliminapiatto?idpiatto=' + idpiatto;
				});
				
				$("#aggiungi").click(function() {
					window.location.href = '/piatti/formnuovopiatto';
				});
			}

	});
});