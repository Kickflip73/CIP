function registerUser() {
    // 获取表单元素
    const emailInput = document.getElementById('email');
    const nicknameInput = document.getElementById('nickName');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm_password');

    // 获取表单数据
    const email = emailInput.value;
    const nickName = nicknameInput.value;
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    // 检查密码和确认密码是否匹配
    if (password !== confirmPassword) {
        alert('密码和确认密码不匹配');
        return;
    }

    // 封装用户对象数据
    const user = {
        email: email,
        nickName: nickName,
        password: password,
    };

    // 将用户对象数据转换为JSON字符串
    const jsonData = JSON.stringify(user);

    // 发送POST请求到服务器
    fetch('http://localhost:8099/cip/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(data => {
            // 处理服务器响应
            console.log(data);
        })
        .catch(error => {
            // 处理错误
            console.error(error);
        });
}
