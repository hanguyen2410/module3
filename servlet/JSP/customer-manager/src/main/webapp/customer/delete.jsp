<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/12/2022
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting customer</title>
</head>
<body>
<h1>Delete customer</h1>
<p><a href="/customer">Back to customer list</a></p>
<form method="post">
  <h3>Are you sure?</h3>
  <fieldset>
    <legend>Customer information</legend>
      <table>
          <tr>
              <td>Name: </td>
              <td>${requestScope["customer"].getName()}</td>
          </tr>
          <tr>
              <td>Email: </td>
              <td>${requestScope["customer"].getEmail()}</td>
          </tr>
          <tr>
              <td>Address: </td>
              <td>${requestScope["customer"].getAddress()}</td>
          </tr>
          <tr>
              <td><input type="submit" value="Delete customer"></td>
              <td><a href="/customer">Back to customer list</a></td>
          </tr>
      </table>
  </fieldset>
</form>
</body>
</html>
