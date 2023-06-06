<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <a class="navbar-brand" href="/">
            <img src="/img/logo/logo.svg" alt="" width="34" height="27" class="d-inline-block align-text-top">
            <span class="fs-4 ms-0">EATER</span>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto ms-2">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/main">Messages</a>
                </li>

                <#if isAuthorized>
                    <li class="nav-item active">
                        <a class="nav-link" href="/user/profile">Profile</a>
                    </li>

                    <li class="nav-item active">
                        <a class="nav-link" href="/user-messages/${currentUserId}">My messages</a>
                    </li>
                </#if>

                <#if isAdmin>
                    <li class="nav-item active">
                        <a class="nav-link" href="/user">Users</a>
                    </li>
                </#if>
            </ul>

        </div>

        <span class="navbar-text me-3">
              ${name}
            </span>

        <#if isAuthorized>
            <div class="me-2"><@l.logout/></div>
        </#if>
    </div>
</nav>