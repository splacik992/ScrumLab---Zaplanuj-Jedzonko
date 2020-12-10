document.addEventListener("DOMContentLoaded", function() {

    const form = document.querySelector("form");
    const newPass = document.querySelector("#newPass");
    const reNewPass = document.querySelector("#reNewPass");

    form.addEventListener("submit", evt => {

        const errors = [];

        if (newPass.value.length === 0) {
            errors.push("Podaj nowe hasło.");
        }

        if (reNewPass.value.length === 0) {
            errors.push("Powtórz nowe hasło.");
        }

        if (newPass.value !== reNewPass.value) {
            errors.push("Wprowadzono różne hasła.");
        }

        if(errors.length) {
            alert(errors.join("\n"));
            evt.preventDefault();
        }
    })

});