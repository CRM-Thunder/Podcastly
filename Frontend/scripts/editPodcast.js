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
            document.getElementById('category').value = data.category;

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

    xhr.send();
}

console.log(podcastId);
fetchPodcastDetails(podcastId);