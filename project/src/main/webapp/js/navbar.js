
function scrollToContacts() {
			window.location.href = 'index.jsp';
            var element = document.getElementById("contacts");
            element.scrollIntoView({ behavior: "smooth", block: "end", inline: "nearest" });
 }