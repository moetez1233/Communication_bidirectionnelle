# SpringAngularWebSocket
This is a web socket application of Spring boot and Angular 8 with SockJS and Stomp

## What is a WebSocket ?
From the definition of wikipedia WebSocket is a computer communications protocol, providing full-duplex communication channels over a single TCP connection. The WebSocket protocol was standardized by the IETF as RFC 6455 in 2011, and the Web Socket API in Web IDL is being standardized by the W3C.
## Why WebSocket ?
In many web applications, websockets are used to push messages to a client for real-time updates. Usually we recommend using a websocket connection when getting started with Feathers because you get real-time updates for free and it is faster than a traditional HTTP connection.
"# Communication_bidirectionnelle"  
## Angular interface result  
In this application i create 3 different type of comminication and all require  user token to start  
 - send and receive specific message to all users
 - send and receive a message only to the user who send the message
 - send and receive a message to specific user by : send post resquest to **http://localhost:8082/send/specificMessageUser/user1/test2**


![adminApp](https://github.com/moetez1233/Communication_bidirectionnelle/assets/57545701/ff5b4e52-a234-4065-99cb-9b8213568d89)
