package com.example.Project.Controller;

import com.example.Project.Entiry.Login;
import com.example.Project.Entiry.User;
import com.example.Project.Repository.AdminRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @GetMapping("/admin_login")
    public String login(Model model)
    {
        Login login=new Login();
        model.addAttribute("admin",login);
        return "a_login";
    }
    @PostMapping("/admin_home")
    public String loginAdmin(@ModelAttribute("admin") Login login){
        String admin=login.getUsername();
        Login admindata=adminRepository.findByUsername(admin);
        if(login.getPassword().equals(admindata.getPassword()))
        {
            return "home";
        }
        else {
            return "error";
        }

    }
    @GetMapping("/a_home")
    public String home(){
        return "home";
    }
//    @RequestMapping(value = {"/logout"},method = RequestMethod.POST)
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        return "index";
//    }
//    @GetMapping("/logout")
//    public String logoutA(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate(); // Invalidate the session
//        }
//        // Redirect to the login page
//        return "redirect:/a_login";
//    }

}
