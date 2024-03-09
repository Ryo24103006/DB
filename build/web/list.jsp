<%-- 
    Document   : list
    Created on : Feb 29, 2024, 8:58:03 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="product?action=search" method="POST">
            <input type="text" name="keyword" placeholder="Nhap vao ten cua san pham"/>
            <input type="submit" value="search"/>
        </form>
        <br/>
        <button onclick="addProduct()">Add</button>
        <br/>
        <table border="1">
            <tbody>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Quantity</td>
                    <td>Price</td>
                    <td>Action</td>
                </tr>
                <c:forEach items="${listProduct}" var="p">
                    <tr>
                        <td name="id">${p.id}</td>
                        <td name="name">${p.name}</td>
                        <td name="quantity">${p.quantity}</td>
                        <td name="price">${p.price}</td>
                        <td>
                            <a href="#" onclick="editProduct(this)">Edit</a>
                            <form action="product?action=delete" style="display: inline" method="POST">
                                <input type="hidden" name="id" value="${p.id}"/>
                                <a href="#" onclick="return this.closest('form').submit()">Delete</a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <form action="product?action=insert" id="formAddEdit" style="display: none" method="POST">
            <h2>Add Product</h2>
            <input type="hidden" name="id" value="0"/>
            <br/>
            <table border="1">
                <tbody>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="number" name="quantity"/></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="submit" value="Add"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>

    <script>
        function addProduct() {
            displayForm();
        }

        function editProduct(e) {
            //display form
            displayForm();
            //get data 
            let tr = e.closest('tr');
            let id = tr.querySelector('td[name="id"]').innerHTML;
            let name = tr.querySelector('td[name="name"]').innerHTML;
            let quantity = tr.querySelector('td[name="quantity"]').innerHTML;
            let price = tr.querySelector('td[name="price"]').innerHTML;

            // set data 
            let form = document.querySelector("#formAddEdit");
            form.querySelector('input[name="name"]').value = name;
            form.querySelector('input[name="quantity"]').value = quantity;
            form.querySelector('input[name="price"]').value = price;
            form.querySelector('input[name="id"]').value = id;

            // set lai cai action
            form.action = "product?action=update";
        }
        
        function deleteProduct(e) {
            let form = e.closest('form');
            form.submit();
        }
        
        function displayForm() {
            let form = document.querySelector("#formAddEdit");
            if (form.style.display === 'none') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }
    </script>
</html>
