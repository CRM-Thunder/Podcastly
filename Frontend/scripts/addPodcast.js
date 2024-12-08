/*
Wszystkie poniższe trzy funkcje i inne zmienne potrzebne są do poprawnego działania tagów.
*/

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
    tagsContainer.innerHTML = '';
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

/*
Cały poniższy kod odpowiedzialny jest za nasłuchiwanie nacisnięcia przycisku dodawania podcastu
i operacji związanych z dodwaniem podcastu do bazy danych.
 */

const fileInput = document.getElementById('file');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = document.getElementById('name').value.trim();
    const description = document.getElementById('description').value.trim();
    const category = document.getElementById('category').value.trim();
    const file = fileInput.files[0];

    const addPostRequestDTO = {
        title: name,
        description: description,
        category: category,
        tags: tags
    };

    const formData = new FormData();
    formData.append('addPostRequestDTO', new Blob([JSON.stringify(addPostRequestDTO)], { type: 'application/json' }));
    formData.append('file', file);

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    xhr.open('POST', url + '/add', true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            alert('Dane zostały przesłane pomyślnie!');
            console.log(xhr.responseText);
            window.location.href = "../HTML files/dashboard.html";
        } else {
            alert('Wystąpił błąd podczas przesyłania danych.');
            console.error('Błąd: ', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Błąd żądania');
    };

    xhr.send(formData);

});