package com.service;

import com.bean.User;
import com.dao.UserDao;
import com.util.PageUtil;

import java.util.List;
//中间层  --业务层
public class UserService {
    //引入dao
    private UserDao userDao =new UserDao();

    public List<User> showUsers() {
        return userDao.showUsers();
    }
    public void addUser(User user) {
        UserDao.insertUser(user);
    }
    public User FindOneById(int id){
        return userDao.selectOneUser(id);
    }
    public  void updateUser(User user){
        UserDao.updateUse(user);
    }
    public void deleteOoneById(int id){
        UserDao.deleteUser(id);
    }

    public List<User> showPageUsers(PageUtil page) {
        return userDao.showPageUsers(page);
    }
    public int selectCount(){
        return userDao.selectCount();
    }
}
