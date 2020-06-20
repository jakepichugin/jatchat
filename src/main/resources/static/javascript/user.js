


//
//$(function() {
//	// Use if needed
//});

function checkForUser() {
	const user = jcGetUserFromLocalStorage();
	console.log("LOCATION: ", window.location);
	if (!user) {
		if (window.location.pathname !== "/login.html" && window.location.pathname !== "/signup.html") {
			window.location.href = "/login.html";
		}
	} else if (window.location.pathname === "/" || window.location.pathname === "/index.html") {
		window.location.href = "/home.html";
	}
}


function jcSaveUserInLocalStorage(user) {
	//const user = { username: "Vasya", key: "1234lkj1234", loginTime: 12341234 };
	localStorage.setItem("jcsession", JSON.stringify(user));
}

function jcGetUserFromLocalStorage() {
	return JSON.parse(localStorage.getItem("jcsession"));
}

checkForUser();

function login(event) {
	event.preventDefault();
	
	const username = $("#username").val();
	const password = $("#password").val();
	
	jQuery.post("/login", { username: username, password: password } ).done(function( user ) {
		if (user && user.id && user.key) {
			jcSaveUserInLocalStorage(user);
			window.location.href = "/home.html";
		} else {
			alert("Login failed!");
			console.log("Login failed: ", data);
		}
	});
	return false;
}

function signup(event) {
	event.preventDefault();
	
	const username = $("#username").val();
	const password = $("#password").val();
	
	jQuery.post("/signup", { username: username, password: password } ).done(function( user ) {
		if (user && user.id && user.key) {
			jcSaveUserInLocalStorage(user);
			window.location.href = "/home.html";
		} else {
			alert("Signup failed!" + user);
			console.log("signup failed: ", user);
		}
	});
	return false;
}