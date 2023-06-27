<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body style="text-align: center; background-color: #eee;">
        <div class="container">
            <h1>Welcome to my Store Đại đi Thầy</h1>
            <form action="MainController" style="text-align: end; margin-bottom: 6px">
                <input type="submit" name="action" value="View" class="btn btn-info">
            </form>
            <p style="text-align: end" class="text-success">${requestScope.MESSAGE}</p>
            <ul class="list-group">
                <li class="list-group-item mb-4">
                    <div class="row">
                        <div class="col-7">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbRjObUFje0jfhrhd5pKe8btQy-JiXfMKn4A&usqp=CAU" alt="Product 1" class=""
                                 style="width: 70%;">
                        </div>
                        <div class="col-4">
                            <form action="MainController" class="d-flex flex-column float-start" style="width: 300px" >
                                <h2 class="ps-5 pe-5">Vai Tea</h2>
                                <p style="font-size: 20px; align-self: flex-start; margin-bottom: 5px">Price: 30K</p>
                                <h4 style="align-self: flex-start; margin-top: 5px">Quantity</h4>
                                <select name="cmbvQuantity" class="form-select mb-4">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                </select>
                                <input type="hidden" value="T01- Vai Tea-30" name="cmbTea">
                                <input type="submit" value="Add" name="action" class="btn btn-primary" />  
                            </form>
                        </div>
                    </div>
                </li>

                <li class="list-group-item mb-4">
                    <div class="row">
                        <div class="col-7">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHXgog47yzjLXVwLYVOZfIUz_OaRArmlb22w&usqp=CAU" alt="Product 1" class=""
                                 style="width: 70%;">
                        </div>
                        <div class="col-4">
                            <form action="MainController" class="d-flex flex-column float-start" style="width: 300px" >
                                <h2 class="ps-5 pe-5" >Green ThaiLan Tea</h2>
                                <p style="font-size: 20px; align-self: flex-start; margin-bottom: 5px">Price: 30K</p>
                                <h4 style="align-self: flex-start; margin-top: 5px">Quantity</h4>
                                <select name="cmbvQuantity" class="form-select mb-4">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                </select>
                                <input type="hidden" value="T02- Green ThaiLan Tea-30" name="cmbTea">
                                <input type="submit" value="Add" name="action" class="btn btn-primary" />
                            </form>
                        </div>
                    </div>
                </li> 

                <li class="list-group-item mb-4">
                    <div class="row">
                        <div class="col-7">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT017VtqML6pSaQT4zTD4I4r3gRhKD1cuBRZw&usqp=CAU" alt="Product 1" class=""
                                 style="width: 70%;">
                        </div>
                        <div class="col-4">
                            <form action="MainController" class="d-flex flex-column float-start" style="width: 300px">
                                <h2 class="ps-5 pe-5">Pink Tea</h2>
                                <p style="font-size: 20px; align-self: flex-start; margin-bottom: 5px">Price: 30K</p>
                                <h4 style="align-self: flex-start; margin-top: 5px">Quantity</h4>
                                <select name="cmbvQuantity" class="form-select mb-4">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                </select>
                                <input type="hidden" value="T03- Pink Tea-30" name="cmbTea">
                                <input type="submit" value="Add" name="action" class="btn btn-primary" />                                         
                            </form>
                        </div>
                    </div>
                </li> 
            </ul>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
