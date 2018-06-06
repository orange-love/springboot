package com.fruitday.boot.service.jpa;

import com.fruitday.boot.domain.jpa.User;

import java.util.List;

public interface UserService {

    Integer addUser(User user);

    List<User> findAllSortByFiels(boolean desc, String... fiels);

    List<User> findAllByPage(int page, int size);

}
