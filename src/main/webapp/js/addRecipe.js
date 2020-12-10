document.addEventListener("DOMContentLoaded", function() {

    const form = document.querySelector("form");
    const recipeName = document.querySelector("#recipeName");
    const recipeDes = document.querySelector("#recipeDes");
    const prepTime = document.querySelector("#prepTime");
    const prepDetails = document.querySelector("#prepDetails");
    const ingredients = document.querySelector("#ingredients");


    form.addEventListener("submit", evt => {
        const errors = [];

        if (recipeName.value.length == 0) {
            errors.push("Podaj nazwe przepisu");
        }

        if (recipeDes.value.length == 0) {
            errors.push("Podaj opis przepisu");
        }
        if ( prepTime.value === "") {
            errors.push("Podaj czas przygotowania");
        }
        if (parseInt(prepTime.value)<=0){
            errors.push("Podaj poprawny czas przygotowania");
        }
        if (prepDetails.value.length == 0) {
            errors.push("Podaj sposob przygotowania");
        }
        if (ingredients.value.length == 0) {
            errors.push("Podaj skladniki");
        }

        if(errors.length) {
            alert(errors.join("\n"));
            evt.preventDefault();
        }
    })

});