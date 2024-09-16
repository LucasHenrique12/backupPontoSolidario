package com.example.login_auth_api.controllers;


import com.example.login_auth_api.domain.DonationPlace;
import com.example.login_auth_api.dto.DonationPlaceDTO;
import com.example.login_auth_api.dto.ResponseDTO;
import com.example.login_auth_api.repositories.DonationPlaceRepository;
import com.example.login_auth_api.service.DonationPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/DonationPlace")
@RequiredArgsConstructor
public class DonationPlaceController {

@Autowired
private DonationPlaceRepository donationPlacerepository;
@Autowired
private DonationPlaceService donationPlaceService;

    @GetMapping
    public List<DonationPlace> findAll(){

        return donationPlacerepository.findAll();
    }

    @PostMapping("/registerDonationPlace")
    public ResponseEntity<DonationPlaceDTO> registerDonationPlace(@RequestBody DonationPlaceDTO body){
            try {
                DonationPlaceDTO response = donationPlaceService.donationPlaceRegister(body);
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(new DonationPlaceDTO("não foi", "Error","Error","Error"));
            }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationPlace> findById(@PathVariable String id) {
        return donationPlacerepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationPlace(@PathVariable String id) {
        if (donationPlacerepository.existsById(id)) {
            donationPlacerepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
