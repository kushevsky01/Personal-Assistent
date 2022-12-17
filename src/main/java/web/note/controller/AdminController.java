package web.note.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import web.note.model.User;
import web.note.perository.UserRepository;
import web.note.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/admin")
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.getUsers());
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/admin/deleteUser")
    private String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/userRole")
    private String redirectUserRole(@RequestParam Long userId,
                                    @RequestParam String roleName){

        User user = userRepository.findById(userId).get();


        if (roleName.equals("ROLE_ADMIN")){
            user.setRole("ROLE_USER");
        }else if(roleName.equals("ROLE_USER")){
            user.setRole("ROLE_ADMIN");
        }
        userRepository.saveAndFlush(user);
        return "redirect:/admin";
    }


    @PostMapping("/admin/userActivity")
    private String changeActivity(@RequestParam Long userId,
                                  @RequestParam String userActivity){


        User user = userRepository.findById(userId).get();

        if (userActivity.equals("active")){
            user.setActive("banned");
        }else if(userActivity.equals("banned")){
            user.setActive("active");
        }
        userRepository.saveAndFlush(user);
        return "redirect:/admin";
    }
}