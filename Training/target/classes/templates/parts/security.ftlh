<#assign
    isAuthorized = Session.SPRING_SECURITY_CONTEXT??
/>

<#if isAuthorized>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        name = "Anonim"
        isAdmin = false
    >
</#if>

