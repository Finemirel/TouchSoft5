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
        let chatUnit = {
            init() {
                this.regbox = document.querySelector(".reg");
                this.chatbox = document.querySelector(".main");

                this.regBtn = this.regbox.querySelector("button");
                this.nameInput = this.regbox.querySelector(".username")
                this.bindEvent();
            },

            bindEvent() {
                this.regBtn.addEventListener("click", e=>this.openSocket())
            },

            openSocket() {
                this.ws = new WebSocket("ws://localhost:8080/webChatApp/chat");
                this.name = this.nameInput.value;
                this.regbox.style.display="none";
                this.chatbox.style.display="block";
            }


        };

        window.addEventListener("load", e=>chatUnit.init());
    </script>
</head>
<body>
<div id="welcome"><h1>Welcome use chat!!)))</h1></div>
<div id="bye" style="display: none"><h1>Good bye!!)))</h1></div>
<div id="reg" class="reg" style="display: block">
    <h3>Choose your role and enter you name:</h3>
    <div id="form" class="helloForm">
        <h4>Enter you name:</h4>
        <input id="username" name="username" type="text" placeholder="enter you name..." maxlength="14" value="guest">
        <h4>You client:</h4>
        <input id="client" type="radio" name="user"><br>
        <h4>You agent:</h4>
        <input id="agent" type="radio" name="user"><br>
        <input id="send" type="button" name="send" value="send">
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
