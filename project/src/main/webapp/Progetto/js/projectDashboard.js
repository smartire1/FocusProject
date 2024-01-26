function showContent(contentId) {
	// Trova tutti gli elementi con la classe 'active' e rimuovi la classe 'active'
	var activeElements = document.querySelectorAll('.active');
	activeElements.forEach(function(element) {
		element.classList.remove('active');
	});

	// Trova l'elemento div corrispondente al pulsante cliccato
	var contentDiv = document.getElementById(contentId);

	// Aggiungi la classe 'active' al div corrispondente
	contentDiv.classList.add('active');
}

document.addEventListener("DOMContentLoaded", function() {
    const openBtns = document.querySelectorAll(".open-popup-btn");
    const closeBtn = document.querySelectorAll(".close-popup-btn");
    const popup = document.getElementById("Popup");
    const overlay = document.getElementById("Overlay");

    openBtns.forEach(function(btn) {
        btn.addEventListener("click", () => {			
            popup.style.display = "block";
            overlay.style.display = "block";
        });
    });

    closeBtn.forEach(function(btn) {
        btn.addEventListener("click", () => {			
            popup.style.display = "none";
            overlay.style.display = "none";
        });
    });

	$("#scadenza").datepicker({
	    minDate: 0,
	    dateFormat: 'yy-mm-dd',
	    onSelect: function(dateText, inst) {

	    }
	});
		     
});

	function updateInputValueResponsabile(email) {
		document.getElementById("responsabile").value = email;
	}
	
// Funzione per chiudere la notifica
function closeNotification() {
	document.getElementById("notification").style.display = "none";
}

setTimeout(function() {
	closeNotification();
}, 5000);	
	
	