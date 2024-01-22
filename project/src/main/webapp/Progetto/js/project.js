// Funzione per mostrare il form specifico nel div dei dipendenti
function mostraFormDipendente(formId) {
	const formContainer = document.getElementById('dipendentiFormContainer');

	// Pulisce il contenuto precedente nel formContainer
	formContainer.innerHTML = '';

	// Crea il form in base all'ID del form richiesto
	let nuovoForm;

	if (formId === 'formDipendente1') {
		nuovoForm = '<form id="formDipendente1">Form Dipendente 1: <input type="text" name="campo1"></form>';
	} else if (formId === 'formDipendente2') {
		nuovoForm = '<form id="formDipendente2">Form Dipendente 2: <input type="text" name="campo2"></form>';
	} else if (formId === 'formDipendente3') {
		nuovoForm = '<form id="formDipendente3">Form Dipendente 3: <input type="text" name="campo3"></form>';
	}

	// Inserisce il nuovo form nel formContainer
	formContainer.innerHTML = nuovoForm;
}

document.addEventListener("DOMContentLoaded", function() {
    const openBtns = document.querySelectorAll(".open-popup-btn");
    const closeBtn = document.getElementById("closeBtn");
    const popup = document.getElementById("Popup");
    const overlay = document.getElementById("Overlay");

    openBtns.forEach(function(btn) {
        btn.addEventListener("click", () => {			
            popup.style.display = "block";
            overlay.style.display = "block";
        });
    });

    closeBtn.addEventListener("click", () => {
        popup.style.display = "none";
        overlay.style.display = "none";
    });
    
});

function toggleButtonDisplay(classNameToShow, classNameToHide) {
    const buttonsToShow = document.querySelectorAll('.' + classNameToShow);
    const buttonsToHide = document.querySelectorAll('.' + classNameToHide);

    buttonsToShow.forEach(function(button) {
        button.style.display = "block";
    });

    buttonsToHide.forEach(function(button) {
        button.style.display = "none";
    });
}

function addButton() {
    toggleButtonDisplay('addButton', 'removeButton');
}

function removeButton() {
    toggleButtonDisplay('removeButton', 'addButton');
}

    function handleEditClick() {		
		
		var idProgetto = document.getElementById("idProject");
        var budgetField = document.getElementById("budgetProgetto");
        var scadenzeField = document.getElementById("scadenzeProgetto");
        var obiettiviField = document.getElementById("testoObiettivi");
        var responsabileField = document.getElementById("responsabileProgetto");
        var descrizioneField = document.getElementById("testoDescrizione");

        budgetField.readOnly = false;
	    scadenzeField.readOnly = false;
	    obiettiviField.readOnly = false;
	    responsabileField.readOnly = false;
	    descrizioneField.readOnly = false;
        toggleElementVisibility("responsabileProgettoBtn");
        toggleElementVisibility("applicaMod");
        toggleElementVisibility("ReloadMod");
        toggleElementVisibility("editButton");
        
        
	    // Aggiungi un listener per l'evento di input sulla textarea
	    //document.getElementById("testoDescrizione").addEventListener("input", updateInputValueNome);
	    document.getElementById("testoDescrizione").addEventListener("input", updateInputValueDesc);
	    document.getElementById("testoObiettivi").addEventListener("input", updateInputValueObbiett);
	    document.getElementById("budgetProgetto").addEventListener("input", updateInputValueBudget);
	    document.getElementById("scadenzeProgetto").addEventListener("input", updateInputValueScadenze);        
        
        alert("Modifiche abilitate!");
        
    }

	function toggleElementVisibility(responsabileProgettoBtn) {
	    var element = document.getElementById(responsabileProgettoBtn);
	    if (element.style.display === "none" || element.style.display === "") {
	        element.style.display = "block";
	    } else {
	        element.style.display = "none";
	    }
	}


/*    function updateInputValueNome() {
        var descrizioneValue = document.getElementById("testoDescrizione").value;
        document.getElementById("newdescrizione").value = descrizioneValue;
    }
*/    
    function updateInputValueDesc() {
        var descrizioneValue = document.getElementById("testoDescrizione").value;
        document.getElementById("newdescrizione").value = descrizioneValue;
    }
    
    function updateInputValueObbiett() {
        var descrizioneValue = document.getElementById("testoObiettivi").value;
        document.getElementById("newobiettivi").value = descrizioneValue;
    } 
           
    function updateInputValueBudget() {
        var descrizioneValue = document.getElementById("budgetProgetto").value;
        document.getElementById("newbudget").value = descrizioneValue;
    }
    
    function updateInputValueScadenze() {
        var descrizioneValue = document.getElementById("scadenzeProgetto").value;
        document.getElementById("newscadenza").value = descrizioneValue;
    }
    





