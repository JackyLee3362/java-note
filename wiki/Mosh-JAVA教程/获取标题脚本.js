// 加序号
var list = document.getElementById('multi_page').getElementsByTagName('a');
var textList = [];
for (let index = 1; index <= list.length; index++) {
    let xuHao = index.toString();
    if (list.length < 10) {
        xuHao = index.toString();
    } else if (list.length < 100) {
        if (index < 10) {
            xuHao = '0' + index.toString();
        }
    } else if (list.length < 1000) {
        if (index < 10) {
            xuHao = '00' + index.toString();
        } else if (index < 100) {
            xuHao = '0' + index.toString();
        }
    }
    textList.push(xuHao + '-' + list[index - 1].title);
}
console.log('本套视频共有：' + list.length + '集');
console.log(textList.join('\n\t'));