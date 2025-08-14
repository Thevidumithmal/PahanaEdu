package com.pahanaedu.servlet;

import com.pahanaedu.business.user.model.User;
import com.pahanaedu.business.user.dto.UserDTO;
import com.pahanaedu.business.user.mapper.UserMapper;
import com.pahanaedu.business.user.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/editShopWorker")
public class EditShopWorkerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);
            User worker = userService.getUserById(id);

            UserDTO workerDTO = UserMapper.toDto(worker);

            req.setAttribute("worker", workerDTO);
            req.getRequestDispatcher("jsp/editShopWorker.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error loading worker for edit", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String name = req.getParameter("name");
        String nicNo = req.getParameter("nicNo");

        // Validate NIC and phone
        boolean nicValid = nicNo != null && (nicNo.matches("\\d{9}[vVxX]") || nicNo.matches("\\d{12}"));
        boolean phoneValid = phone != null && phone.matches("\\d{10}");

        boolean hasErrors = false;

        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("usernameError", "Username is required.");
            hasErrors = true;
        }
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("nameError", "Name is required.");
            hasErrors = true;
        }
        if (!nicValid) {
            req.setAttribute("nicError", "NIC must be 10 chars (9 digits + letter) or 12 digits.");
            hasErrors = true;
        }
        if (!phoneValid) {
            req.setAttribute("phoneError", "Phone number must be exactly 10 digits.");
            hasErrors = true;
        }

        if (hasErrors) {
            req.setAttribute("userInput", req.getParameterMap());
            req.getRequestDispatcher("jsp/editShopWorker.jsp").forward(req, resp);
            return;
        }

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);

            // Check for duplicates excluding current user id
            boolean phoneExists = userService.isPhoneExistsForOtherUser(phone, id);
            boolean nicExists = userService.isNicNoExistsForOtherUser(nicNo, id);

            boolean hasDbErrors = false;

            if (phoneExists) {
                req.setAttribute("phoneError", "Phone number already exists.");
                hasDbErrors = true;
            }
            if (nicExists) {
                req.setAttribute("nicError", "NIC number already exists.");
                hasDbErrors = true;
            }

            if (hasDbErrors) {
                req.setAttribute("userInput", req.getParameterMap());
                req.getRequestDispatcher("jsp/editShopWorker.jsp").forward(req, resp);
                return;
            }

            User user = new User.Builder()
                    .setId(id)
                    .setUsername(username)
                    .setName(name)
                    .setNicNo(nicNo)
                    .setPhone(phone)
                    .setAddress(address)
                    .build();

            boolean updated = userService.updateUser(user);
            if (updated) {
                resp.sendRedirect("viewShopWorkers");
            } else {
                req.setAttribute("error", "Failed to update user.");
                req.getRequestDispatcher("jsp/editShopWorker.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException("Error updating worker", e);
        }
    }
}
