# Technical Test backend

Technical test


## Docker

In the project's root:

Create file target/technical-test.jar

``mvn install
``

Build image: 
``docker build -f Dockerfile -t technical-test .
``

Run image: 
``docker run -p 8080:8080 technical-test``

``http://localhost:8080/clients/``

##Swagger

``http://localhost:8080/swagger-ui.html``

## Testing
