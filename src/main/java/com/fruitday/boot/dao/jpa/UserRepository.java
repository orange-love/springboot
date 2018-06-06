package com.fruitday.boot.dao.jpa;

import com.fruitday.boot.domain.jpa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Autowired
    EntityManager em = null;

    /**
     * 基于方法名称查询
     * @return
     */
    User findByName();

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    //    @Query(value = "select u from User u where u.name=:name")
    @Query(value = "select u from User u where u.name=?1",nativeQuery = true)
    User findUser(String name);

    /**
     * 统计查询
     * @return
     */
    @Query(value = "select u.id,count(u.name) from User u")
    List<Object[]> queryUserCount();

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Query(value = "select u from User u")
    Page<User> queryUsers(Pageable page);

    /**
     * 修改数据
     * @param name
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update User u set u.name=?1 where u.id=?2")
    int updateName(String name, Integer id);
}
