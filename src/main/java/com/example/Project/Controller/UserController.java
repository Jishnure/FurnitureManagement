package com.example.Project.Controller;

import com.example.Project.Entiry.Furniture;
import com.example.Project.Entiry.FurnitureDto;
import com.example.Project.Entiry.User;
import com.example.Project.Repository.UserRepository;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public  String register(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "Registration";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user){
        System.out.println(user);
        userService.registerUser(user);
        return "Registration";
    }
    @GetMapping("/login")
    public String login(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user){
        String userid=user.getEmail();
        User userdata=userRepository.findByEmail(userid);
        if(user.getPassword().equals(userdata.getPassword())){
            return "u_home";
        }else{
            return "error";
        }
    }
    @GetMapping("/userList")
    public String userList(Model model){
        model.addAttribute("userList",userRepository.findAll());
        return "u_list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/viewUserList")
    public String userListView(Model model){
        model.addAttribute("userList",userRepository.findAll());
        return "a_u_view";
    }
    @GetMapping("/viewUserListV")
    public String userListViewV(Model model){
        model.addAttribute("userList",userRepository.findAll());
        return "v_u_view";
    }
    @GetMapping("/user_home")
    public String home(){
        return "u_home";
    }


}