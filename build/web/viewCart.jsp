<%-- 
    Document   : viewCart
    Created on : Jun 15, 2023, 10:24:03 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    </head>
    <body style="background: #eee;">
        <section class="h-100 gradient-custom">
            <div class="container py-5">
                <div class="row d-flex justify-content-center my-4">
                    <div class="col-md-8">
                        <a href="shopping.html" class="btn btn-primary ms-3 mb-3">Add More</a>
                        <c:if test="${sessionScope.LOGIN_USER == null}">
                            <a href="login.html" class="btn btn-primary ms-3 mb-3">Login</a>
                        </c:if>
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Cart</h5>
                            </div>
                            <div class="card-body">
                                <!-- Single item -->
                                <c:set var="total" value="${0}"></c:set>
                                <c:forEach var="tea" varStatus="counter" items="${sessionScope.CART.getCart().values()}">
                                    <c:set var="total" value="${total + (tea.price * tea.quantity)}"></c:set>
                                        <form action="MainController">
                                            <div class="row">

                                                <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                                                    <!-- Data -->
                                                    <p><strong>${tea.name}</strong></p>
                                                <p>ID: <input type="text" name="id" value="${tea.id}" readonly="" /></p>
                                                <p>Price: ${tea.price}</p>
                                                <button type="submit" value="Remove" name="action" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip"
                                                        title="Remove item">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                                <button type="submit" value="Edit" name="action" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip"
                                                        title="Remove item">
                                                    <i class="fas fa-pen"></i>
                                                </button>
                                                <!-- Data -->
                                            </div>

                                            <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                                <!-- Quantity -->
                                                <div class="d-flex mb-4" style="max-width: 300px">
                                                    <div class="form-outline">
                                                        <input id="form1" min="0" name="quantity" value="${tea.quantity}" type="number" class="form-control" />
                                                        <label class="form-label" for="form1">Quantity</label>
                                                    </div>
                                                </div>
                                                <!-- Quantity -->

                                                <!-- Price -->
                                                <p class="text-start text-md-center">
                                                    <strong>${tea.price * tea.quantity}</strong>
                                                </p>
                                                <!-- Price -->
                                            </div>
                                        </div>
                                    </form>
                                    <hr class="my-4" />
                                </c:forEach>
                                <!-- Single item -->

                            </div>
                        </div>

                    </div>
                    <div class="col-md-4 my-5 py-1">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Summary</h5>
                            </div>
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>Total amount</strong>
                                        </div>
                                        <span><strong>${total}</strong></span>
                                    </li>
                                </ul>
                            <p class="text-danger">${requestScope.ERROR}</p>
                                <form action="MainController">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="Checkout">
                                        Go to checkout
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
