# ERP System
A **ERP** System based on **Event Sourcing**. This repository contains the Client (Fontend) and Server (Backend) applications for a ERP System which uses Event Sourcing for storage. The Client is a **command-line interface** used to interact with the Server. Using Event Sourcing has the advantage of **providing a natural audit log, capturing all changes** to the application state. The **events** are persisted in PostgreSQL. 

If you have any questions about the applications or you'd like to know how to run it then feel free to contact me via [mmaresch.com](http://mmaresch.com).

# Dependencies
Thanks to everyone contributing to any of the following projects:
- Any Spring project
- Lombok
- OkHttp
- JSON-Java (org.json)
- PostgreSQL JDBC Driver
- H2
