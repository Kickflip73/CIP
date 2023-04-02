
const form = document.getElementById('login-form');
const message = document.querySelector('.message');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const response = await fetch(event.target.action, {
        method: event.target.method,
        body: formData,
    });

    const result = await response.json();
    var id = 0;
//    记录登录状态
var loginStatus = false;
window.localStorage.setItem("loginStatus", loginStatus);

    window.localStorage.setItem("loginStatus",false)
    if (result.code === 1011) {
        const data = result.data;
        sessionStorage.setItem('user', JSON.stringify(data));
        window.localStorage.setItem("loginStatus",true);
        window.localStorage.setItem("id",result.data.id);
        window.location.href = '../index.html';
    } else {
        message.textContent = result.message;
    }
});

