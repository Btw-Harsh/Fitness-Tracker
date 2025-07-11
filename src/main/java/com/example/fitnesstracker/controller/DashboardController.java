package com.example.fitnesstracker.controller;
import com.example.fitnesstracker.model.User;
import com.example.fitnesstracker.repository.ActivityRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        int totalActivities = activityRepository.findByUser(user).size();
        int totalDuration = activityRepository.findByUser(user)
                .stream().mapToInt(a -> a.getDuration()).sum();

        model.addAttribute("name", user.getName());
        model.addAttribute("totalActivities", totalActivities);
        model.addAttribute("totalDuration", totalDuration);
        return "dashboard";
    }
}
