/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.shopping.Tea;
import sample.shopping.TeaDAO;
import sample.shopping.TeaError;

/**
 *
 * @author DELL
 */
@WebServlet(name = "InsertProductController", urlPatterns = {"/InsertProductController"})
public class InsertProductController extends HttpServlet {

    private static final String ERROR = "insertProduct.jsp";
    private static final String SUCCESS = "insertProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        TeaError teaError = new TeaError();
        try {
            String productID = request.getParameter("productID");
            String pName = request.getParameter("pName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String img = request.getParameter("img");
            boolean checkValidation = true;
            if (pName.length() < 2 || pName.length() > 20) {
                teaError.setNameError("Product name must be in [2-20");
                checkValidation = false;
            }
            if (price < 0) {
                teaError.setPriceError("Price must bigger than 0!!!");
                checkValidation = false;
            }
            if (quantity < 0) {
                teaError.setQuantityError("Quantity must bigger than 0!!!");
                checkValidation = false;
            }
            if (checkValidation) {
                TeaDAO dao = new TeaDAO();
                Tea tea = new Tea(productID, pName, price, quantity, img);
                boolean checkInsert = dao.insertProduct(tea);
                if (checkInsert) {
                    request.setAttribute("MESSAGE", "SUCCESS");
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Unknown Error!!!");
                }
            } else {
                request.setAttribute("ERROR", teaError);
            }
        } catch (Exception e) {
            log("Error at InsertProductController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                teaError.setIdError("Duplicate product(co nghia la product ID da co roi)");
                request.setAttribute("ERROR", teaError);
            }
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
