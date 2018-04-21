var link = document.querySelector('link[rel=import]');
var content = link.import.querySelector('#libraryCard');
var node = content.cloneNode(true);
node.getElementsByTagName('h1').item(0).hidden = true;
document.body.getElementsByTagName('div').item(0).appendChild(node);