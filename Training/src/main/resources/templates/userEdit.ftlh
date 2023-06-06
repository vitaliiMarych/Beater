<#import "parts/common.ftlh" as c>

<@c.page>
    <h2>Edit user ${user.username}</h2>

    <form method="post" action="saveUser" class="">

        <div class="row g-2">
        <input class="form-control col-lg-4" type="text" name = "username" value="${user.username}">

        <div class="btn-group" role="group">
            <#list roles as role>
                <input type="checkbox" name="${role}" class="btn-check" id="${role}" autocomplete="off" ${user.roles ? seq_contains(role)?string("checked", "")}>
                <label class="btn btn-outline-success" for="${role}">${role}</label>
            </#list>
        </div>
        </div>

        <input type="hidden" value="${user.id}" name="userId">
        <input type = "hidden" name = "_csrf" value="${_csrf.token}">
        <div class="row">
            <button class="btn btn-success mt-2 ms-2" type="submit">Save</button>
        </div>
    </form>

</@c.page>