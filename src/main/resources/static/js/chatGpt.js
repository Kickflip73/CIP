// Get DOM elements
const chatForm = document.getElementById('chat-form');
const inputField = document.getElementById('user-input');
const chatMessages = document.getElementById('chat-messages');

// Function to display messages in chat window
function displayMessageMe(message, type) {
  const imgSrc = "../image/GPT.jfif";
  // const imgSrc = "../image/背景图片1.jpg"
  const messageDiv = document.createElement('div');
  messageDiv.classList.add('message', type);
  messageDiv.innerHTML = `
  <img src="${imgSrc}" alt="avatar">
  <div class="message-content">
    <div class="message-text">
      <p>${message}</p>
    </div>
    <div class="message-time">
      <p>${new Date().toLocaleTimeString()}</p>
    </div>
  </div>
`;
  chatMessages.appendChild(messageDiv);
  chatMessages.scrollTop = chatMessages.scrollHeight;
}
function displayMessageGPT(message, type) {
    const imgSrc = "../image/chatGpt.png";
    // const imgSrc = "../image/背景图片1.jpg"
    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message', type);
    messageDiv.innerHTML = `
    <img src="${imgSrc}" alt="avatar">
    <div class="message-content">
      <div class="message-text">
        <p>${message}</p>
      </div>
      <div class="message-time">
        <p>${new Date().toLocaleTimeString()}</p>
      </div>
    </div>
  `;
    chatMessages.appendChild(messageDiv);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Function to send user input to backend and display response
async function getResponse(userInput) {
    // Send POST request to backend with user input as data
    const response = await fetch('../GPT', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInput)
    });

    // Parse response as text
    const responseData = await response.text();

    // Display response in chat window
    // cmy 有点问题
    // displayMessageGPT(responseData,'received')
    // displayMessage(responseData, 'received');
}

// Function to handle form submission
function handleSubmit(event) {
    event.preventDefault();

    // Get user input from input field
    const userInput = inputField.value;

    // Clear input field
    inputField.value = '';

    // Display user input in chat window
    displayMessageMe(userInput, '发送');

    // Display "Waiting for response" message in chat window
    displayMessageGPT('正在处理中...', 'received');

    // Get response from backend
    getResponse(userInput);
}

// Event listener for form submission
chatForm.addEventListener('submit', handleSubmit);
