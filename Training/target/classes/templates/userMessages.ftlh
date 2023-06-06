<#import "parts/common.ftlh" as c>
<#import "parts/addOrEditMessage.ftlh" as ae>
<#include "parts/security.ftlh">

<@c.page>
    <#if writtenMessage?? && authorId == currentUserId>
        <@ae.addOrEditMessage false/>
    </#if>

    <#include "parts/messageList.ftlh"/>
</@c.page>