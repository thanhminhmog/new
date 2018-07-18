/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.creat;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
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
    @Result(name = "success", location = "login.html")
    ,
    @Result(name = "fail", type = "redirect", location = "errorPage.html")
    ,
    @Result(name = "input", location = "creatAccount.jsp")
})
  public class CreatAccountAction extends ActionSupport {
//  extend ActionSupport will call CreatAccountAction-validation -> continue with validation of Annotation
//public class CreatAccountAction {
    
    private String username;
    private String password;
    private String confirm;
    private String lastName;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CreatAccountAction() {
    }

    @Action(value = "/creatAccount",
            exceptionMappings = {
                @ExceptionMapping(exception = "java.sql.SQLException", result = "input")
            }
    )
    public String execute() throws Exception {
        tblUserDAO dao = new tblUserDAO();
        boolean result = dao.creatAccount(username, password, lastName, false);
        String url = FAIL;
        if (result) {
            url = SUCCESS;
        }
        return url;
    }

    public String getUsername() {
        return username;
    }

    @RequiredStringValidator(trim = true,
            message = "Username is required Annotation"
            ,key = "insert.username.required"
    )
    @StringLengthFieldValidator(minLength = "6", maxLength = "30",
            message = "Username lenghth is required from 6 to 20 characters Annotation !!!"
            
//            ,key = "insert.username.length"
    )

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(trim = true,
            message = "Password is required Annotation"
            ,key = "insert.password.required"
    )
    @StringLengthFieldValidator(minLength = "6", maxLength = "30",
            message = "Password lenghth is required from 6 to 30 characters Annotation !!"
//            ,key = "insert.password.length"
    )
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    @FieldExpressionValidator(expression = "confirm==password",
            message = "Comfirm password must match password Annotation !!!"
//            ,key = "insert.confirm.match"
    )
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getLastName() {
        return lastName;
    }

    @RequiredStringValidator(trim = true,
            message = "Last Name is required Annotation"
//            ,key = "insert.lastName.required"
    )
    @StringLengthFieldValidator(minLength = "2", maxLength = "50",
            message = "Last Name lenghth is required from 2 to 50 characters Annotation !!"
//            ,key = "insert.lastName.length"
    )
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
