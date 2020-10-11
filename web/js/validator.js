function validateLogin() {
    var s = document.getElementById("surname").value;
    var n = document.getElementById("name").value;
    var p = document.getElementById("password").value;

    if (s.length <= 0) {
        alert("Please enter your surname");
        return false;
    }
    if (n.length <= 0) {
        alert("Please enter your name");
        return false;
    }
    if (p.length <= 0) {
        alert("Please enter you password");
        return false;
    }
    return true;
}

function validateAuthor() {
    alert("wtf")
    return false
    var a1 = document.getElementById("author1").value;
    var a2 = document.getElementById("author2").value;
    alert(a2)
    return false
    if (a1.length > 0 && a1.includes(" ")) {
        alert("Please enter only the athor surname, without any spaces");
        return false;
    }
    if (a2.length > 0 && a2.includes(" ")) {
        alert("Please enter only the athor surname, without any spaces");
        return false;
    }
    alert(a1)
    return false;
}
