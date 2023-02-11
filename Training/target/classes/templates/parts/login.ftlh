
<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div class="row">
            <div class="form-group col-lg-4">
                <label class="col-form-label"> User Name :</label>
                <input type="text" class="form-control" name="username" placeholder="username"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-4">
                <label class="col-form-label"> Password:</label>
                <input type="password" class="form-control" name="password" placeholder="password"/>
            </div>
        </div>

        <input type = "hidden" name = "_csrf" value="${_csrf.token}">

        <div class="row col-lg-6">
            <input type="submit" class="btn btn-primary" value="
            <#if isRegisterForm>
                Створити
            <#else>
                Увійти
            </#if>"/>

            <#if !isRegisterForm>
                <div class="ml-4 mt-2">
                    <a href="/registration">Add new user</a>
                </div>
            </#if>
        </div>


    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type = "hidden" name = "_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Вийти</button>
    </form>
</#macro>