$(document).ready(function()
{
    $.ajax({
            url : "http://localhost:8080/prenotati/listaprenotazionijson",
            success : function(result) {
                var lista = JSON.parse(result);
                var ris = "";

                ris += "<tr>";
                ris += "<td>ID Cliente</td>" +
                       "<td>Prenotazione</td>" +
                       "<td>Fascia oraria</td>" +
                       "<td>Numero persone</td>" ;
                ris += "</tr>";

                for(var i in lista)
                    ris += "<tr>" +
                           "<td>" + "<a href='/clienti/visualizzacliente?idcliente=" + lista[i].idcliente + "'>" 
									+ lista[i].idcliente + "</href></td>" +
                           "<td>" + lista[i].prenotazione + "</td>" +
                           "<td>" + lista[i].fasciaOraria + "</td>" +
                           "<td>" + lista[i].nPersone + "</td>" +
                           "</tr>";


                $("#elenco").html(ris);

            }
    })

});