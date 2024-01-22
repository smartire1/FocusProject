function toggleStats(email) {
	var statsDiv = document.getElementById(email + '_stats');
	if (statsDiv.style.display === 'none') {
		statsDiv.style.display = 'block';
	} else {
		statsDiv.style.display = 'none';
	}
}