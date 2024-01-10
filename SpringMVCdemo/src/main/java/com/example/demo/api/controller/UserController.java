package com.example.demo.api.controller;

import com.example.demo.api.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    //Test method in Postman

    //localhost:8080/data
    /*
    [
        "A",
        "B",
        "C"
    ]
    */

    @GetMapping("/data")
    public List getData(){
        List l = new ArrayList<>();
        l.add("A");
        l.add("B");
        l.add("C");
        return l;
    }

    @Autowired
    private UserService userService;
    //localhost:8080/AllUser
    /*
    [
     {
        "id": 1,
        "name": "Ida",
        "age": 32,
        "email": "Ida@mail.com"
    },
    {
        "id": 1,
        "name": "Hans",
        "age": 26,
        "email": "Hans@mail.com"
    },
    {
        "id": 1,
        "name": "Lars",
        "age": 45,
        "email": "Lars@mail.com"
    },
    {
        "id": 1,
        "name": "Ben",
        "age": 32,
        "email": "Ben@mail.com"
    },
    {
        "id": 1,
        "name": "Eva",
        "age": 59,
        "email": "Eva@mail.com"
    }
]
     */
    @GetMapping("/AllUser")

    public  List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //localhost:8080/User?id=1
/*
{
    "id": 1,
    "name": "Ida",
    "age": 32,
    "email": "Ida@mail.com"
}
 */

    @GetMapping("/User")
    public User getUser(@RequestParam Integer id){

        Optional user = userService.getUser(id);
        if(user.isPresent()){
            return(User) user.get();
        }
        return null;

    }


}
