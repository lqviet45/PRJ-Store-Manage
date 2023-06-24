<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : shopping
    Created on : Jun 15, 2023, 10:14:57 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Welcome to my Store Đại đi Thầy</h1>
            <select name="cmbTea">
                <option value="T01- Vai Tea-30">Vai Tea- 30k</option>
                <option value="T02- Green ThaiLan Tea-30">Green ThaiLan Tea- 30k</option>
                <option value="T03- Pink Tea-30">Pink Tea- 30k</option>
                <option value="T04- Daisy tea-30">Daisy Tea- 30k</option>
            </select>

            <select name="cmbvQuantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="10">10</option>
            </select>
            <input type="submit" name="action" value="Add" />

            <input type="submit" name="action" value="View">
        </form>
        ${requestScope.MESSAGE}
    </body>
</html>
