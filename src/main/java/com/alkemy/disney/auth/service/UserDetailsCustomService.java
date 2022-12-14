package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.dto.UserDTO;
import com.alkemy.disney.auth.entity.UserEntity;
import com.alkemy.disney.auth.repository.UserRepository;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.excepciones.UserAlreadyExistAuthenticationException;
import com.alkemy.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    /*si no se encuentra el usuario, se crea uno,
    la collecion es para los permisos que no vamos a utilizar
     */
    }

    public boolean save(UserDTO userDto){
        UserEntity userEntity = new UserEntity();
        Optional<UserEntity> optUser = Optional.ofNullable(userRepository.findByUsername(userDto.getUsername()));
        if (optUser.isPresent())
            throw new UserAlreadyExistAuthenticationException("Correo ya registrado");

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity =this.userRepository.save(userEntity);
        if(userEntity != null){
            emailService.SendWelcomeEmailTo(userEntity.getUsername());

        }
        return userEntity != null;
    }
}
