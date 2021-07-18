const form = document.getElementById('form');
const firstName = document.getElementById('firstName');
const lastName = document.getElementById('lastName');
const patronymic = document.getElementById('patronymic');
const birthdate = document.getElementById('birthdate');


form.addEventListener('submit', e => {
    if (checkInputs() === false) {
        e.preventDefault()
    }
});

function checkInputs() {
    // trim to remove the whitespaces
    const firstNameValue = firstName.value.trim();
    const lastNameValue = lastName.value.trim();
    const patronymicValue = patronymic.value.trim();
    const birthdateValue = birthdate.value.trim();
    let num = 0
    if (firstNameValue === '') {
        setErrorFor(firstName, 'First name cannot be blank');
        num++

    } else if (!isString(firstNameValue)) {
        setErrorFor(firstName, 'Введите буквенные значения');
        num++
    } else {
        setSuccessFor(firstName);

    }


    if (lastNameValue === '') {
        setErrorFor(lastName, 'Last name cannot be blank');
        num++
    } else if (!isString(lastNameValue)) {
        setErrorFor(lastName, 'Введите буквенные значения');
        num++
    } else {
        setSuccessFor(lastName);
    }


    if (patronymicValue === '') {
        setErrorFor(patronymic, 'Patronymic cannot be blank');
        num++
    } else if (!isString(patronymicValue)) {
        setErrorFor(patronymic, 'Введите буквенные значения');
        num++
    } else {
        setSuccessFor(patronymic);
    }

    if (birthdateValue === '') {
        setErrorFor(birthdate, 'Дата рождения не может быть пустой');
        num++
    } else if (!isBirthdate(birthdateValue)) {
        setErrorFor(birthdate, 'Введите дату dd.MM.yyyy');
        num++
    } else {
        setSuccessFor(birthdate);
    }
    return num === 0;
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'form-control error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}

function isBirthdate(birthdate) {
    return /^(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]\d{4}$/.test(birthdate);
}

function isString(name) {
    return /^[a-zA-Z|а-яА-Я]+$/.test(name)
}




