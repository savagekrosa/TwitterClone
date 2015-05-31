package soa.ejb.local;

import soa.model.entity.UserEntity;

import java.io.Serializable;

public interface UserManager extends Serializable{
    UserEntity findByUsername(String username);
}
