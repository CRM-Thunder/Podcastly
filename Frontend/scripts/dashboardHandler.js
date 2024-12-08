// Znajdź listę w HTML
const itemList = document.getElementById('item-list');

// Funkcja do usuwania podcastu
function deleteItem(id) {
    if (confirm('Czy na pewno chcesz usunąć ten podcast?')) {
        const xhr = new XMLHttpRequest();
        const url = `http://localhost:8080/rest/podcasts/deleteById?id={id}`;

        // Otwieramy połączenie
        xhr.open('DELETE', url, true);

        // Obsługa odpowiedzi
        xhr.onload = function () {
            if (xhr.status === 200) {
                alert('Podcast został usunięty!');
                document.getElementById(`item-${id}`).remove(); // Usuwamy element z DOM
            } else {
                alert(`Nie udało się usunąć podcastu. Status: ${xhr.status}`);
            }
        };

        // Obsługa błędów
        xhr.onerror = function () {
            console.error('Błąd podczas usuwania podcastu.');
            alert('Wystąpił błąd połączenia z serwerem.');
        };

        // Wysyłamy żądanie
        xhr.send();
    }
}

// Funkcja do pobierania danych podcastu i aktualizacji listy podcastów
function fetchAndUpdateList() {
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"
    xhr.open('GET', url + '/listall', true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);

            itemList.innerHTML = '';

            data.forEach(item => {
                const listItem = document.createElement('li');

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

fetchAndUpdateList();
