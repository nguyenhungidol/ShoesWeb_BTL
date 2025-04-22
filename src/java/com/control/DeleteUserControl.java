package com.control;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteUserControl", urlPatterns = {"/DeleteAccount"})
public class DeleteUserControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Lấy tham số id từ request
            String id = request.getParameter("id");
            int accountId = 0;
            
            // Kiểm tra và chuyển id sang số nguyên
            try {
                accountId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid account ID");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            
            AccountDAO accountDAO = new AccountDAO();
            
            // Xóa tài khoản
            boolean isDeleted = accountDAO.delete(accountId);
            
            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về trang danh sách tài khoản
                request.setAttribute("success", "Account deleted successfully");
                request.getRequestDispatcher("ManagerAccount").forward(request, response);
            } else {
                // Nếu xóa không thành công, hiển thị thông báo lỗi
                request.setAttribute("error", "Failed to delete account. Please try again.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting user account";
    }
}
