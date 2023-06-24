package sample.controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.GoogleUserDAO;
import sample.user.GoogleUserDTO;
import sample.user.UserError;


@WebServlet(name = "CreateGoogleController", urlPatterns = {"/CreateGoogleController"})
public class CreateGoogleController extends HttpServlet {

    private static final String SUCCESS = "login.html";
    private static final String ERROR = "create.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            if(code == null || code.isEmpty()) {
                request.setAttribute("ERROR", "Unknow error");
            } else {
                GoogleUserDAO dao = new GoogleUserDAO();
                String accessToken = dao.getTokenSignUp(code);
                GoogleUserDTO googleUser = dao.getUserInfo(accessToken);
                boolean isUpdate = dao.insertGoogleUser(googleUser);
                if(isUpdate) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Unknow error");
                }               
            }
        } catch (Exception e) {
            log("Error at CreateGoogleController: " + e.toString());
            if(e.toString().contains("duplicate")) {     
                request.setAttribute("GOOGLE_USER_ERROR", "This gmail aready have signup please login!!!");
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
