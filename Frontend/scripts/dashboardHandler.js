// Znajdź listę w HTML
const itemList = document.getElementById('item-list');

function deleteItem(id) {
    if (confirm('Czy na pewno chcesz usunąć ten element?')) {
        fetch(`http://localhost:8080/rest/podcasts/delete/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    alert('Element usunięty!');
                    fetchAndUpdateList();
                } else {
                    alert('Nie udało się usunąć elementu.');
                }
            })
            .catch(error => console.error('Błąd:', error));
    }
}

// Funkcja do pobierania danych i aktualizacji listy
function fetchAndUpdateList() {
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"
    xhr.open('GET', url + '/listall', true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText); // Pobierz dane z API

            // Wyczyść istniejącą zawartość listy
            itemList.innerHTML = '';

            // Iteruj przez dane i twórz elementy listy
            data.forEach(item => {
                // Stwórz element <li>
                const listItem = document.createElement('li');

                // Dodaj strukturę HTML do elementu <li>
                listItem.innerHTML = `
                    <div class="left-block">
                        <h1>${item.title}</h1>
                        <h5>${item.created_at}</h5>
                    </div>
                    <div class="right-block">
                        <button class="button-edit">
                            <a href="edit.html?id=${item.id}" class="logo-link">
                                <img src="../icons/edit-icon.png" width="50px" alt="edit-icon">
                            </a>
                        </button>
                        <button class="button-delete" onclick="deleteItem('${item.id}')">
                            <img src="../icons/delete-icon.png" width="50px" alt="delete-icon">
                        </button>
                    </div>
                `;

                // Dodaj element do listy
                itemList.appendChild(listItem);
            });
        } else {
            console.error('Błąd w żądaniu: ', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Żądanie nie powiodło się.');
    };

    xhr.send();
}

// Wywołaj funkcję po załadowaniu strony
fetchAndUpdateList();
