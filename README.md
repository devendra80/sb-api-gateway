# sb-api-gateway
This is my learning page. In this series, I am trying to publish multiple apis using spring boot api gateway service.

1. There are 2 services sb-emp-services-h2 and sb-cust-services-h2.
2. Above services are using h2 database
3. There is no authentication is configured.
4. These services can be accessed as follows.
    a.  sb-emp-services-h2 will run on http://localhost:8083/employees
    b.  sb-cust-services-h2 will run on http://localhost:8083/customers
5. sb-api-gateway service is created for api gateway. this way, you can access above services using api gateway url http://localhost:8085 internally without telling client which url is serving the request. It routes to respective url based on path (e.g. employees or customers)
6. sb-api-gateway-basic-auth service is doing same except basic auth is implemented here. here are details about security.
    a.  add spring-boot-starter-security in pom.xml
    b.  implement SecurityConfig.java to apply basic auth 
    c.  add authenticationEntryPoint to add custom logic 
    d.  here are flows for any request.
        i. The request curl -v -u user:password http://localhost:8085/employees is sent to the server.
        ii. The request is intercepted by the Spring Security filter chain configured in SecurityConfig
        iii.    The request is checked against the authorization rules. all paths except login, register are configured to require authentication.
        iv. Basic Authentication is enabled, so the request must include valid credentials.
        v.  The Basic Authentication filter extracts the credentials (user:password) from the request.
        vi. The credentials are validated against the security.user configured in application.yaml.
        vii.    If the credentials are valid, the request is authenticated, and access is granted to the /employees endpoint.
        viii.   If the credentials are invalid, an authentication failure response is returned.
7.  Here are curl commands.
    curl -u user:password http://localhost:8085/employees
    curl -u user:password http://localhost:8085/customers
