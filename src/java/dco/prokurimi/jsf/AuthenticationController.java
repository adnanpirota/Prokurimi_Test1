/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.jsf;

import dco.prokurimi.authentication.AuthenticationBean;
import dco.prokurimi.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author pirota
 */
@ManagedBean
@SessionScoped
public class AuthenticationController implements Serializable {
    
    @EJB
    private AuthenticationBean authenticationFacade;
    private String username;
    private User user;
    private boolean authenticated;
    private HttpSession session = null;
    private String userAgent;

    /**
     * Creates a new instance of AuthenticationController
     */
    public AuthenticationController() {
        getUser();
    }
    
    public HttpSession getSession(){
        // if(session == null){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();
        
        return session;
    }
    
    /**
     * @return the username
     */
    public String getUsername(){
        this.username = getUser().getUsername();
        return this.username;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username){
        this.username = username;
        getUser().setUsername(username);
    }
    
    /**
     * @return the password
     */
    public String getPassword(){
        return authenticationFacade.getPassword();
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password){
        authenticationFacade.setPassword(password);
    }
    
    public User getUser(){
        if (this.user == null) {
            user = new User();
            setUser(authenticationFacade.getUser());
        }
        
        return user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public String login(){
        authenticationFacade.setUser(getUser());
        boolean authResult = authenticationFacade.login();
        
        if (authResult){
            this.authenticated = true;
            
            setUser(authenticationFacade.getUser());
            
            return "SUCCESS_LOGIN";
        } else {
            this.authenticated = false;
            setUser(null);
            return "BAD_LOGIN";
        }
    }
    
    public String logout(){
        user = null;
        this.authenticated = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "SUCCESS_LOGOUT";
    }
    
    /**
     * @return the authenticated
     */
    public boolean isAuthenticated(){
        try {
            // Allows subsequent requests to obtain status from the ssession state
            boolean auth = (Boolean) getSession().getAttribute("authenticated");
            if (auth){
                this.authenticated = true;
            } else {
                authenticated = false;
            }
        } catch (Exception e){
            this.authenticated = false;
        }
        return authenticated;
    }
    
    public void setAuthenticated(boolean authenticated){
        this.authenticated = authenticated;
    }
    
}
