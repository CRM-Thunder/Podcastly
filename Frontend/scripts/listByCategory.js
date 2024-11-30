function listByCategory(category) {

    // Utworzenie zmiennej przechowującej iframe
    const iframe = document.getElementById("podcast-by-category-frame");

    /*
    // Utworzenie nowego obiektu klasy XMLHttpRequest
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/rest/podcasts"

    // Otwarcie połączenia (TU TRZEBA WSPISAĆ FUNKCJĘ, KTÓRA BĘDZIE ZWRACAĆ PODCASTY PO KATEGORII )
    xhr.open('GET', url + '/listall', true)

    xhr.onload = () => {
        var response = JSON.parse(xhr.responseText);


    }
    xhr.send()
    */

    iframe.src = "HTML files/podcastsByCategory.html";

    iframe.onload = () => {
        try {
            // Dostęp do iframe
            const iframeDocument = iframe.contentDocument

            // Edytowanie elementów wewnątrz iframe
            const categoryName = iframeDocument.getElementById("category-name");

            if (categoryName) {
                categoryName.textContent = category;

            } else {
                console.error("Elementy nie zostały znalezione wewnątrz iframe.");
            }
        } catch (error) {
            console.error("Brak dostępu do zawartości iframe.", error);
        }
    }

}