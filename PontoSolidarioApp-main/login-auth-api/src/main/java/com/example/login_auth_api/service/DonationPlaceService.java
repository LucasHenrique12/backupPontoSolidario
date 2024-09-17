package com.example.login_auth_api.service;

import com.example.login_auth_api.domain.DonationPlace;
import com.example.login_auth_api.dto.DonationPlaceDTO;
import com.example.login_auth_api.repositories.DonationPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationPlaceService {
    @Autowired
    private DonationPlaceRepository donationPlaceRepository;

        public void donationPlaceRegister(DonationPlaceDTO body) {

            DonationPlace donationPlace = new DonationPlace();

            donationPlace.setName(body.name());
            donationPlace.setLatitude(body.latitude());
            donationPlace.setLongitude(body.longitude());
            donationPlace.setAddress(body.address());

            if (donationPlaceRepository.existsByNameAndLatitudeAndLongitude(
                    donationPlace.getName(),
                    donationPlace.getLatitude(),
                    donationPlace.getLongitude())) {
                throw new RuntimeException("Donation Place already exists");
            }
            else {
                donationPlaceRepository.save(donationPlace);
            }

        }
}




