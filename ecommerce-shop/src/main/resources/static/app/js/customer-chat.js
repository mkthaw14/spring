var chatId = "";
var userName = "";
var stompClient = "";

$(document).ready(function() {	
	$("#btn-send-msg").click(function() { 
		//alert("Click");
		sendMessage();	
	});
		
	$("#btn-open-messages").click(function() {
		connect();
	});
	
	$("#btn-close-chat-message-area").click(function() {
		disconnect();
	});
	ajaxBeforeSendEventListener();
});

function showMessage(message)
{
	console.log(JSON.parse(message));
	message = JSON.parse(message);
	
	//alert("userName : " + userName);
	//alert("message.senderName : " + message.senderName);
	let msgDirection = userName == message.senderName ? "right" : "left";
	let bgColor = userName == message.senderName ? "bg-dark" : "bg-primary";
	let msgSenderName = userName == message.senderName ? "You" : message.senderName;
	let name = message.senderName[0];
	
	let newMsg = `
		<div class="chat-message-${msgDirection} pb-4">
			<div>
				<div class="rounded-circle mr-1 ${bgColor}
				d-flex justify-content-center align-items-center text-white" 
				style="width: 40px; height: 40px;">${name.toUpperCase()}</div>
				<div class="text-muted small text-nowrap mt-2">2:33 am</div>
			</div>
			<div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
				<div class="font-weight-bold mb-1">${msgSenderName}</div>
				${message.content}
			</div>
		</div>
	`; 
	
	$(".chat-messages").append(newMsg);
	$("#msg-input").val('');
}

function connect()
{
	getChatID();
	loadOfflineMessage();	
}


function getChatID()
{
		jQuery.ajax({
		type : "POST",
		async : false,
		url : "/chat/get-chatId",
		success : function(data) {
			console.log(JSON.stringify(data));
		    chatId = data.chatId;
		    userName = data.userName;
		},
		error : function(err) {
			console.log(JSON.stringify(err));
		}
	});
}

function loadOfflineMessage()
{
	let post_obj = {
		"chatId" : chatId
	};
	
	jQuery.ajax({
		type : "POST",
		url : "/chat/load-offline-msg",
		data : post_obj,
		async : false,
		success : function(messages) {
			if(messages.length > 0)
			{
				messages.forEach(function(msg) {
					
					showMessage(JSON.stringify(msg));
				})
			}
				
			console.log("offine-msg : " + JSON.stringify(messages));
			connectToChatRoom();
		},
		error : function(err) {
			console.log(JSON.stringify(err));
		}
	});
}


function connectToChatRoom()
{
	var socket = new SockJS("/customer-support-websocket");
	stompClient = Stomp.over(socket);
	
	stompClient.connect({} , function(frame) {
		console.log(frame);
		let url = "/user/" + chatId + "/customer-private-chat-room";
		stompClient.subscribe(url, function(message) {
			console.log("msg : " + message);
			showMessage(message.body);
		})
	});
}

function disconnect()
{
	if(stompClient != null)
	{
		stompClient.disconnect();
	}
}

function sendMessage()
{
	if($("#msg-input").val() == "")
		return;
		
	let chatRoom = {
		"id" : chatId
	}
	
	let post_obj = {
		"senderName" : null,
		"content" :  $("#msg-input").val(),
		"chatRoom" : chatRoom
	}
	// chatRoomUrl "/app/private-msg-to-site-admin"
	stompClient.send("/app/private-msg", {}, JSON.stringify(post_obj));
	console.log(JSON.stringify(post_obj));
}

function ajaxBeforeSendEventListener() {
	//alert('Befor send');
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
}