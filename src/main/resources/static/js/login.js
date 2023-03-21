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

    if (result.code === 1011) {
        const data = result.data;
        sessionStorage.setItem('user', JSON.stringify(data));
        window.location.href = 'http://localhost:8099/cip/index.html';
    } else {
        message.textContent = result.message;
    }
});
