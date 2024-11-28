function readPodcastFromDb(){
    // Utworzenie nowego obiektu klasy XMLHttpRequest
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Otwarcie połączenia (url musi być zmieniony na taki, który wyświetla ostatnio opublikowane podcasty)
    xhr.open('GET', url + '/listall', true)

    xhr.onload = () => {
        var response = JSON.parse(xhr.responseText);

        // Pobranie kontenera wiersza
        const rowContainer = document.querySelector('.content-row');

        // Pętla dodająca kolumny
        for (let i = 0; i < 4; i++) {

            // Tworzenie elementu HTML
            const contentColumn = document.createElement('div');
            contentColumn.className = 'content-column';

            let podcast_id = response[i].id

            contentColumn.innerHTML = `
              <div class="content-box" onclick="openPodcast(\'` + podcast_id + `\')">
                <h5>` + response[i].title + `</h5>
                <p>` + response[i].created_at + `</p>
              </div>
            `;

            // Dodanie kolumny do kontenera wiersza
            rowContainer.appendChild(contentColumn);
        }
    }
    xhr.send()

}

function openPodcast(id){
    window.open(`HTML%20files/podcastDetails.html?id=${id}`)
}


readPodcastFromDb()