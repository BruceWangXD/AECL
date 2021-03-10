## Overview

Our team implemented an online learning ststem for CS undergraduate students in the University of Sydney. 

The site contains three different servers:
  * a web server
  The web server uses JavaScript to collect information from users. It will check some form validation then valid processed requests will be transferred to API server.
  * an API server
  The API server is the middleware connecting database and the web server. More importantly, it plays an important role in preventing malicious attacks. In the server, there are three different layers: Controller, DAO(Data Access Object) and Service. A monitoring module Alibaba Druid is deployed to help with suspecting in API server. 
  * a MySQL server
  We use MySQL database management system to store the data. API will transfer valid queries to it. MySQL will search in the database and reply to API server.
  
  
## Security Model

1. Full-site HTTPS encryption is implemented to ensure confidentiality and integrity.
When clients request for a connection, the web server will return certificate of the website including public key. Clients generate random symmetric key and use public key of the website to encrypt. The encrypted key will be sent to the web server and will be decrypted using the private key of the website.
 
2. A clean RESTful API model is implemented to ensure the atomicity. 
After receiving requests from the web server, API server will check the authorized token and the correctness of API format. Also, the request will be processed for the second time to prevent SQL injection attack, identity forgery and other malicious attack.
3. A logging system and a detailed document is built to ensure the availability and auditability.
Administrators can log in to check if there exists abnormal data queries.

## API
To check the usage of API, users can visit this [URL](https://10.86.164.216:8080/swagger-ui.html#/front-controller). All controllers are listed by category.
Users can also visit this site from their AECL main page. There are six buttons on the top-right of the page, among which the first one from left is the API introduction. 




#### Alibaba Druid Module 
The API server allows administrators to check the status using a Alibaba Druid Module. The module is deployed locally. Administrators can visit [URL](https://10.86.164.216:8080/druid) to check the running status and data queries of the site.


## GitHub 
[Link to GitHub repository](https://github.sydney.edu.au/weli6728/Aecl)
























