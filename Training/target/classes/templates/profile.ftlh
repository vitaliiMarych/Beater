<#import "parts/common.ftlh" as c>

<@c.page>
    <h4>${username}</h4>

    <form method="post">

        <div class="row">
            <div class="form-group col-lg-4">
                <label class="col-form-label"> Password:</label>
                <input type="password" class="form-control" name="password" placeholder="password"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-4">
                <label class="col-form-label"> Email:</label>
                <input type="email" class="form-control" name="email" placeholder="some@some.com" value="${email!''}"/>
            </div>
        </div>

        <input type = "hidden" name = "_csrf" value="${_csrf.token}">

        <div class="row col-lg-6">
            <input type="submit" class="btn btn-primary" value="Зберегти"/>
        </div>
    </form>
</@c.page>