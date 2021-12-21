package org.techscribbler.user.service;

import org.springframework.stereotype.Component;
import org.techscribbler.user.bean.User;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    static{
        users.add(new User(1, "Adam",new Date()));
        users.add(new User(3, "Bob",new Date()));
        users.add(new User(2, "Eve",new Date()));
    }
    private static int userCount= users.size();
    public List<User> findAll(){
        return users;
    }
    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
    public User update(int id, User argUser){
        for (User user : users) {
            if (argUser.getId() == id) {
                if(argUser.getName()!=null)
                    user.setName(argUser.getName());
                if(argUser.getBirthDate()!=null){
                    user.setBirthDate(argUser.getBirthDate());
                }
            }
            return user;
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iter =users.iterator();
        while(iter.hasNext()){
            User user= iter.next();
            if (user.getId() == id) {
                iter.remove();
            }
            return user;
        }
        return null;
    }
}
