<#import "parts/login.ftlh" as l>
<#import "parts/common.ftlh" as c>

<@c.page>
    <@l.login "/login"/>

    <a href="/registration">Add new user</a>

</@c.page>