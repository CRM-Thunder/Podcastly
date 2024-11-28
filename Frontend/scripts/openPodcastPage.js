function openPodcastPage() {

    // Utworzenie nowego obiektu klasy XMLHttpRequest
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Pobranie ID podcastu z URL (...podcastDetails.html?id=...) i przypoisanie do zmiennej
    const params = new URLSearchParams(document.location.search)
    const id = params.get('id')

    // Otwarcie połączenia z funkcją, która pobiera dane podcastu za pomocą ID
    xhr.open('GET', url + '/find/' + id, true)

    xhr.onload = () => {

        // Przypisanie wyniku w formacie JSON do zmiennej
        const response = JSON.parse(xhr.responseText);

        // Przpisanie poszczególnych wartości do osobnych zmiennych
        const title = response.title
        const category = response.category
        const description= response.description
        const created_at = response.created_at
        const modified_at = response.modified_at
        const tags = response.tags
        const file_id = response.file_id

        // Przypisanie tagów do zmiennej string, separator to ','
        let sTags = ""

        tags.forEach(tag => {
            sTags += tag +","
        })
        sTags = sTags.slice(0, -1) // Usunięcie ostatniego przecinka

        // Wstawienie zmeinnych do HTML
        document.getElementById("podcast-title").innerHTML += title;
        document.getElementById("podcast-category").innerHTML += category;
        document.getElementById("podcast-description").innerHTML += description;
        document.getElementById("podcast-created-at").innerHTML += created_at;
        document.getElementById("podcast-modified-at").innerHTML += modified_at;
        document.getElementById("podcast-tags").innerHTML += tags;

    }
    xhr.send()
}

openPodcastPage();