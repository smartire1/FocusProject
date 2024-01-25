function showContent(contentId) {
	var activeElements = document.querySelectorAll('.active');
	activeElements.forEach(function(element) {
		element.classList.remove('active');
	});
	var contentDiv = document.getElementById(contentId);
	contentDiv.classList.add('active');
}

// Funzione per chiudere la notifica
function closeNotification() {
	document.getElementById("notification").style.display = "none";
}

setTimeout(function() {
	closeNotification();
}, 5000);


document.addEventListener("DOMContentLoaded", function() {

	$("#dalGiorno").datepicker({
	    minDate: 0,
	    dateFormat: 'yy-mm-dd',
	    onSelect: function(dateText, inst) {

	    }
	});
	
		$("#alGiorno").datepicker({
	    minDate: 0,
	    dateFormat: 'yy-mm-dd',
	    onSelect: function(dateText, inst) {

	    }
	});
		              
});


