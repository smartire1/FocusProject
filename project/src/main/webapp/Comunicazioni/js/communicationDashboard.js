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