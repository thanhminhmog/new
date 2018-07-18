/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.login;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
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
    @Result(location="search.jsp"),
    @Result(name="fail", type="redirect", location="invalid.html")
})
public class LoginAction {
    private String username;
    private String password;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    public LoginAction() {
    }
    
    @Action("/login")
    public String execute() throws Exception {
            tblUserDAO dao = new tblUserDAO();
            boolean result = dao.checkLogin(username, password);
        String url = FAIL;
        if(result ){
            url = SUCCESS;           
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();            
            session.put("USERNAME", username);
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
    
}
