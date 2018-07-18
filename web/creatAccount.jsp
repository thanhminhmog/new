<%-- 
    Document   : creatAccount
    Created on : Jul 9, 2018, 9:27:12 AM
    Author     : Saisam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>creat account</title>
      
    </head>
    <body>
        <h1>CREATE ACCOUNT</h1>
        <s:form action="creatAccount" method="post">
            <s:textfield name="username" label="Username"/><s:label value=" (6 - 20 characters)" />            
            <s:password name="password" label="Password"/><s:label value=" (6 - 30 characters)" />            
            <s:password name="confirm" label="Confirm"/>
            <s:textfield name="lastName" label="Last Name"/><s:label value=" (2 - 50 characters)" />            
            <s:submit value="Creat" />
            <s:reset value="Reset" />
        </s:form><br/>
        <s:if test="%{exception.message.contains('duplicate')}">
            <b>
                <font color="red" >${username} is existed!!!</font>
            </b>
        </s:if>
    </body>
</html>
