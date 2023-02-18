<#import "parts/common.ftlh" as c>

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

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddMessage" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add message
    </a>

    <div class="collapse <#if writtenMessage??>show</#if>" id="collapseAddMessage">

        <form method="post" action="add" enctype="multipart/form-data">

                <input type = "text" name = "text" class="form-control <#if textError??>is-invalid</#if> mt-3"
                       value="<#if writtenMessage??>${writtenMessage.text}</#if>"
                       placeholder="Write a message">

                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>

                <div class = "form-row mt-3">
                    <div class="form-group col-md-3">
                        <input type = "text" name = "tag" class="form-control <#if tagError??>is-invalid</#if>"
                               value="<#if writtenMessage??>${writtenMessage.tag}</#if>"
                               placeholder="Tag">
                        <#if tagError??>
                            <div class="invalid-feedback">
                                ${tagError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group col-md-3">
                        <div class="custom-file">
                            <input type="file" name="file" class="form-control" id="customFile">
                            <label class="custom-file-label" for="customFile">Choose file</label>
                        </div>
                    </div>
                </div>

                <input type = "hidden" name = "_csrf" value="${_csrf.token}">
                <button type = "submit" class="btn btn-primary">Add</button>


        </form>

    </div>

    <div>List of messages:</div>

    <div class="card-columns">
    <#list messages as message>
        <div class="card mt-2">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>

            <div class="m-2">
                <span>${message.text}</span>
                <i>${message.tag}</i><br><br>
            </div>

            <div class="card-footer text-muted">
                ${message.authorName}:
            </div>
        </div>

    <#else>
        Messages is not exist
    </#list>
    </div>



</@c.page>