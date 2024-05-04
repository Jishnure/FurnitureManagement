package com.example.Project.Controller;

import com.example.Project.Entiry.User;
import com.example.Project.Entiry.Vendor;
import com.example.Project.Repository.VendorRepository;
import com.example.Project.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private VendorService vendorService;
    @GetMapping("/v_register")
    public  String register(Model model){
        Vendor vendor=new Vendor();
        model.addAttribute("vendor",vendor);
        return "v_reg";
    }
    @PostMapping("/v_register")
    public String registerVendor(@ModelAttribute("vendor") Vendor vendor){
        System.out.println(vendor);
        vendorService.registerVendor(vendor);
        return "v_reg";
    }
    @GetMapping("/ven_login")
    public String login(Model model){
        Vendor vendor=new Vendor();
        model.addAttribute("vendor",vendor);
        return "v_login";
    }
    @PostMapping("/ven_home")
    public String loginVendor(@ModelAttribute("vendor") Vendor vendor){
        String vendorId=vendor.getEmail();
        Vendor vendorData=vendorRepository.findByEmail(vendorId);
        if(vendor.getPassword().equals(vendorData.getPassword())){
            return "v_home";
        }else{
            return "error";
        }
    }
    @GetMapping("/vendorList")
    public String vendorList(Model model){
        model.addAttribute("vendorList",vendorRepository.findAll());
        return "v_list";
    }
    @GetMapping("/viewVendorList")
    public String vendorListView(Model model){
        model.addAttribute("vendorList",vendorRepository.findAll());
        return "a_v_view";
    }

    @GetMapping("/vendor_home")
    public String home(){
        return "v_home";
    }
}
