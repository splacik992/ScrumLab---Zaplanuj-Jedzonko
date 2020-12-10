document.addEventListener("DOMContentLoaded", function() {

    const form = document.querySelector("form");
    const name = document.querySelector("#name");
    const surname = document.querySelector("#surname");
    const email = document.querySelector("#email");
    const pass1 = document.querySelector("#password");
    const pass2 = document.querySelector("#repassword");


    form.addEventListener("submit", evt => {

        const errors = [];
        const regular = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if (!regular.test(email.value)) {
            errors.push("Email jest niepoprawny.");
        }
        if (name.value.length < 2) {
            errors.push("Twoje imie jest za krótkie.");
        }

        if (surname.value.length < 2) {
            errors.push("Twoje nazwisko jest za krótkie.");
        }
        if ((pass1.value !== pass2.value)) {
            errors.push("Hasła są różne.")
        }
        if (pass1.value === "" || pass2.value === "") {
            errors.push("Wypełnij pola z hasłami.")
        }

        if(errors.length) {
            alert(errors.join("\n"));
            evt.preventDefault();
        }
    })

});