##  Steps to run-:
1) install rabbit mq management image from docker hub.
2) docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.13.2-management.
3) Send a post request from PostMan to the url http://localhost:8080/api/v1/publish2 with a json User object or send a get request at http://localhost:8080/api/v1/publish?message=heelo

##  Explaination-:
1) It has 2 queues, 2 binding(one for each queue) and 1 exchange.
2) Producer sends the message toh the Exchange with the routing key, exchange uses the rounting key to the send the message to respective queue.Binding is bind between exchange and the queue through routing.
3) Queue1 is for String messages and Queue2 is for Json message(User class), Queue1 is having routing key "rounting" and Queue is having routing key "routing2".
4) Json message is simply User class.
5) RabbitTemplate object is required to send message to the Exchange with the routing key.
6) @RabbitListner annotation is required by Consumer to receive message form queue.

##  Working of Code-:
1)  When you send a get request to the http://localhost:8080/api/v1/publish?message=heelo, request goest to the MessageController which has a Producer object. MessageController runs the sendMessage function, which calls Producer's sendMessage function.
2)  Producer has a RabbitTemplate object, so Producer's sendMessage calls RabbitTemplate convertAndSend function by giving parmeters like exchage,routing key,message.
3)  Now if the message is of String type then rabbitTemplate will simply route it to the respective queue using routing key.
4)  If the message id of type Json then rabbitTemplate will use converter to convert it into Java object then sends it to the respective queue using routing key.

# Difference between AMQP(Advance Message Queuing Protocol) and WebSocket

1) Function:  
AMQP (Advanced Message Queuing Protocol):  An application-layer protocol for reliable message exchange. It acts as a message broker, allowing applications to send and receive messages asynchronously, ensuring messages are delivered even if the recipient is unavailable temporarily.  
WebSocket: A network protocol that provides bi-directional, full-duplex communication channels between a client and a server. It enables real-time data exchange between applications, ideal for building interactive web applications.
2) Communication Model:  
AMQP: Follows a message-oriented communication model where messages are sent to and received from queues. It supports both one-to-one and one-to-many communication patterns.  
WebSocket: Facilitates full-duplex communication between a client and a server over a single TCP connection. It enables real-time, bidirectional communication between a web client and a server.
3) Use Cases:  
AMQP:  
Microservices communication: Enables communication between different parts of a complex application.  
Scalable messaging: Supports high message volumes and multiple clients.  
Guaranteed delivery: Ensures messages are delivered even if the recipient is temporarily unavailable.  
Offline messaging: Messages can be queued and delivered later when the recipient comes online.    
WebSocket:  
Real-time applications: Provides low-latency, two-way communication for applications like chat, online gaming, and live dashboards.  
Streaming data: Enables continuous data flow between applications, useful for live stock quotes or sensor data.
4) Choosing Between Them:  
AMQP: Choose AMQP for reliable, asynchronous message exchange between applications that don't require real-time communication. It's a good fit for complex architectures with high message volumes.
WebSocket: Choose WebSocket for building real-time web applications that require low-latency, bi-directional data flow. It excels in scenarios where instant updates and interaction are crucial.
5) Uses:  
   AMQP:  
   i) Enterprise messaging systems where reliability, scalability, and asynchronous communication are critical.  
   ii) Microservices architecture where decoupling of services is important.  
   iii) IoT (Internet of Things) applications for handling large volumes of data streams.  
   WebSocket:  
   i) Real-time web applications such as chat applications, stock tickers, and live sports updates.  
   ii) Collaborative editing tools where multiple users need to work on the same document simultaneously.  
   iii) Online gaming platforms for real-time player interactions.  



