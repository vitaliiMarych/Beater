<#include "security.ftlh">
<#import "pager.ftlh" as p>

<div class="mt-3">List of messages</div>

<@p.pager url page></@p.pager>

<div class="">
    <#list page.content as message>
        <div class="col">
        <div class="card mt-2 border-primary">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top p-2 rounded-1 w-25">
            </#if>

            <div class="m-2">
                <span>${message.text}</span><br>
                <i>#${message.tag}</i>
            </div>

            <div class="card-footer text-muted">
                <a href="/user-messages/${message.author.id}">${message.authorName}</a>

                <#if message.author.id == currentUserId>
                    <a class="btn btn-primary btn-sm float-end" href="/user-messages/${message.author.id}?message=${message.id}" style="width: 150px">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
        </div>

    <#else>
        Messages is not exist
    </#list>

</div>
