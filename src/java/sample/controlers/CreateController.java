/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controlers;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.service.EmailService;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String SUCCESS = "login.html";
    private static final String ERROR = "create.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            boolean checkValidation = true;
            UserDAO dao = new UserDAO();
            if (userID.length() < 2 || userID.length() > 10) {
                userError.setUserIDError("User ID must be in [2-10]");
                checkValidation = false;
            }
//            boolean checkDuplicate = dao.checkDuplicate(userID);
//            if (checkDuplicate) {
//                userError.setUserIDError("Duplicate userID(co nghia la userID da co roi)");
//                checkValidation = false;
//            }
            if (fullName.length() < 5 || fullName.length() > 20) {
                userError.setFullNameError("Full Name must be in [5,20]");
                checkValidation = false;
            }
            
            if(isValidEmail(email)) {
                userError.setEmailError("The email is not valid!!!");
                checkValidation = false;
            }
            
            if (!password.equals(confirm)) {
                userError.setConfirmError("The confirm password does both match!");
                checkValidation = false;
            }

            if (checkValidation) {
                UserDTO user = new UserDTO(userID, fullName, roleID, email, password);
                boolean checkInsert = dao.insertV2(user);
                if (checkInsert) {
                    EmailService emailService = new EmailService();
                    user.setToken(UUID.randomUUID().toString());
                    dao.saveToken(user);
                    emailService.sendMail(user.getEmail(), user.getToken());
                    url = SUCCESS;               
                } else {
                    request.setAttribute("ERROR", "Unknow error");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            if(e.toString().contains("duplicate")) {
                userError.setUserIDError("Duplicate userID(co nghia la userID da co roi)V2");
                request.setAttribute("USER_ERROR", userError);
            }
            
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    public  boolean isValidEmail(String emailAddress) {
    return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
      .matcher(emailAddress)
      .matches();
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
