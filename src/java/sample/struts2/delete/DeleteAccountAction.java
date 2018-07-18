/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.delete;

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
@Results({
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "searchLastName", "searchValue", "${lastSearchValue}"
            })
    ,
    @Result(name = "fail", type = "redirect", location = "errorPage.html")
})
public class DeleteAccountAction {

    private String username;
    private String lastSearchValue;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    @Action("/deleteAccount")
    public String execute() throws Exception {
        tblUserDAO dao = new tblUserDAO();
        boolean result = dao.deleteAccount(username);
        String url = FAIL;
        if (result) {
            url = SUCCESS;
        }
        return url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public String getUsername() {
        return username;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

}
