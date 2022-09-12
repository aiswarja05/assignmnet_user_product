# Spring Secure Login by jwt authentication with rolewise privilage
<p>
  <h3>Spring Boot Security Login example with JWT and H2 database</h3> 
<p/>

<p>
  <ul>
    <li>Appropriate Flow for User Login and Registration with JWT</li>
    <li>Spring Boot Rest Api Architecture with Spring Security</li>
    <li>How to configure Spring Security to work with JWT</li>
    <li>How to define Data Models and association for Authentication and Authorization</li>
    <li>Way to use Spring Data JPA to interact with H2 Database</li>
  </ul>
</p>

<p>
  <h3>User Registration, Login and Authorization process.</h3>
</p>

<p>
  <a href="https://www.joyee.com/">
    <img alt="Qries" src="https://github.com/bezkoder/spring-boot-security-login/blob/master/spring-boot-security-login-jwt-flow.png" width=700" height="400">
  </a>
</p>

<p>
  <h3>Spring Boot Server Architecture with Spring Security</h3>
</p>

<p>
  You can have an overview of our Spring Boot Server with the diagram below:
</p>

<p>
  <a href="https://www.joyee.com/">
    <img alt="Qries" src="https://github.com/bezkoder/spring-boot-security-login/blob/master/spring-boot-security-login-jwt-architecture.png" width=700" height="400">
  </a>
</p>

<p>
  <h3>Dependency</h3>
  to user h2 database
</p>
<pre>
  <code>
    <span class="pl-ent">dependency</span>
      <span class="pl-ent">groupId</span>&gt;com.h2database&lt;/<span class="pl-ent">groupId</span>
      <span class="pl-ent">artifactId</span>&gt;h2&lt;/<span class="pl-ent">artifactId</span>
      <span class="pl-ent">scope</span>&gt;runtime&lt;/<span class="pl-ent">scope</span>
    <span class="pl-ent">dependency</span>
  </code>
</pre>

<p>
  for jwt
</p>

<pre>
  <code>
    <span class="pl-ent">dependency</span>
      <span class="pl-ent">groupId</span>&gt;io.jsonwebtoken&lt;/<span class="pl-ent">groupId</span>
      <span class="pl-ent">artifactId</span>&gt;jjwt&lt;/<span class="pl-ent">artifactId</span>
      <span class="pl-ent">version</span>&gt;0.9.1&lt;/<span class="pl-ent">version</span>
    <span class="pl-ent">dependency</span>
  </code>
</pre>

<p>
  for spring security
</p>

<pre>
  <code>
    <span class="pl-ent">dependency</span>
      <span class="pl-ent">groupId</span>&gt;org.springframework.security.oauth&lt;/<span class="pl-ent">groupId</span>
      <span class="pl-ent">artifactId</span>&gt;spring-security-oauth2&lt;/<span class="pl-ent">artifactId</span>
      <span class="pl-ent">version</span>&gt;2.0.2.RELEASE&lt;/<span class="pl-ent">version</span>
    <span class="pl-ent">dependency</span>
  </code>
</pre>

<p>
  <h3>Configure Spring Datasource, JPA, App properties</h3>
  Open <code>src/main/resources/application.properties</code>
</p>

<pre>
  <code>
    spring.h2.console.enabled=true
    # default path: h2-console
    spring.h2.console.path=/h2-ui

    spring.datasource.url=jdbc:h2:file:./testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=

    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto= update

    # App Properties
    joyee.app.jwtSecret= no7nd1ixe0
    joyee.app.jwtExpirationMs= 5400000

    server.port=9310
  </code>
</pre>

<p>
  <h3>To run this application execute the maven command</h3>
</p>
<pre>
  <code>
    mvn spring-boot:run
  </code>
</pre>

<pre>
  <b>check the database UI from<b> <a href="http://localhost:9310/h2-ui" target="_blank">here</a>
</pre>

<pre>
  <b>find the collection of this service from <b> <a href="https://www.getpostman.com/collections/5821e7b4020bd6ad3924" target="_blank">here</a>
</pre>




