function listByTitle() {

    // Przypisanie do zmniennej frazy, którą użytkownik wpisuje do paska wyszykiwania
    const search_bar_text = document.getElementById("podcast-search-bar").value;

    // Utworzenie zmiennej przechowującej iframe
    const iframe = document.getElementById("podcast-iframe");

    // Ukrycie sekcji odpowiedzialnej za ostatnio opublikowane podcasty
    const content_section = document.getElementById("content-section");
    content_section.style.display = "none";

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Otwarcie połączenia
    xhr.open('GET', url + '/find/title/' + search_bar_text, true)

    xhr.onload = () => {
        var response = JSON.parse(xhr.responseText);

        iframe.src = "HTML files/iframePlaceholder.html";

        iframe.onload = () => {
            try {

                // Dostęp do iframe
                const iframeDocument = iframe.contentDocument;

                // Dodanie do nagłówka iframe'u frazy
                const categoryName = iframeDocument.getElementById("iframe-header");
                categoryName.textContent = "Wyniki wyszukiwania:"

                // Pobranie kontenera wiersza
                const rowContainer = iframeDocument.querySelector('.content-row');

                // Sprawdzenie odpowiedzi
                if (response && Array.isArray(response)) {
                    response.forEach(item => {
                        // Tworzenie elementu HTML
                        const contentColumn = iframeDocument.createElement('div');
                        contentColumn.className = 'content-column';

                        contentColumn.innerHTML = `
                      <div class="content-box" onclick="window.open('podcastDetails.html?id=${item.id}')">
                        <h5>` + item.title + `</h5>
                        <p>` + item.created_at + `</p>
                      </div>
                    `;

                        // Dodanie kolumny do kontenera wiersza
                        rowContainer.appendChild(contentColumn);
                    });
                } else {
                    console.error("Brak danych lub niepoprawna odpowiedź z serwera.");
                }
            } catch (error) {
                console.error("Brak dostępu do zawartości iframe.", error);
            }
        };
    };


    xhr.send()
}
