
<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div class="row">
            <div class="form-group col-lg-4">
                <label class="col-form-label"> User Name :</label>
                <input type="text" class="form-control <#if usernameError??>is-invalid</#if>"
                       value="<#if user??>${user.username}</#if>" name="username" placeholder="Username"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-4">
                <label class="col-form-label"> Password:</label>
                <input type="password" class="form-control <#if passwordError??>is-invalid</#if>" name="password" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="row">
                <div class="form-group col-lg-4">
                    <label class="col-form-label"> Password:</label>
                    <input type="password" class="form-control <#if password2Error??>is-invalid</#if>" name="password2" placeholder="Confirm password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-lg-4">
                    <label class="col-form-label"> Email:</label>
                    <input type="email" class="form-control <#if emailError??>is-invalid</#if>"
                           value="<#if user??>${user.email}</#if>" name="email" placeholder="Some@some.com"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>

        <input type = "hidden" name = "_csrf" value="${_csrf.token}">

        <div class="row mt-3">
            <div class="col-lg-6">
                <input type="submit" class="btn btn-primary" style="width: 150px" value="<#if isRegisterForm> Create<#else> Sign in</#if>"/>
                <#if !isRegisterForm>
                    <a href="/registration" class="ms-4">Add new user</a>
                </#if>
            </div>
        </div>

        <div>

        </div>


    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type = "hidden" name = "_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Sign out</button>
    </form>
</#macro>