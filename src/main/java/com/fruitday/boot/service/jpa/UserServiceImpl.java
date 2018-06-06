package com.fruitday.boot.service.jpa;

import com.fruitday.boot.dao.jpa.UserRepository;
import com.fruitday.boot.domain.jpa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

//@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userdao;

    public String moni(int num) {
        String resault = num + ":" + this.userdao.count();
        System.out.println(resault);
        return resault;
    }

    /**
     * 保存实体，并修改
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        userdao.save(user);
        Integer id = user.getId();
        user.setName(id + ":" + user.getName());
        userdao.save(user);
        return id;
    }

    /**
     * 查询数据，并对字段进行排序
     * @param desc
     * @param fiels
     * @return
     */
    @Override
    public List<User> findAllSortByFiels(boolean desc, String... fiels) {
        Sort sort = new Sort(desc ? Sort.Direction.DESC : Sort.Direction.ASC, fiels);
        return userdao.findAll(sort);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<User> findAllByPage(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<User> users = userdao.findAll(pageRequest);
        int totalPages = users.getTotalPages();
        long count = users.getTotalElements();
        return users.getContent();
    }

    public User findByName(String name) {
        return userdao.findUser(name);
    }
}
