package com.alkemy.disney.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    /*Validacion de mail*/
    @Email(message = "Username must be an email" )
    private String username;
    /*tamaño minimo de la pass*/
    @Size(min = 8)
    private String password;


}
