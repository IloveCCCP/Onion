Onion Routing
=================


Onion is an Onion Routing simulation project. Implemented using Netty, AES, RSA and FASTJSON etc.

Usage
----------
Environment: Windows, JDK 19, MAVEN
Go to directory 'Onion/' 
Start up:  
1. Run directory/DirectoryServer.  
   mvn exec:java -D"exec.mainClass"="directory.DirectoryServer"
2. Run as many numbers of node/OnionNode you like.  
   mvn exec:java -D"exec.mainClass"="node.OnionNode"
3. Run client/Client.  
   mvn exec:java -D"exec.mainClass"="client.Client"
4. Enter messages in the client.
5. Check the console of the nodes to see the data flow. 

Structure
----------
### Node Register
![Secure Flow](https://raw.githubusercontent.com/IloveCCCP/Onion/main/img/img.png)
### Node List Retrieving

![Secure Flow](https://raw.githubusercontent.com/IloveCCCP/Onion/main/img/img_1.png)
### Payload (Key Exchange)
![Secure Flow](https://raw.githubusercontent.com/IloveCCCP/Onion/main/img/img_2.png)

### Key Exchange
![Secure Flow](https://raw.githubusercontent.com/IloveCCCP/Onion/main/img/img_3.png)

### Payload (Message)
![Secure Flow](https://raw.githubusercontent.com/IloveCCCP/Onion/main/img/img_4.png)
### Sending Message
![Secure Flow](https://raw.githubusercontent.com/IloveCCCP/Onion/main/img/img_5.png)