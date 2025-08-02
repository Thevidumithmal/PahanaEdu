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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setRole("shopworker");

        try (Connection connection = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(connection);

            // Convert DTO to Entity
            User user = UserMapper.toEntity(userDTO);
            boolean success = userService.addUser(user);

            if (success) {
                req.setAttribute("success", "Shop worker added successfully.");
            } else {
                req.setAttribute("error", "Failed to add shop worker.");
            }
            req.getRequestDispatcher("jsp/addShopWorker.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error adding shop worker", e);
        }
    }
}
