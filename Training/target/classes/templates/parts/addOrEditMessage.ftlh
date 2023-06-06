<#macro addOrEditMessage isAdd>

    <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseAddMessage" role="button" aria-expanded="false" aria-controls="collapseExample">
        <#if isAdd>
            Add message
        <#else>
            Edit message
        </#if>
    </a>

    <div class="collapse <#if writtenMessage??>show</#if>" id="collapseAddMessage">

        <form method="post" enctype="multipart/form-data" class="">

            <input type = "text" name = "text" class="form-control <#if textError??>is-invalid</#if> mt-3"
                   value="<#if writtenMessage??>${writtenMessage.text}</#if>"
                   placeholder="Write a message">

            <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>

            <div class = "d-flex  mt-2">

                    <input type = "text" name = "tag" class="form-control form-control-sm me-2 <#if tagError??>is-invalid</#if>"
                           value="<#if writtenMessage??>${writtenMessage.tag}</#if>"
                           placeholder="Tag">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>

                    <div class="custom-file">
                        <input type="file" name="file" class="form-control col-2" id="customFile">
                    </div>
            </div>

            <input type="hidden" name="id" value="<#if (writtenMessage.id)??>${writtenMessage.id}</#if>">
            <input type = "hidden" name = "_csrf" value="${_csrf.token}">

            <button type = "submit" class="btn btn-primary mt-2" style="width: 125px">
                <#if isAdd>
                    Add
                <#else>
                    Save
                </#if>

            </button>


        </form>

    </div>
</#macro>