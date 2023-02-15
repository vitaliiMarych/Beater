<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">BEATER</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto ml-3">
            <li class="nav-item active">
                <a class="nav-link" href="/">Початкова сторінка</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/main">Повідомлення</a>
            </li>
            <#if isAdmin>
                <li class="nav-item active">
                    <a class="nav-link" href="/user">Список користувачів</a>
                </li>
            </#if>

            <#if isAuthorized>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/profile">Профіль</a>
                </li>
            </#if>
        </ul>

        <span class="navbar-text mr-3">
          ${name}
        </span>

        <#if isAuthorized>
            <@l.logout/>
        </#if>

    </div>
</nav>