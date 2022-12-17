package web.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.note.model.User;
import web.note.perository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"/"})
    public String index(Model model) {
        return "login";
    }

    @PostMapping("/fail")
    public String loginFail(Model model, @RequestParam String username) {
        User user = userRepository.findByUsername(username);

        if(user!=null){

            if (user.getActive().equals("banned")) {
                model.addAttribute("loginError", "Пользователь заблокирован");
            } else {
                model.addAttribute("loginError", "Неверный логин или пароль");
            }
        }else{
            model.addAttribute("loginError", "Неверный логин или пароль");
        }
        return "login";
    }
}