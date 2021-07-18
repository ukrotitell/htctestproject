let popup = document.getElementById('mypopup')
let popupToggle = document.getElementById('myBtn')
let popupClose = document.querySelector('.close')

popupToggle.onclick = function () {
    popup.style.display = 'block';

};

popupClose.onclick = function () {
    popup.style.display = 'none';

};

window.onclick = function (e) {
    if (e.target === popup) {
        popup.style.display = 'none'
    }
}
