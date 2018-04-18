var link = document.querySelector('link[rel=import]');
                        var content = link.import.querySelector('#book');
                        var node = content.cloneNode(true);
                        <!--node.getElementsByTagName('h1').item(0).hidden;-->
                        node.getElementsByClassName('form-group').item(2).hidden = true;
                        node.getElementsByClassName('form-group').item(3).hidden = true;
                        document.body.getElementsByTagName('form').item(0)
                            .appendChild(node);