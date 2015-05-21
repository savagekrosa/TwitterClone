package soa.ejb.local;

import soa.model.entity.UserEntity;

public interface UserManager {
    UserEntity findByUsername(String username);
}
