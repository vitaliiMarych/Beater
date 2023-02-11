<#import "parts/login.ftlh" as l>
<#import "parts/common.ftlh" as c>

<@c.page>
    <h4><b>Registration</b></h4>

    <@l.login "/registration" true/>
    <br>
    <a style = "color:red">${message?if_exists}</a>

</@c.page>