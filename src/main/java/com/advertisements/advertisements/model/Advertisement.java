package com.advertisements.advertisements.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity class for Advertisement
 */

@Entity
@Data
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String title;

    String description;


}
