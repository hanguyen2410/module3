<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Simple Calculator</h1>
<form method="post" action="/calculate">
<fieldset>
    <legend>Calculator</legend>
    <table>
        <tr>
            <td>First operand: </td>
            <td><input type="text" name="number1"></td>
        </tr>
        <tr>
            <td>Operation: </td>
            <td>
                <select name="operator">
                    <option value="+">Addition</option>
                    <option value="-">Subtraction</option>
                    <option value="*">Multiplication</option>
                    <option value="/">Division</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Second operand: </td>
            <td><input name="number2" type="text"></td>
        </tr>
        <tr>
            <td colspan="2" style="margin-left: 20px"><input type="submit" value="Calculate"></td>
        </tr>
    </table>
</fieldset>
</form>
</body>
</html>