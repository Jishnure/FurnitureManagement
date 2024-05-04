package com.example.Project.Service;

import com.example.Project.Entiry.User;
import com.example.Project.Entiry.Vendor;
import com.example.Project.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    public void registerVendor(Vendor vendor){
        vendorRepository.save(vendor);
    }
}
