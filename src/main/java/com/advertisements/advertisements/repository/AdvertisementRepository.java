package com.advertisements.advertisements.repository;


import com.advertisements.advertisements.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Advertisement JPA Repository interface
 */
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {


}
