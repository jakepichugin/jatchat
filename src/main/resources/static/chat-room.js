//
//  .   ____          _            __ _ _
// /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
// \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//  '  |____| .__|_| |_|_| |_\__, | / / / /
// =========|_|==============|___/=/_/_/_/

function sendMessage() {
	console.log("Sending message to the server");
	
	let message = document.getElementById("message").value;
	let roomId = document.getElementById("roomId").value;
	let name = document.getElementById("name").value;
	
	stompClient.send("/app/rooms/" + roomId + "/messages", {}, JSON.stringify({
		'message' : message,
		"username": name
	}));
}

function scrollMsgToBottom() {
	var objDiv = document.getElementById("msgContainer");
	objDiv.scrollTop = objDiv.scrollHeight;
}

document.getElementById("message").onkeydown = function (e) { 
	if (e.keyCode === 13) {
		sendMessage();
	}
}



var stompClient = null;



function connect() {
	let roomId = document.getElementById("roomId").value;
	
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe("/topic/rooms/" + roomId + "/messages", function(greeting) {
			console.log("RECEIVED: ", greeting);
			showGreeting(JSON.parse(greeting.body));
		});
	});
}

/*function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
}*/


function showGreeting(messageObj) {
	console.log(messageObj);
	$("#msgContainer").append("<p><span style='font-weight:bold; width: 100px'>" + messageObj.username + "</span>: " + messageObj.message + "</p>");
	scrollMsgToBottom();
	document.getElementById("message").value = "";
	//$("#greetings").append("<tr><td>" + message + "</td></tr>");
}


connect();


