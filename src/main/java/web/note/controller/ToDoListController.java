package web.note.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.note.model.Affairs;
import web.note.model.ToDoList;
import web.note.model.User;
import web.note.perository.AffairsRepository;
import web.note.perository.ToDoListRepository;
import web.note.perository.UserRepository;
import web.note.service.UserService;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ToDoListController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private AffairsRepository affairsRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/toDoList/delete")
    @Transactional
    public String deleteToDoList(@RequestParam Long toDoListViewId){
        toDoListRepository.deleteById(toDoListViewId);
        return "redirect:/main";
    }

    @GetMapping("/affairs/delete")
    public String deleteAffair(@RequestParam Long affairId){
        affairsRepository.deleteById(affairId);
        return "redirect:/main";
    }

}
