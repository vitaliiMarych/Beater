<#import "parts/common.ftlh" as c>
<#import "parts/addOrEditMessage.ftlh" as ae>

<@c.page>

    <div>Find messages:</div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type = "text" name = "filter" value = "${filter?if_exists}" placeholder="Find by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <@ae.addOrEditMessage true/>

    <#include "parts/messageList.ftlh"/>

</@c.page>