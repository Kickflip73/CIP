// 获取导航栏中的链接和主体内容
const resourcesLink = document.querySelector('#resources');
const teamLink = document.querySelector('#team');
const gptLink = document.querySelector('#gpt');
const resourcesContent = document.querySelector('#resources-content');
const teamContent = document.querySelector('#team-content');
const gptContent = document.querySelector('#gpt-content');
const welcomeMessage = document.querySelector('#welcome');

// 点击资源分享链接时显示资源分享主体内容
resourcesLink.addEventListener('click', () => {
    resourcesLink.classList.add('active');
    teamLink.classList.remove('active');
    gptLink.classList.remove('active');
    resourcesContent.classList.remove('hidden');
    teamContent.classList.add('hidden');
    gptContent.classList.add('hidden');
    welcomeMessage.classList.add('hidden');
});

// 点击竞赛组队链接时显示竞赛组队主体内容
teamLink.addEventListener('click', () => {
    resourcesLink.classList.remove('active');
    teamLink.classList.add('active');
    gptLink.classList.remove('active');
    resourcesContent.classList.add('hidden');
    teamContent.classList.remove('hidden');
    gptContent.classList.add('hidden');
    welcomeMessage.classList.add('hidden');
});

// 点击GPT问答链接时显示GPT问答主体内容
gptLink.addEventListener('click', () => {
    resourcesLink.classList.remove('active');
    teamLink.classList.remove('active');
    gptLink.classList.add('active');
    resourcesContent.classList.add('hidden');
    teamContent.classList.add('hidden');
    gptContent.classList.remove('hidden');
    welcomeMessage.classList.add('hidden');
});
