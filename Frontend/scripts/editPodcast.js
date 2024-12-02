const tagInput = document.getElementById('tag-input');
const tagsContainer = document.getElementById('tags-container');
const form = document.getElementById('dashboard-add-form');

let tags = [];

// Funkcja dodająca tag
function addTag(tag) {
    if (tag && !tags.includes(tag)) {
        tags.push(tag);
        renderTags();
        tagInput.value = '';
    }
}

// Funkcja usuwająca tag
function removeTag(tagToRemove) {
    tags = tags.filter(tag => tag !== tagToRemove);
    renderTags();
}

// Funkcja renderująca tagi
function renderTags() {
    tagsContainer.innerHTML = ''; // Wyczyść istniejące tagi
    tags.forEach(tag => {
        const tagElement = document.createElement('div');
        tagElement.className = 'tag';
        tagElement.innerHTML = `${tag} <span class="remove-tag" onclick="removeTag('${tag}')">&times;</span>`;
        tagsContainer.appendChild(tagElement);
    });
}

// Obsługa zdarzenia dodawania tagu po Enter
tagInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        addTag(tagInput.value.trim());
    }
});

function getPodcastIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id'); // Pobiera wartość parametru "id"
}

const podcastId = getPodcastIdFromUrl();

function fetchPodcastFile(id) {
    const url = `http://localhost:8080/rest/podcasts/stream/${id}`; // Endpoint backendu

    const xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'blob'; // Oczekujemy odpowiedzi jako Blob
    xhr.onload = function () {
        if (xhr.status === 200) {
            const blob = xhr.response;

            // Twórz obiekt File i przypisz go do inputa plikowego
            const fileInput = document.getElementById('file');
            const file = new File([blob], "plik.mp4", { type: blob.type });

            const dataTransfer = new DataTransfer();
            dataTransfer.items.add(file);
            fileInput.files = dataTransfer.files;
        } else {
            console.error(`Błąd pobierania pliku: ${xhr.statusText}`);
        }
    };
    xhr.onerror = function () {
        console.error('Błąd żądania pliku.');
    };
    xhr.send();
}

function fetchPodcastDetails(id) {
    const xhr = new XMLHttpRequest();
    const url = `http://localhost:8080/rest/podcasts/find/${id}`; // Endpoint backendu

    xhr.open('GET', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function () {
        if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText); // Parsuj dane z backendu

            // Wypełnij formularz danymi podcastu
            document.getElementById('title').value = data.title;
            document.getElementById('description').value = data.description;
            document.getElementById('category').value = data.category.toLowerCase();
            const fileId = data.file_id;
            fetchPodcastFile(fileId);
            // Renderuj tagi
            const tagsContainer = document.getElementById('tags-container');
            tagsContainer.innerHTML = '';
            data.tags.forEach(tag => {
                addTag(tag); // Funkcja z Twojego wcześniejszego kodu
            });
        } else {
            console.error('Nie udało się pobrać danych podcastu:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Błąd połączenia z backendem.');
    };

    console.log(document.getElementById('category').value);
    xhr.send();
}

console.log(podcastId);
fetchPodcastDetails(podcastId);

const fileInput = document.getElementById('file');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = document.getElementById('title').value.trim();
    const description = document.getElementById('description').value.trim();
    const category = document.getElementById('category').value.trim();
    const file = fileInput.files[0];

    const updatePostRequestDTO = {
        id: podcastId,
        title: name,
        description: description,
        category: category,
        tags: tags
    };

    const formData = new FormData();
    formData.append('updatePostRequestDTO', new Blob([JSON.stringify(updatePostRequestDTO)], { type: 'application/json' })); // JSON jako Blob
    formData.append('file', file);

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    xhr.open('PUT', url + '/update', true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            alert('Dane zostały zaaktualizowane pomyślnie!');
            console.log(xhr.responseText);
            window.location.href = "../HTML files/dashboard.html";
        } else {
            alert('Wystąpił błąd podczas aktualizacji danych.');
            console.error('Błąd: ', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Błąd żądania');
    };

    xhr.send(formData);

});