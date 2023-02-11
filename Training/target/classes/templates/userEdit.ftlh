<#import "parts/common.ftlh" as c>

<@c.page>
    <h2>Редагувати користувача ${user.username}</h2>

    <form method="post" action="saveUser">
        <input type="text" name = "username" value="${user.username}">

        <#list roles as role>
            <label>
                <input type="checkbox" name="${role}" ${user.roles ? seq_contains(role)?string("checked", "")}>
                ${role}
            </label>
        </#list>

        <input type="hidden" value="${user.id}" name="userId">
        <input type = "hidden" name = "_csrf" value="${_csrf.token}">
        <button type="submit">Зберегти</button>
    </form>

    <a style = "color:red">${message?if_exists}</a>

</@c.page>