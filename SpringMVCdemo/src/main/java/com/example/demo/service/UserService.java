package com.example.demo.service;

import com.example.demo.api.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    private List<User> userList;

    public UserService() {

        userList = new ArrayList<>();

        User user1 = new User(1, "Ida", 32, "Ida@mail.com");
        User user2 = new User(2, "Hans", 26, "Hans@mail.com");
        User user3 = new User(3, "Lars", 45, "Lars@mail.com");
        User user4 = new User(4, "Ben", 32, "Ben@mail.com");
        User user5 = new User(5, "Eva", 59, "Eva@mail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));

    }

    public Optional<User> getUser(Integer id) {
        Optional optinal = Optional.empty();
        for (User user : userList) {

            if (user.getId() == id) {
                optinal = Optional.of(user);
                return optinal;

            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<User> getAllUsersFromDB(){
        String sql = "SELECT * FROM testDB";
        //List<User> users = JdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class);
        return userList;
    }
}
