package com.advertisements.advertisements.service;

import com.advertisements.advertisements.model.Advertisement;
import com.advertisements.advertisements.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService {

    AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository){
        this.advertisementRepository = advertisementRepository;
    }

    /**
     * Return all advertisements in database
     * @return List<Advertisement>
     */
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    /**
     * Saves the parameter advertisement to database
     * @param advertisement - Advertisement
     * @return true if save was successful, otherwise false
     */
    public boolean save(Advertisement advertisement) {

        try{
            advertisementRepository.save(advertisement);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * Removes an advertisement from database
     * @param id - The ID of the advertisement to remove
     */
    public void delete(long id) {

        Advertisement a = advertisementRepository.findById(id).orElse(null);
        if(a == null){
            return;
        }
        advertisementRepository.delete(a);
    }

    /**
     * returns an advertisement from database by ID
     * @param id - Advertisement ID
     * @return Advertisement if find was successful, otherwise null
     */
    public Advertisement findById(long id) {

        return advertisementRepository.findById(id).orElse(null);
    }

    /**
     * Modify an advertisement
     * @param advertisement - Advertisement
     * @return true if find the advertisement, otherwise false
     */
    public boolean edit(Advertisement advertisement) {
        Advertisement a = advertisementRepository.findById(advertisement.getId()).orElse(null);
        if(a == null){
            return false;
        }
        a.setDescription(advertisement.getDescription());
        a.setTitle(advertisement.getTitle());
        advertisementRepository.save(a);

        return true;
    }
}
