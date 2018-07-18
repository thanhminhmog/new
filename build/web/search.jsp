<%-- 
    Document   : search
    Created on : Jun 29, 2018, 9:39:24 AM
    Author     : Saisam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEARCH</title>
    </head>
    <body>
        <h1>
            Welcome, <s:property value="username"/>
            <s:property value="%{#session.USERNAME}"/>
        </h1>
        <h1>SEARCH PAGE</h1>
        <form action="searchLastName">
            <%--Search Value <input type="text" name="searchValue" value="<s:property value="searchValue"/>" />--%>
            <s:textfield name="searchValue" label="Search Value" />
            <input type="submit" value="Search" />
        </form><br/>
        <s:if test="%{!searchValue.isEmpty()}">
            <s:if test="%{listAccounts != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listAccounts" status="counter">
                            <s:form action="updatePassRole" theme="simple" >
                                <tr>
                                    <td>
                                        <s:property value="%{#counter.count}"/>
                                    </td>
                                    <td>
                                        <s:property value="username"/>
                                        <s:hidden name="username" value="%{username}"/>
                                    </td>
                                    <td>
                                        <s:property value="password"/>                                    
                                        <s:password value="%{password}" name="password"/>
                                        
                                    </td>
                                    <td>
                                        <s:property value="lastname"/>
                                        <s:property value="%{#lastname}"/>
                                    </td>
                                    <td>
                                        <s:checkbox name="Role" value="true"/>
                                        <s:hidden name="Role" value="%{Role}"/>
                                    </td>
                                    <td>
                                        <s:url id="delLink" action="deleteAccount">
                                            <s:param name="username" value="username"/>
                                            <s:param name="lastSearchValue" value="searchValue"/>
                                        </s:url>
                                        <s:a href="%{delLink}">Delete</s:a>
                                        </td>
                                        <td>
                                        <s:submit value="Update"/>
                                        <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                <h2>No record is matched</h2>
            </s:else>
        </s:if>

    </body>
</html>
