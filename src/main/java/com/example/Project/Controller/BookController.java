package com.example.Project.Controller;

import com.example.Project.Entiry.Book;
import com.example.Project.Entiry.Furniture;
import com.example.Project.Repository.FurnitureRepository;
import com.example.Project.Repository.BookRepository;
import com.example.Project.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private BookService orderService;
    @GetMapping("/booking")
    public String showCreatePage(Model model) {
        Book book=new Book();
        model.addAttribute("book", book);
        return "order_f";
    }
//    @PostMapping("/booking")
//    public String createOrder(@RequestParam("id") Long id,@RequestParam int quantity,@ModelAttribute Book book) {
//        Furniture furniture=furnitureRepository.findById(id).get();
//
////            Book book = new Book();
//            book.setCustomerName(book.getCustomerName());
//            book.setDate(book.getDate());
//            book.setTotalPrice(furniture.getPrice() *quantity);
//            book.setQuantity(quantity);
//            bookRepository.save(book);
//
//        return "redirect:u_f_details";
//    }
//@GetMapping("/myPage")
//public String myPage(Model model) {
//    LocalDate today = LocalDate.now();
//    model.addAttribute("minDate", today); // Pass today's date to the view
//    return "order_f"; // Return the name of your Thymeleaf template
//}
    @PostMapping("/booking")
    public String BookingPage(@ModelAttribute Book book,Model model)
    {
        Furniture furniture = furnitureRepository.findAll().get(book.getQuantity());
        LocalDate today = LocalDate.now();
        model.addAttribute("minDate", today);

        book.setFurniture(furniture);
        book.setCustomerName(book.getCustomerName());
        book.setDate(book.getDate());
        int quantity =book.getQuantity();
                book.setQuantity(book.getQuantity());
        System.out.println("quantity");
        book.setTotalPrice(furniture.getPrice() * book.getQuantity());
        model.addAttribute("furniture", furniture);


        bookRepository.save(book);

        return "order_f";
    }
    @GetMapping("/viewOrderList")
    public String userListView(Model model){
        model.addAttribute("book",bookRepository.findAll());
        return "order_list";
    }
    @GetMapping("/viewOrderListVendor")
    public String vendorListView(Model model){
        model.addAttribute("book",bookRepository.findAll());
        return "v_order_list";
    }



}
