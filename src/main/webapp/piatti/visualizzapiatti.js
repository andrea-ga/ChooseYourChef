$(document).ready(function() {
		var urlParams = new URLSearchParams(window.location.search);
	
	    $.ajax({
            url : "http://localhost:8080/piatti/listapiattijson?idcuoco=" + urlParams.get('idcuoco'),
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
						   "</tr>";
			
				$("#elenco").html(ris);
			}

	});
});