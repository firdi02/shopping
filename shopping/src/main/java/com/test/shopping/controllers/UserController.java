package com.test.shopping.controllers;

import com.test.shopping.dto.JwtResponse;
import com.test.shopping.dto.LoginDTO;
import com.test.shopping.dto.UserRequest;
import com.test.shopping.service.UserService;
import com.test.shopping.utils.JwtUtility;
import org.modelmapper.ModelMapper;
import com.test.shopping.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home(){
        return "latihan JWT";
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserRequest data){
        HashMap<String, Object> response= new HashMap<>();
        User user= modelMapper.map(data, User.class);
        userService.registerAppUser(user);
        response.put("message","register berhasil");
        response.put("data",user);
        response.put("success",true);
        return response;

    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody LoginDTO loginDTO) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new Exception("Invalid credential", e);

        }
        final UserDetails userDetails =
                userService.loadUserByUsername(loginDTO.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);


    }

    @GetMapping("/user/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }


}