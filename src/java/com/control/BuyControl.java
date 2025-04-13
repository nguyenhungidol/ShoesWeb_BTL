package com.control;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAObyKhanh;
import dao.HistoryDAO;
import entity.Account;
import entity.Cart;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BuyControl", urlPatterns = {"/buy"})
public class BuyControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassCastException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");

        if (account == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else if (cart == null) {
            request.getRequestDispatcher("cart").forward(request, response);
        } else {
            // Thêm Order vào DB
            Order order = new Order();
            order.setAccountID(account.getId());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeNow = dtf.format(LocalDateTime.now());
            order.setOrderDate(timeNow);

            OrderDAO orderDAO = new OrderDAO();
            int orderId = orderDAO.addOrderAndReturnID(order);

            if (orderId == -1) {
                throw new Exception("Không thể tạo đơn hàng mới");
            }

            // Thêm chi tiết đơn hàng
            List<Item> items = cart.getItems();
            for (Item item : items) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setPrice(item.getPrice());
                orderDetail.setProductID(item.getProduct().getId());
                orderDetail.setQuantity(item.getQuantity());
                orderDetail.setOrderID(orderId);

                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.addOrderDetail(orderDetail);

                String name = item.getProduct().getName();
                int quantity = item.getQuantity();
                double totalPrice = item.getPrice() * quantity;

                HistoryDAO historyDAO = new HistoryDAO();
                historyDAO.insert(timeNow, account.getUser(), name, quantity, totalPrice);

                ProductDAObyKhanh productDAObyKhanh = new ProductDAObyKhanh();
                productDAObyKhanh.updateQuantity(item.getProduct().getId(), quantity);
            }

            session.removeAttribute("cart");
            request.setAttribute("orderDate", timeNow);
            request.getRequestDispatcher("ConfirmBuy.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuyControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuyControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "BuyControl handles order processing and confirmation.";
    }
}
