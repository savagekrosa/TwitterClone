package soa.web.beans;

import soa.ejb.local.UserManager;
import soa.model.entity.UserEntity;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("userBacking")
@SessionScoped
public class UserBacking implements Serializable {
    @Inject
    private transient UserManager userManager;

    public UserEntity getLoggedUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String username = context.getExternalContext().getRemoteUser();
        UserEntity loggedUser = userManager.findByUsername(username);
        return loggedUser;
    }

    public boolean isLoggedIn() {
        return getLoggedUser() != null;
    }
}
