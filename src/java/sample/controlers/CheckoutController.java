/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controlers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.Order;
import sample.shopping.ShoppingDAO;
import sample.shopping.Tea;
import sample.user.UserDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            ShoppingDAO dao = new ShoppingDAO();
            if (user == null) {
                request.setAttribute("MESSAGE", "Please login to checkout!!!!");
                return;
            }
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                request.setAttribute("MESSAGE", "Your cart don't have anything!!!");
                return;
            }
            List<String> error = new ArrayList<>();
            boolean isStocking = true;
            for (Map.Entry<String, Tea> c : cart.getCart().entrySet()) {
                Tea tea = c.getValue();
                int productAllQuantity = dao.checkProductQuantity(tea.getId());
                if (tea.getQuantity() > productAllQuantity) {
                    String errorMess = tea.getName() + " only have " + productAllQuantity + " products left \n";
                    error.add(errorMess);
                    isStocking = false;
                }
            }
            if (!isStocking) {
                request.setAttribute("MESSAGE", error);
                return;
            }
            Order order = new Order(dao.getOrderID(),
                    user.getUserID(),
                    Date.valueOf(LocalDate.now()),
                    cart.getTotal());
            boolean checkSaveOrder = dao.saveOrder(order);
            if (!checkSaveOrder) {
                request.setAttribute("MESSAGE", "Unknow error can not save your order, please try again!!!");
                return;
            }
            int orderDetailID = dao.getOrderDetailID();
            for (Map.Entry<String, Tea> c : cart.getCart().entrySet()) {
                Tea tea = c.getValue();
                boolean saveOrderDetail = dao.saveOrderDetail(orderDetailID, order, tea);
                if (!saveOrderDetail) {
                    request.setAttribute("MESSAGE", "save your order failed (unknown error), please try again for a few minutes");
                    dao.removeOrder(order);
                    return;
                } else {
                    if (!dao.updateProductQuantity(tea.getId(), orderDetailID)) {
                        dao.removeOrderDetail(orderDetailID);
                        request.setAttribute("MESSAGE", "save your order failed (unknown error), please try again for a few minutes");
                        return;
                    }
                }
            }
            request.setAttribute("MESSAGE", "SUCCESS");
            url = SUCCESS;

        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

}
