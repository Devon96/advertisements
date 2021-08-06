package com.advertisements.advertisements.controller;

import com.advertisements.advertisements.model.Advertisement;
import com.advertisements.advertisements.model.AuthRequest;
import com.advertisements.advertisements.service.AdvertisementService;
import com.advertisements.advertisements.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * These functions serve http requests
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ApiController {

    AdvertisementService advertisementService;

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    @Autowired
    public ApiController(AdvertisementService advertisementService, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.advertisementService = advertisementService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Return all advertisements to the database
     * @return List<Advertisement> in a HttpEntity
     */
    @PostMapping(value = "/advertisement/list")
    public HttpEntity<List<Advertisement>> listAdvertisements(){

        List<Advertisement> advertisements = advertisementService.findAll();
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    /**
     * Returns the advertisement with the parameter ID
     * @return Advertisement entity in a HttpEntity
     */
    @GetMapping(value = "/advertisement/get/{id}")
    public HttpEntity<Advertisement> getAdvertisement(@PathVariable long id, HttpServletRequest request){
        Advertisement advertisement = advertisementService.findById(id);


        if (advertisement == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(advertisement, HttpStatus.OK);
    }


    /**
     * This server endpoint saves a new advertisement to the database
     * @param title - the title of the new advertisement
     * @param description - the description of the new advertisement
     * @return true if save was successful, otherwise false
     */
    @PostMapping(value = "/advertisement/add")
    public boolean addAdvertisements(@RequestParam String title, @RequestParam String description){

        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        return advertisementService.save(advertisement);

    }

    /**
     * Modify an advertisement
     * @param title - the title of the advertisement to edit
     * @param description - the description of the advertisement to edit
     * @param id - the ID of the advertisement to edit
     * @return true if amend was successful, otherwise false
     */
    @PostMapping(value = "/advertisement/edit")
    public boolean editAdvertisements(@RequestParam String title, @RequestParam String description, @RequestParam long id){

        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        advertisement.setId(id);

        return advertisementService.edit(advertisement);
    }

    /**
     * Removes an advertisement from database
     * @param id - The ID of the advertisement to remove
     */
    @GetMapping("/advertisement/delete/{id}")
    public void deleteAdvertisement(@PathVariable long id){
        advertisementService.delete(id);
    }

    /**
     * Sends a web token after successful authentication
     * @param authRequest - AuthRequest with email and password members
     * @return web token
     * @throws Exception
     */
    @PostMapping(value = "/authenticate")
    public String jxtController(@RequestBody AuthRequest authRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Inavalid username or password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }


}
