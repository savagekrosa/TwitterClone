package soa.web.beans;

import soa.ejb.local.UserManager;
import soa.model.entity.UserEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name="userBacking")
@SessionScoped
public class UserBacking {
    @Inject
    UserManager userManager;

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
