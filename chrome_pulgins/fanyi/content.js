chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
    if (request.text) {
        translateText(request.text);
    }
});

function translateText(text) {
    // 在此处调用翻译API，例如Google Translate或其他服务
    // 以下代码为示例，您需要替换为实际的API调用和处理响应

    // 示例API调用URL
    let url = "https://api.example.com/translate?text=" + encodeURIComponent(text);

    fetch(url)
        .then(response => response.json())
        .then(data => {
            // 处理翻译结果
            alert(data.translatedText);
        })
        .catch(error => console.error('Error:', error));
}

