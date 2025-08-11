package com.pahanaedu.servlet;

import com.pahanaedu.business.user.dto.UserDTO;
import com.pahanaedu.business.user.mapper.UserMapper;
import com.pahanaedu.business.user.model.User;
import com.pahanaedu.business.user.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/addShopWorker")
public class AddShopWorkerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String successParam = req.getParameter("success");
        if ("true".equals(successParam)) {
            req.setAttribute("success", "Shop worker added successfully.");
        }
        req.getRequestDispatcher("jsp/addShopWorker.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String name = req.getParameter("name");
        String nicNo = req.getParameter("nicNo");

        // Validate NIC: 10 chars (9 digits + letter) or 12 digits
        boolean nicValid = nicNo != null && (nicNo.matches("\\d{9}[vVxX]") || nicNo.matches("\\d{12}"));
        // Validate phone: exactly 10 digits
        boolean phoneValid = phone != null && phone.matches("\\d{10}");

        boolean hasErrors = false;

        if (!nicValid) {
            req.setAttribute("nicError", "NIC must be 10 chars (9 digits + letter) or 12 digits.");
            hasErrors = true;
        }

        if (!phoneValid) {
            req.setAttribute("phoneError", "Phone number must be exactly 10 digits.");
            hasErrors = true;
        }

        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("usernameError", "Username is required.");
            hasErrors = true;
        }

        if (password == null || password.trim().isEmpty()) {
            req.setAttribute("passwordError", "Password is required.");
            hasErrors = true;
        }

        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("nameError", "Name is required.");
            hasErrors = true;
        }

        // If any error, forward back to form with messages and pre-fill values
        if (hasErrors) {
            req.setAttribute("userInput", req.getParameterMap());
            req.getRequestDispatcher("jsp/addShopWorker.jsp").forward(req, resp);
            return;
        }

        // No errors, proceed as before
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setName(name);
        userDTO.setNicNo(nicNo);
        userDTO.setRole("shopworker");

        try (Connection connection = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(connection);
            User user = UserMapper.toEntity(userDTO);
            boolean success = userService.addUser(user);

            if (success) {
                resp.sendRedirect("addShopWorker?success=true");
                return;
            } else {
                req.setAttribute("error", "Failed to add shop worker.");
            }
            req.getRequestDispatcher("jsp/addShopWorker.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error adding shop worker", e);
        }
    }

}
