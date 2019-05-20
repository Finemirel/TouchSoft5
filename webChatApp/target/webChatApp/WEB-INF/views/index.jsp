<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Chat</title>
    <style>
        textarea#message {
            width: 485px;
            height: 50px;
            border: 1px solid #cccccc;
            padding: 5px;
            font-family: Tahoma, sans-serif;
            resize: none;
        }

        .scrolling-messages {
            height: 400px;
            width: 100%;
            overflow-y: auto;
            overflow-x: auto;
            border-bottom: 1px solid #cccccc;
        }

        .message-section {
            height: 95px;
            width: 100%;
        }

        .main {
            display: flex;
            flex-direction: column;
            max-width: 500px;
            max-height: 570px;
            border: 3px solid #cccccc;
        }

        .message-label {
            height: 20px;
            width: 100%;
            font-size: 12px;
            font-family: Tahoma, sans-serif;
            padding-bottom: 2px;
            padding-top: 2px;
        }

        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            text-align: center;
            display: inline-block;
            font-size: 12px;
            cursor: pointer;
        }

        .exitButton {
            background-color: #4CAF50;
            border: none;
            color: white;
            text-align: center;
            display: inline-block;
            font-size: 12px;
            cursor: pointer;
        }

        p {
            width: 485px;
            word-wrap: break-word;
        }
    </style>
    <script>
        let userName = null;
        let websocket = null;

        function init() {
            if ("WebSocket" in window) {
                while (userName === null) {
                    userName = prompt("Enter user name");
                    if(userName === "") {
                        userName = null;
                    }
                }
                websocket = new WebSocket('ws://localhost:8080/webChatApp/chat');
                websocket.onopen = function() {
                    document.getElementById("reg").style.display = "block";
                    console.log("open");
                };

                websocket.onmessage = function(data) {
                    setMessage(JSON.parse(data.data));
                };

                websocket.onerror = function(e) {
                    alert('An error occured, closing application');

                    cleanUp();
                };

                websocket.onclose = function(data) {
                    cleanUp();
                };
            } else {
                alert("Websockets not supported");
            }
        }

        function cleanUp() {
            document.getElementById("main").style.display = "none";

            userName = null;
            websocket = null;
        }

        function closeSession() {
            document.getElementById("welcome").style.display = "none";
            document.getElementById("bye").style.display = "block";
            document.getElementById("main").clean;
            console.log("close method");
            let message = "/exit";
            setMessage(message);
            websocket.send(message);
            cleanUp();
        }

        function leave() {
            console.log("leave method");
            let message = "/leave";
            setMessage(message);
            websocket.send(message);
        }

        function sendRegister() {
            console.log("register");
            let messageContent;
            if (document.getElementById("client").checked) {
                messageContent = '/register client ' + userName;
            }
            if (document.getElementById("agent").checked) {
                messageContent = '/register agent ' + userName;
            }
            console.log(messageContent);
            websocket.send(messageContent);
            document.getElementById("reg").style.display = "none";
            document.getElementById("main").style.display = "block";
        }

        function sendMessage() {
            let message = document.getElementById("message").value;
            setMessage(message);
            document.getElementById("message").value = '';
            websocket.send(message);
        }


        function setMessage(msg) {
            let currentHTML = document.getElementById('scrolling-messages').innerHTML;
            let newElem;
            newElem = '<p><span>' + msg + '</span></p>';
            document.getElementById('scrolling-messages').innerHTML = currentHTML
                + newElem;
        }
    </script>
</head>
<body onload="init();">
<div id="welcome"><h1>Welcome use chat!!)))</h1></div>
<div id="bye" style="display: none"><h1>Good bye!!)))</h1></div>
<div id="reg" class="reg" style="display: block">
    <h3>Choose your role and enter you name:</h3>
    <div id="form" class="helloForm">
        <h4>You client:</h4>
        <input id="client" type="radio" name="user"><br>
        <h4>You agent:</h4>
        <input id="agent" type="radio" name="user"><br>
        <input id="send" type="button" name="send" value="send" onclick="sendRegister();">
    </div>
</div>
<div id="main" class="main" style="display: none">
    <div id="scrolling-messages" class="scrolling-messages"></div>
    <div class="message-label">
        <span>Enter message:</span>
    </div>
    <div class="message-section">
        <div>
            <textarea id="message"></textarea>
        </div>
        <div style="float: right">
            <input type="button" value="submit" onclick="sendMessage();"
                   class="button" />
        </div>
    </div>
    <div>
        <input type="button" value="exit" onclick="closeSession();" class="exitButton" />
        <input type="button" value="leave" onclick="leave();" class="button" />
    </div>
</div>
</body>
</html>
