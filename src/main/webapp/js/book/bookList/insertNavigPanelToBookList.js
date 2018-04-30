var link = document.querySelector('link[rel=import]');
var content = link.import.querySelector('#navigation');
var node = content.cloneNode(true);
document.body.getElementsByTagName('div').item(0).after(node);