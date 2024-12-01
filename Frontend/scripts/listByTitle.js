function listByTitle() {
    const search_bar_text = document.getElementById("podcast-search-bar").value;

    // Ukrycie sekcji odpowiedzialnej za ostatnio opublikowane podcasty
    const content_section = document.getElementById("content-section");
    content_section.style.display = "none";

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Otwarcie połączenia (url musi być zmieniony na taki, który wyświetla ostatnio opublikowane podcasty)
    xhr.open('GET', url + '/find/title/' + search_bar_text, true)

    xhr.onload = () => {
        var response = JSON.parse(xhr.responseText);

        console.log(response);

    }

    xhr.send()
}
