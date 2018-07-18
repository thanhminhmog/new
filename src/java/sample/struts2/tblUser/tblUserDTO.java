/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.tblUser;

import java.io.Serializable;

/**
 *
 * @author Saisam
 */
public class tblUserDTO implements Serializable{
    private String username;
    private String password;
    private String lastname;
    private boolean Role;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the Role
     */
    public boolean isRole() {
        return Role;
    }

    /**
     * @param Role the Role to set
     */
    public void setRole(boolean Role) {
        this.Role = Role;
    }

    public tblUserDTO() {
    }

    public tblUserDTO(String username, String password, String lastname, boolean Role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.Role = Role;
    }
    
     
}
