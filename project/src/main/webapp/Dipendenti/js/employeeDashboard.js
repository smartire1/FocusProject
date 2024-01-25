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
    const closeBtns = document.querySelectorAll(".close-popup-btn");    
   
    const popup = document.getElementById("Popup");
    const overlay = document.getElementById("Overlay");

    openBtns.forEach(function(btn) {
        btn.addEventListener("click", () => {			
            popup.style.display = "block";
            overlay.style.display = "block";
        });
    });

    closeBtns.forEach(function(btn) {
        btn.addEventListener("click", () => {			
            popup.style.display = "none";
            overlay.style.display = "none";
        });
    });
      
	$("#datePicker").datepicker({
	    minDate: 0,
	    dateFormat: 'yy-mm-dd',
	    onSelect: function(dateText, inst) {

	    }
	});
	              
});

function mostraForm(classeForm) {
    // Seleziona tutti gli elementi con la classe specificata
    var forms = document.getElementsByClassName(classeForm);

    // Itera su tutti gli elementi selezionati e cambia lo stile di visualizzazione
    for (var i = 0; i < forms.length; i++) {
        var form = forms[i];
        form.style.display = (form.style.display === 'none') ? 'block' : 'none';
    }
}

function toAddUser(email) {
	document.getElementById("toAdd").value = email;	
}

function verificaOrario() {
    var oraInizio = document.getElementById("oraInizio").value;
    var oraFine = document.getElementById("oraFine").value;

    if (oraInizio >= oraFine) {
        alert("L'ora di inizio non puo' essere maggiore o uguale all'ora di fine.");
    } else {
        alert("Orario valido!");
        // Puoi eseguire altre azioni qui se l'orario è valido
    }
}
