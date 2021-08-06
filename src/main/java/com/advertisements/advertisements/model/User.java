package com.advertisements.advertisements.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for users
 */
@Entity
@Data
@Table(name = "Users")
public class User{

    @Id
    String email;

    String password;

}
