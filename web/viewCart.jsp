<%-- 
    Document   : viewCart
    Created on : Jun 15, 2023, 10:24:03 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.shopping.Tea"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>It is your order</h1>
        <c:if test="${sessionScope.CART != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Edit</th>
                        <th>Remove</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="${0}"></c:set>
                    <c:forEach var="tea" varStatus="counter" items="${sessionScope.CART.getCart().values()}">
                        <c:set var="total" value="${total + (tea.price * tea.quantity)}"></c:set>
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                            <td>
                                <input type="text" name="id" value="${tea.id}" readonly="" />
                            </td>
                            <td>${tea.name}</td>
                            <td>${tea.price}</td>
                            <td>
                                <input type="number" name="quantity" value="${tea.quantity}" required="" min="1"/>
                            </td>

                            <!--Edit here-->
                            <td>
                                <input type="submit" name="action" value="Edit" />
                            </td>

                            <!--Remove here-->
                            <td>
                                <input type="submit" name="action" value="Remove" />
                            </td>

                            <td>${tea.price * tea.quantity}</td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <h3>Total : ${total}</h3>
    </c:if>
    <a href="shopping.html">Add more</a>
</body>
</html>
