package com.example.taskplanner.controller;

import com.example.taskplanner.model.Task;
import com.example.taskplanner.model.User;
import com.example.taskplanner.service.TaskService;
import com.example.taskplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        List<Task> userTasks = taskService.getTasksForUser(currentUser);

        model.addAttribute("user", currentUser);
        model.addAttribute("tasks", userTasks);
        model.addAttribute("pendingTasks", taskService.getTasksByStatus(Status.PENDING));
        model.addAttribute("inProgressTasks", taskService.getTasksByStatus(Status.IN_PROGRESS));
        model.addAttribute("completedTasks", taskService.getTasksByStatus(Status.COMPLETED));
        model.addAttribute("highPriorityTasks", taskService.getTasksByPriority(Priority.HIGH));

        if (currentUser.getRole().equals("ADMIN")) {
            model.addAttribute("urgentTasks", taskService.getTasksByAdminPriority(AdminPriority.URGENT));
            model.addAttribute("importantTasks", taskService.getTasksByAdminPriority(AdminPriority.IMPORTANT));
        }

        return "dashboard";
    }
}

