/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.authentication;

import dco.prokurimi.entity.User;
import javax.ejb.Stateless;

import java.io.Serializable;
import javax.ejb.Remove;
import javax.faces.application.FacesMessage;

import javax.persistence.CacheRetrieveMode;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pirota
 */
@Stateless
public class AuthenticationBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // shfrytezuesit dhe fjalekalimet do ti manipulojme permes nje JPA objekti
    @PersistenceContext(unitName="ProkurimiPU")
    private EntityManager em;
    private boolean authenticated = false;
    private String username = null;
    private String password = null;
    HttpSession session = null;
    User user;
    
    public AuthenticationBean(){
        
    }
    
    public void findUser(){
        try {
            em.flush();
            
            getUser();
            // the following si a JPA query to look for the existance of one or more users by a spcecific name
            
            Query userQry = em.createQuery(
                        "select object(u) from User u "
                        + "where u.username = :username").setParameter("username", getUser().getUsername().toUpperCase());
            
            // Enable forced database query
            userQry.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            setUser((User) userQry.getSingleResult());
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Jeni kycur me sukuses",""));
        } catch (Exception e) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password", ""));
            setUser(null);
        }
    }
    
    public HttpSession getSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession(false);
        return session;
    }
    
    public boolean login(){
        
        HttpSession session = getSession();
        HttpServletRequest request = null;
        Query userQry = null;
        System.out.println("In the login method ... " + getUser().getUsername());
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(getUser().getUsername(), this.password);
            
            session.setMaxInactiveInterval(1800);
            
            em.flush();
            
            userQry = em.createQuery(
                        "select count(u) from User u "
                    +   "where u.username = :username").setParameter("username", getUser().getUsername().toUpperCase());
            
            // Enable forced query
            userQry.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            Long count = (Long)userQry.getSingleResult();
            if (count > 0){
                
                userQry  = em.createQuery(
                        "select object(u) from User u "
                        + "where u.username = :username").setParameter("username", getUser().getUsername().toUpperCase());
                
                // Enable forced database query 
                userQry.setHint("javax.persistence.cache.retriveMode", CacheRetrieveMode.BYPASS);
                
                setUser((User) userQry.getSingleResult());
                System.out.println("Setting User, user exists in database with role ->" +
                                                                            user.getSecurityRole());
                setAuthenticated(true);
                session.setAttribute("authenticated", new Boolean(true));
                
            } else {
                // User cannot authenticate successfully
                FacesContext.getCurrentInstance().addMessage(null, new 
                        FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password",""));
                }
            
            FacesContext.getCurrentInstance().addMessage(null, new 
                        FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Authenticated",""));
            return authenticated;
        } catch (NoResultException| ServletException ex) {
            setUser(null);
            setAuthenticated(false);
            session = getSession();
            session.setAttribute("authenticated", new Boolean(false));
            if (request != null){
                try {
                    request.logout();;
                } catch (ServletException ex1) {
                    System.out.println("AuthBean#login Error: " + ex);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new 
                        FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid username/password",""));
            return false;
            
        } finally {
            setPassword(null);
        }
    }
    
    /**
     * @return the isAuthenticated
     */
    public boolean isAuthenticated(){
        if (getSession().getAttribute("authenticated") != null){
            boolean auth = (Boolean) getSession().getAttribute("authenticated");
            if (auth){
                authenticated = true;
            }
        } else {
            authenticated = false;
        }
        
        //System.out.println("Are we authenticated? " + auth);
        return authenticated;
    }
    
    /**
     * @param isAuthenticated the isAuthenticated to set
     */
    public void setAuthenticated(boolean isAuthenticated){
        this.authenticated = isAuthenticated;
    }
    
    
    @Remove
    public void remove(){
        System.out.println("Being removed from session...");
        setUser(null);
    }
    
    /**
     * @return the username
    */
    public String getUsername(){
        try {
            System.out.println("The current username is: " + user.getUsername());
            username = getUser().getUsername();
            
        } catch (NullPointerException ex){
            
        }
        return username;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username){
        getUser().setUsername(username);
        System.out.println("Just set the username to: " + getUser().getUsername());
        this.username = null;
        
    }
    
    /**
     * @return the password
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * @return the user
     */
    public User getUser(){
        if (this.user == null) {
            user = new User();
        }
        return user;
    }
    
    /**
     * @param user the user to set
     */
    public void setUser(User user){
        this.user = user;
    }
    
    
  
    
}
