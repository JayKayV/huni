function parseUser() {
    var formElements = document.querySelect("form");
    var request = new HttpRequest();
    request.open("POST", "/signup")
    request.send(new FormData(formElements))
}