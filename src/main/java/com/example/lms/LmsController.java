package com.example.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.lms.user.User;
import com.example.lms.user.UserRepository;

@Controller
public class LmsController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  public String root() {
    return "redirect:/home";
  }

  @GetMapping("/home")
  public String getMethodName(Model model) {
    SecurityContext context = SecurityContextHolder.getContext();
    if (context == null) {
      model.addAttribute("permission", "guest");
      return "home";
    }

    Authentication auth = context.getAuthentication();
    if (auth == null) {
      model.addAttribute("permission", "guest");
      return "home";
    }

    Object userDetails = auth.getPrincipal();
    if (userDetails instanceof UserDetails) {
      User user = userRepository.findById(((UserDetails) userDetails).getUsername()).get();
      model.addAttribute("permission", user.getPermission().getName());
    }
    return "home";
  }


}
