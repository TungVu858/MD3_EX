package controller;

import model.Product;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            action = "";
        }
        switch (action){
            case "search":
                showSearchProduct(request,response);
                break;
            case "searchPrice":
                showSearchPrice(request,response);
                break;
            default :
                showProduct(request,response,session);
                break;
        }

    }

    private void showSearchPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int price1 = Integer.parseInt(request.getParameter("p1"));
        int price2 = Integer.parseInt(request.getParameter("p2"));
        List<Product> products = productService.findByPrice(price1,price2);
        request.setAttribute("product",products);
        request.getRequestDispatcher("product/list.jsp").forward(request,response);
    }

    private void showSearchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Product> products = productService.findByName(name);
        request.setAttribute("product",products);
        request.getRequestDispatcher("product/list.jsp").forward(request,response);
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("product",products);
        request.getRequestDispatcher("product/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
