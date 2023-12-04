package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.Api;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody@Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("user added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody@Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdated=userService.updateUser(id, user);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("user updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("wrong id"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        Boolean isDeleted=userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("user deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("wrong id"));
    }
}
