const formInit = document.getElementById('form');

const usernameInput = document.getElementById('username');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const password2Input = document.getElementById('password2');

// Show input error message
function showError(input, message) {
    const formControl = input.parentElement;
    formControl.className = 'form-control error';
    const small = formControl.querySelector('small');
    small.innerText = message;
}

// Show success outline
function showSuccess(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}

// Check email is valid
function checkEmail(input) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (re.test(input.value.trim())) {
        showSuccess(input);
    } else {
        showError(input, '邮箱不合法');
    }
}

// Check required fields
function checkRequired(inputArr) {
    let isRequired = false;
    inputArr.forEach(function(input) {
        if (input.value.trim() === '') {
            showError(input, `${getFieldName(input)}必填`);
            isRequired = true;
        } else {
            showSuccess(input);
        }
    });

    return isRequired;
}

// Check input length
function checkLength(input, min, max) {
    if (input.value.length < min) {
        showError(input, `${getFieldName(input)}至少 ${min} 个字符`);
    } else if (input.value.length > max) {
        showError(input, `${getFieldName(input)}至多 ${max} 个字符`);
    } else {
        showSuccess(input);
    }
}

// Check passwords match
function checkPasswordsMatch(input1, input2) {
    if (input1.value !== input2.value) {
        showError(input2, '密码不匹配');
    }
}

// Get fieldname
function getFieldName(input) {
    const formControl = input.parentElement;
    const label = formControl.querySelector('label');
    return label.innerText;
}

// Event listeners
formInit.addEventListener('submit', function(e) {
    e.preventDefault();

    if (!checkRequired([usernameInput, emailInput, passwordInput, password2Input])) {
        checkLength(usernameInput, 3, 15);
        checkLength(passwordInput, 6, 18);
        checkEmail(emailInput);
        checkPasswordsMatch(passwordInput, password2Input);
    }
});


function registerUser() {
    // 获取表单元素
    const usernameInput = document.getElementById('username');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const password2Input = document.getElementById('password2');

    // 获取表单数据
    const email = emailInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value;
    const password2 = password2Input.value;

    // 封装用户对象数据
    const user = {
        email: email,
        username: username,
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