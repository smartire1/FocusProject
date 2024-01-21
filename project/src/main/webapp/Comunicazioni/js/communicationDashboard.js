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

// Funzione per chiudere la notifica
function closeNotification() {
	document.getElementById("notification").style.display = "none";
}

// Limite di tempo visualizzazione notifica
setTimeout(function() {
	closeNotification();
}, 5000);