<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/12/2022
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit customer</title>
</head>
<body>
<h1>Edit customer</h1>
<p>
  <c:if test='${requestScope["message"] != null}'>
    <span class="message">${requestScope["message"]}</span>
  </c:if>
</p>
<p>
  <a href="/customer">Back to customer list</a>
</p>
<form method="post">
  <fieldset>
    <legend>Customre information</legend>
    <table>
      <tr>
        <td>Name:</td>
        <td><input type="text" name="name" id="name" value="${requestScope["customer"].getName()}"></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" id="email" value="${requestScope["customer"].getName()}"></td>
      </tr>
      <tr>
        <td>Address:</td>
        <td><input type="text" name="address" id="address" value="${requestScope["customer"].getName()}"></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Update customer"></td>
      </tr>
    </table>
  </fieldset>
</form>
</body>
</html>
