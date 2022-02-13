$(document).ready(function() {
	var urlParams = new URLSearchParams(window.location.search);
	
	$("#dati").html("<input type='hidden' name='idcliente' value='" + urlParams.get('idcliente') + "'>" +
					"<input type='hidden' name='idcuoco' value='" + urlParams.get('idcuoco') + "'>");
});