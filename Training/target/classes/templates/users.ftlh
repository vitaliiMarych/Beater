<#import "parts/common.ftlh" as c>

<@c.page>
    <h2><b>Список користувачів:</b></h2>

    <table>
        <thead>
        <tr>
            <th>User</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td> <#list user.roles as role> ${role}<#sep> ,</#list></td>
                <td> <a href = "/user/${user.id}">edit</a></td>
            </tr>
        </#list>
        <tbody>

        </tbody>
    </table>


</@c.page>