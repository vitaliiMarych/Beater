<#import "parts/common.ftlh" as c>
<#import "parts/addOrEditMessage.ftlh" as ae>
<#include "parts/security.ftlh">

<@c.page>
    <div>

    <h3>Username - ${userChannel.username}</h3>

    <#if authorId != currentUserId>
        <#if !isSubsriber>
            <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Subscribe</a>
        <#else>
            <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
        </#if>
    </#if>


    </div>

    <div class="container">
        <div class="row mt-3">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <#if writtenMessage?? && authorId == currentUserId>
        <@ae.addOrEditMessage false/>
    </#if>

    <#include "parts/messageList.ftlh"/>
</@c.page>