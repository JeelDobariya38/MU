package com.example.complaint;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComplaintController {

    private final ComplaintRepository repository;

    public ComplaintController(ComplaintRepository repository) {
        this.repository = repository;
    }

    // First page: complaint submission form.
    @GetMapping("/")
    public String home() {
        return "redirect:/submit";
    }

    @GetMapping("/submit")
    public String showSubmitPage() {
        return "submit";
    }

    // Save complaint using JDBC.
    @PostMapping("/register")
    public String register(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam String priority) {

        if (title == null || title.isBlank()
                || description == null || description.isBlank()
                || !isValidPriority(priority)) {
            return "redirect:/submit";
        }

        Complaint complaint = new Complaint();
        complaint.setTitle(title.trim());
        complaint.setDescription(description.trim());
        complaint.setPriority(priority);

        repository.save(complaint);
        return "redirect:/complaints";
    }

    // Second page: display all complaints.
    @GetMapping("/complaints")
    public String showComplaints(Model model) {
        List<Complaint> complaints = repository.findAll();
        model.addAttribute("complaints", complaints);
        model.addAttribute("pageTitle", "All Complaints");
        return "complaints";
    }

    // Show pending complaints on the same viewing page.
    @GetMapping("/pending")
    public String showPending(Model model) {
        model.addAttribute("complaints", repository.findPending());
        model.addAttribute("pageTitle", "Pending Complaints");
        return "complaints";
    }

    @PostMapping("/update-status/{id}")
    public String updateStatus(@PathVariable int id,
                               @RequestParam String status) {
        if (isValidStatus(status)) {
            repository.updateStatus(id, status);
        }
        return "redirect:/complaints";
    }

    @PostMapping("/update-priority/{id}")
    public String updatePriority(@PathVariable int id,
                                 @RequestParam String priority) {
        if (isValidPriority(priority)) {
            repository.updatePriority(id, priority);
        }
        return "redirect:/complaints";
    }

    private boolean isValidStatus(String status) {
        return "Pending".equals(status)
                || "In Progress".equals(status)
                || "Resolved".equals(status);
    }

    private boolean isValidPriority(String priority) {
        return "Low".equals(priority)
                || "Medium".equals(priority)
                || "High".equals(priority);
    }
}
