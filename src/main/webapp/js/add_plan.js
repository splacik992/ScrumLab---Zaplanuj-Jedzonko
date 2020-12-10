document.addEventListener("DOMContentLoaded", function() {

    const form = document.querySelector("form");
    const planName = document.querySelector("#planName");
    const planDescription = document.querySelector("#planDescription");

    form.addEventListener("submit", evt => {

        const errors = [];

        if (planName.value.length === 0) {
            errors.push("Podaj nazwę planu.");
        }

        if (planDescription.value.length === 0) {
            errors.push("Dodaj opis planu.");
        }

        if(errors.length) {
            alert(errors.join("\n"));
            evt.preventDefault();
        }
    })

});