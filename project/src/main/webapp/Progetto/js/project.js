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

