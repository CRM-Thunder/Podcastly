function readPodcastFromDb(){
    // Utworzenie nowego obiektu klasy XMLHttpRequest
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Otwarcie połączenia (url musi być zmieniony na taki, który wyświetla ostatnio opublikowane podcasty)
    xhr.open('GET', url + '/listall', true)

    xhr.onload = () => {
        var response = JSON.parse(xhr.responseText);

        //console.log(response);

        // Pobranie kontenera wiersza
        const rowContainer = document.querySelector('.content-row');

        response.forEach(item => {
            // Tworzenie elementu HTML (kolumny)
            const contentColumn = document.createElement('div');
            contentColumn.className = 'content-column';

            contentColumn.innerHTML = `
              <div class="content-box" onclick="openPodcast(\'` + item.id + `\')">
                <h5>` + item.title + `</h5>
                <p>` + item.created_at + `</p>
              </div>
            `;

            // Dodanie kolumny do kontenera wiersza
            rowContainer.appendChild(contentColumn);
        })

    }
    xhr.send()

}

function openPodcast(id){
    window.open(`HTML%20files/podcastDetails.html?id=${id}`)
}


readPodcastFromDb()