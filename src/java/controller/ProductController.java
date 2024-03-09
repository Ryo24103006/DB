/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Product;

/**
 *
 * @author admin
 */
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        HttpSession session = request.getSession();
        List<Product> listProduct = (List<Product>) session.getAttribute("listProduct");
        //kiểm tra xem có listproduct ở trong session hay không?
        //TH1: không hề có list product trong session
        if (listProduct == null) {
            // get dữ liệu sản phẩm
            listProduct = dao.findAll();
        }
        // get dữ liệu vào trong request 
        request.setAttribute("listProduct", listProduct);
        //chuyển sang trang list.jsp
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? "" : request.getParameter("action");
        List<Product> listProduct;
        switch (action) {
            case "search":
                listProduct = searchProduct(request, response);
                break;
            case "insert":
                listProduct = insert(request, response);
                break;
            case "update":
                listProduct = update(request, response);
                break;
            case "delete":
                listProduct = delete(request, response);
                break;
            default:
                throw new AssertionError();
        }
        request.getSession().setAttribute("listProduct", listProduct);
        response.sendRedirect("product");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Product> searchProduct(HttpServletRequest request, HttpServletResponse response) {
        //get ve keyword người dùng nhập
        String keyword = request.getParameter("keyword");
        //dua vào keyword do, tìm trong DB có product nào chứa từ khóa mà người dùng nhập không
        ProductDAO dao = new ProductDAO();
        List<Product> listProduct = dao.findByName(keyword);
        //trả về cái list
        return listProduct;
    }

    private List<Product> insert(HttpServletRequest request, HttpServletResponse response) {
        ProductDAO dao = new ProductDAO();

        //get ve data
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        //insert vao trong DB
        dao.insert(product);
        //get ve toan bo data moi
        return dao.findAll();
        //tra ve list 
    }

    private List<Product> update(HttpServletRequest request, HttpServletResponse response) {

        ProductDAO dao = new ProductDAO();

        // get ve dât
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        //update vao DB
        dao.update(product);
        //get ve toan bo data moi
        return dao.findAll();
        //tra ve list
    }

    private List<Product> delete(HttpServletRequest request, HttpServletResponse response) {
        ProductDAO dao = new ProductDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        
        Product product = new Product();
        product.setId(id);
        dao.deleteById(product);
        return dao.findAll();
    }
}
