/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.update;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import sample.struts2.tblUser.tblUserDAO;

/**
 *
 * @author Saisam
 */
@ResultPath("/")
@Results ({
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "searchLastName", "searchValue", "${lastSearchValue}"
            }
            ),
    @Result (name = "fail", type = "redirect", location = "errorPage.html")
})
public class UpdatePassRoleAction {
    private String username;
    private String password; 
    private boolean Role;
    private String lastSearchValue;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    public UpdatePassRoleAction() {
    }
    @Action("/updatePassRole")
    public String execute() throws Exception {
        tblUserDAO dao = new tblUserDAO();
        boolean result = dao.updateAccount(password, Role, username);
        String url = FAIL;
        if (result) {
            url = SUCCESS;            
        }
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return Role;
    }

    public void setRole(boolean Role) {
        this.Role = Role;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }
    
}
