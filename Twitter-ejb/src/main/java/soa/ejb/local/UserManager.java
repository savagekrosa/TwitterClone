package soa.ejb.local;

import soa.model.entity.UserEntity;

import java.io.Serializable;

public interface UserManager extends Serializable{
    UserEntity findByUsername(String username);
    boolean addUser(String username, String mail,String password);
    void deleteUser(int id);

}
