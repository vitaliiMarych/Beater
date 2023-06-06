<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <h2><b>List of users:</b></h2>

    <table class="table">
        <thead>
        <tr>
            <th>User</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <#list users as userInList>
            <tr>
                <td>${userInList.username}</td>
                <td> <#list userInList.roles as role> ${role}<#sep> ,</#list></td>
                <td> <a href = "/user/${userInList.id}">edit</a></td>
            </tr>
        </#list>
        <tbody>

        </tbody>
    </table>


</@c.page>