Onion Routing
=================


Onion is an Onion Routing simulation project. Implemented using Netty, AES, RSA and FASTJSON etc.

Usage
----------
Start up:  
1. Run directory/DirectoryServer.
2. Run as many numbers of node/OnionNode you like.
3. Run client/Client.
4. Enter messages in the client.
5. Check the console in the nodes to see the data flow. 

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