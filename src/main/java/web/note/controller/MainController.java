package web.note.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.note.model.Affairs;
import web.note.model.Role;
import web.note.model.ToDoList;
import web.note.model.User;
import web.note.perository.AffairsRepository;
import web.note.perository.ToDoListRepository;
import web.note.perository.UserRepository;
import web.note.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    AffairsRepository affairsRepository;
    private String getList;
    private ToDoList toDoListView = new ToDoList();
    @GetMapping("/main")
    private String main(Model model, Principal principal) {
        User user = userService.getUser(principal.getName());
        ToDoList toDoList = toDoListRepository.findByIdAndNameAndUser(toDoListView.getId(),toDoListView.getName(), user);
        if (toDoList == null){
            toDoListView = new ToDoList();
        }else {
            toDoListView = toDoList;
        }
        model.addAttribute("user", user);
        model.addAttribute("toDoListView", toDoListView);
        return "main";
    }

    @GetMapping("/todolist/affairs")
    public String getAffair(@RequestParam String toDoListName,
                            @RequestParam Long userId,
                            @RequestParam Long toDoListViewId,
                            Model model){
        User user = userRepository.findById(userId).get();
        ToDoList toDoList = toDoListRepository. findByIdAndNameAndUser(toDoListViewId, toDoListName,user);
        toDoListView = toDoList;
        return "redirect:/main";
    }

    @PostMapping("/tolist")
    public String getAcces(@RequestParam String toDoList,
                           @RequestParam Long userId,
                           Model model){
        ToDoList toDoLists = new ToDoList();
        toDoLists.setName(toDoList);
        toDoLists.setCreateTime("Создан: "+ new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        toDoLists.setUser(userRepository.findById(userId).get());
        toDoListRepository.save(toDoLists);
        toDoListView = toDoLists;
        return "redirect:/main";
    }

    @PostMapping("/tolistAffairs")
    public String getAffairs(@RequestParam String affair,
                             @RequestParam Long toDoListId,
                             @RequestParam String toDoListName,
                             @RequestParam Long userId){

        User user = userRepository.findById(userId).get();
        Affairs affairs = new Affairs();
        affairs.setName(affair);
        affairs.setUpdateTime(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        affairs.setDone("false");
        affairs.setToDoList(toDoListRepository.findByIdAndNameAndUser(toDoListId,toDoListName, user));
        affairsRepository.saveAndFlush(affairs);
        ToDoList toDoList = toDoListRepository.findByIdAndNameAndUser(toDoListId,toDoListName, user);
        toDoListView = toDoList;
        return "redirect:/main";
    }


    @PostMapping("/tolistAffairs/redirect")
    public String redirectAffair(@RequestParam Long affairId,
                                 @RequestParam String affairName){
        Affairs affairs = affairsRepository.findById(affairId).get();
        affairs.setName(affairName);
        affairs.setUpdateTime(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        affairsRepository.saveAndFlush(affairs);
        return "redirect:/main";
    }

    @PostMapping("/affair/checkboxFalse")
    public String getCheckBoxFalse(@RequestParam Long affairId){

        Affairs affairs = affairsRepository.findById(affairId).get();
        affairs.setDone("true");
        affairsRepository.saveAndFlush(affairs);
        return "redirect:/main";
    }

    @PostMapping("/affair/checkboxTrue")
    public String getCheckBoxTrue(@RequestParam Long affairId){
        Affairs affairs = affairsRepository.findById(affairId).get();
        affairs.setDone("false");
        affairsRepository.saveAndFlush(affairs);
        return "redirect:/main";
    }

}