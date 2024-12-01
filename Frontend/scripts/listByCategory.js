function listByCategory(category) {

    // Utworzenie zmiennej przechowującej iframe
    const iframe = document.getElementById("podcast-by-category-frame");

    // Ukrycie sekcji odpowiedzialnej za ostatnio opublikowane podcasty
    const content_section = document.getElementById("content-section");
    content_section.style.display = "none";

    // Utworzenie nowego obiektu klasy XMLHttpRequest
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Otwarcie połączenia
    xhr.open('GET', url + '/find/category/' + category, true)

    xhr.onload = () => {

        var response = JSON.parse(xhr.responseText);

        iframe.src = "HTML files/podcastsByCategory.html";

        iframe.onload = () => {
            try {
                // Dostęp do iframe
                const iframeDocument = iframe.contentDocument

                // Dodanie do nagłówka iframe'u nazwy karegorii
                const categoryName = iframeDocument.getElementById("category-name");
                categoryName.textContent += category;

                // Pobranie kontenera wiersza
                const rowContainer = iframeDocument.querySelector('.content-row');

                // Jeśli jest jakiś podcast do wyświetlenia, to ... (to trzeba zmienić, bo nie działa)
                if (response) {

                    // Pętla for each iterująca po wszystkich wynikach z JSONa
                    response.forEach(item => {

                        // Tworzenie elementu HTML (kolumny)
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
                    })

                } else {
                    console.error("Brak podcastów do wyświetlenia.");
                }
            } catch (error) {
                console.error("Brak dostępu do zawartości iframe.", error);
            }
        }

    }
    xhr.send()

}