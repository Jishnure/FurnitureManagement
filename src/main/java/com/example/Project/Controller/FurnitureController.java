package com.example.Project.Controller;

import com.example.Project.Entiry.Book;
import com.example.Project.Entiry.Furniture;
import com.example.Project.Entiry.FurnitureDto;
import com.example.Project.Repository.FurnitureRepository;
import com.example.Project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/furniture")
public class FurnitureController {
    @Autowired
    private FurnitureRepository furnitureRepository;

    @GetMapping("/furniture")
    public String showFurnitureList(Model model) {
        List<Furniture> furniture = furnitureRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("furnitures", furniture);
        return "f_list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        FurnitureDto furnitureDto = new FurnitureDto();
        model.addAttribute("furnitureDto", furnitureDto);
        return "/createFurniture";
    }

    @PostMapping("/create")
    public String createFurniture(@ModelAttribute FurnitureDto furnitureDto, BindingResult result) {
        if (furnitureDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("furnitureDto", "imageFile", "The image file is required"));
        }
        if (result.hasErrors()) {
            return "createFurniture";
        }
        //save image file
        MultipartFile image = furnitureDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "static/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        Furniture furniture = new Furniture();
        furniture.setName(furnitureDto.getName());
        furniture.setCategory(furnitureDto.getCategory());
        furniture.setImageFileName(storageFileName);
        furniture.setDescription(furnitureDto.getDescription());
        furniture.setPrice(furnitureDto.getPrice());
        furniture.setCreatedAt(createdAt);

        furnitureRepository.save(furniture);

        return "redirect:/furniture";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam(name = "id") Long id) {
        try {
            Furniture furniture = furnitureRepository.findById(id).get();
            model.addAttribute("furniture", furniture);

            FurnitureDto furnitureDto = new FurnitureDto();
            furnitureDto.setName(furniture.getName());
            furnitureDto.setCategory(furniture.getCategory());
            furnitureDto.setDescription(furniture.getDescription());
            furnitureDto.setPrice(furniture.getPrice());

            model.addAttribute("furnitureDto", furnitureDto);

        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return "redirect:/furniture";
        }

        return "editFurniture";
    }

    @PostMapping("/edit")
    public String updateFurniture(Model model, @RequestParam(name = "id") Long id, @ModelAttribute FurnitureDto furnitureDto, BindingResult result) {
        try {
            Furniture furniture = furnitureRepository.findById(id).get();
            model.addAttribute("furniture", furniture);

            if (result.hasErrors()) {
                return "editFurniture";
            }
            if (!furnitureDto.getImageFile().isEmpty()) {
                //delete old image
                String uploadDir = "static/images/";
                Path oldImagePath = Paths.get(uploadDir + furniture.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception e) {
                    System.out.println("Exception:" + e.getMessage());
                }
                //save new image file
                MultipartFile image = furnitureDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                furniture.setImageFileName(storageFileName);
            }
            furniture.setName(furnitureDto.getName());
            furniture.setCategory(furnitureDto.getCategory());
            furniture.setDescription(furnitureDto.getDescription());
            furniture.setPrice(furnitureDto.getPrice());

            furnitureRepository.save(furniture);

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return "redirect:/furniture";
    }
    @GetMapping("/delete")
    public  String deleteFurniture(@RequestParam Long id){
        try {
            Furniture furniture=furnitureRepository.findById(id).get();

            //delete furniture image
            Path imagePath=Paths.get("static/images/"+furniture.getImageFileName());

            try {
                Files.delete(imagePath);
            }
            catch (Exception e){
                System.out.println("Exception:"+e.getMessage());
            }

            //delete the Furniture
            furnitureRepository.delete(furniture);

        }catch (Exception e){
            System.out.println("Exception:" +e.getMessage());
        }
        return "redirect:furniture";
    }
    @GetMapping("/viewPage/{id}")
    public String viewFurniture(@PathVariable("id") Long id,Model model)
    {
        Furniture furniture=furnitureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid product id" +id));
        model.addAttribute("furniture",furniture);
        return "u_f_details";

    }
    @GetMapping("/adminViewPage/{id}")
    public String adminViewFurniture(@PathVariable("id") Long id,Model model)
    {
        Furniture furniture=furnitureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid product id" +id));
        model.addAttribute("furniture",furniture);
        return "furniture_details";

    }
    @GetMapping("/viewList")
    public String viewFurnitureList(Model model) {
        List<Furniture> furniture = furnitureRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("furniture", furniture);
        return "u_f_view";
    }
    @GetMapping("/AdminViewList")
    public String viewFurnitureListA(Model model) {
        List<Furniture> furniture = furnitureRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("furniture", furniture);
        return "a_f_view";
    }




}