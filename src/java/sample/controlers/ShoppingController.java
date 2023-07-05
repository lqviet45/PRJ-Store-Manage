/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controlers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.shopping.Tea;
import sample.shopping.TeaDAO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ShoppingController", urlPatterns = {"/ShoppingController"})
public class ShoppingController extends HttpServlet {

    private static final String ERROR = "login.html";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int page = 1;
            int recordsPerPage = 2;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            TeaDAO dao = new TeaDAO();
            List<Tea> list = dao.getAllProductPaging((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = dao.getNumberOfPage();
            int numberOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            if (list.size() > 0) {
                request.setAttribute("LIST_PRODUCT", list);
                request.setAttribute("CURRENT_PAGE", page);
                request.setAttribute("NUMBER_OF_PAGES", numberOfPages);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ShoppingController: " + e.toString());
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
