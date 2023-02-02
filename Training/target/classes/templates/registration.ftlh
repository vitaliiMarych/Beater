<#import "parts/login.ftlh" as l>
<#import "parts/common.ftlh" as c>

<@c.page>
    <div>Registration</div>

    <@l.login "/registration"/>
    <br>
    <a style = "color:red">${message?if_exists}</a>

</@c.page>