/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2.search;

import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import sample.struts2.tblUser.tblUserDAO;
import sample.struts2.tblUser.tblUserDTO;

/**
 *
 * @author Saisam
 */
@ResultPath("/")
@Results({
    @Result(location="search.jsp")
})
public class SearchLastNameAction {

    private String searchValue;
    private List<tblUserDTO> listAccounts;
    private final String SUCCESS = "success";

    public SearchLastNameAction() {
    }
    @Action("/searchLastName")
    public String execute() throws Exception {
        tblUserDAO dao = new tblUserDAO();
        dao.searchLastName(searchValue);
        this.listAccounts = dao.getListUsers();
        return SUCCESS;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<tblUserDTO> getListAccounts() {
        return listAccounts;
    }

    public void setListAccounts(List<tblUserDTO> listAccounts) {
        this.listAccounts = listAccounts;
    }

}
