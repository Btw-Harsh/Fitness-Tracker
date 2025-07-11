package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.model.Activity;
import com.example.fitnesstracker.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    // List all activities - homepage
    @GetMapping("/")
    public String listActivities(Model model) {
        model.addAttribute("activities", activityRepository.findAll());
        return "index";
    }

    // Show add form
    @GetMapping("/activities/new")
    public String showAddForm(Model model) {
        model.addAttribute("activity", new Activity());
        return "activity-form";
    }

    // Save new or updated activity
    @PostMapping("/activities")
    public String saveActivity(@ModelAttribute Activity activity) {
        activityRepository.save(activity);
        return "redirect:/";
    }

    // Show edit form
    @GetMapping("/activities/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Activity> activityOpt = activityRepository.findById(id);
        if (activityOpt.isPresent()) {
            model.addAttribute("activity", activityOpt.get());
            return "activity-form";
        } else {
            return "redirect:/";
        }
    }

    // Delete activity
    @GetMapping("/activities/delete/{id}")
    public String deleteActivity(@PathVariable Long id) {
        activityRepository.deleteById(id);
        return "redirect:/";
    }


}
