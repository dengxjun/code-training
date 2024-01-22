chrome.contextMenus.create({
    id: "translate",
    title: "翻译选中的文本",
    contexts: ["selection"]
});

chrome.contextMenus.onClicked.addListener(function(info, tab) {
    if (info.menuItemId === "translate") {
        chrome.tabs.sendMessage(tab.id, { text: info.selectionText });
    }
});

