package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.model.User;
import com.example.fitnesstracker.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

//    @GetMapping("/dashboard")
//    public String showDashboard(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("loggedInUser");
//
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        model.addAttribute("user", user);
//        return "dashboard";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
