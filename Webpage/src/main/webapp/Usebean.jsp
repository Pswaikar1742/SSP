<jsp:useBean id="user" class="com.example.User" scope="session" />
<p>User Name: <jsp:getProperty name="user" property="name" /></p>
<p>User Email: <jsp:getProperty name="user" property="email" /></p>