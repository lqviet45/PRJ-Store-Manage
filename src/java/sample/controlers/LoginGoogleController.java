package sample.controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.GoogleUserDAO;
import sample.user.GoogleUserDTO;
import sample.user.UserDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String AD = "AD";
    private static final String US = "US";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String US_PAGE = "usPage.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;

        try {
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                request.setAttribute("ERROR", "Can not sign in!!!(Email is invalid)");
            } else {
                GoogleUserDAO dao = new GoogleUserDAO();
                String accessToken = dao.getToken(code);
                GoogleUserDTO googleUser = dao.getUserInfo(accessToken);
                UserDTO user = dao.checkLogin(googleUser.getId(), googleUser.getEmail());
                if (user == null) {
                    request.setAttribute("ERROR", "Your email is not signup yet!!!");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", user);
                    String userRole = user.getRoleID();
                    if (AD.equals(userRole)) {
                        url = ADMIN_PAGE;
                    } else if (US.equals(userRole)) {
                        url = US_PAGE;
                    } else {
                        request.setAttribute("ERROR", "Your role is not supported yet!");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at LoginGoogleController: " + e.toString());
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