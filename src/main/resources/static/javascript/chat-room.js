//
//  .   ____          _            __ _ _
// /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
// \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//  '  |____| .__|_| |_|_| |_\__, | / / / /
// =========|_|==============|___/=/_/_/_/

function sendMessage( message, roomId, name) {
	console.log("Sending message to the server");
	
	
	stompClient.send("/app/rooms/" + roomId + "/messages", {}, JSON.stringify({
		'message' : message,
		"username": name
		
	}));
	//document.getElementById("message").value = "";  //cleaning up the input field
}

function scrollMsgToBottom() {
	var objDiv = document.getElementById("msgContainer");
	objDiv.scrollTop = objDiv.scrollHeight;
}

//document.getElementById("message").onkeydown = function (e) { 
//	if (e.keyCode === 13) {
//		sendMessage();
//	}
//}



var stompClient = null;


function disconnect() {
	if (stompClient) {
		stompClient.disconnect();
	}
}
function connect(roomId, callback) {
//	let roomId = document.getElementById("roomId").value;
	if (!roomId) {
		console.log("ERROR: cannot connect to a room. ID is required!");
		return;
	}
	disconnect();
	
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe("/topic/rooms/" + roomId + "/messages", function(greeting) {
			console.log("RECEIVED: ", greeting);
			callback(JSON.parse(greeting.body));
			//showGreeting(JSON.parse(greeting.body));
		});
	});
}




//function showGreeting(messageObj) {
//	console.log(messageObj);
//	$("#msgContainer").append("<p><span style='font-weight:bold; width: 100px'>" + messageObj.username + "</span>: " + messageObj.message + "</p>");
//	scrollMsgToBottom();
//}

//
//connect();


