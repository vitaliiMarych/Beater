<#import "parts/login.ftlh" as l>
<#import "parts/common.ftlh" as c>

<@c.page>

    <#if RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

    <@l.login "/login" false/>
</@c.page>