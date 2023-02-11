<#import "parts/common.ftlh" as c>

<@c.page>

    <div>Знайти повідомлення:</div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type = "text" name = "filter" value = "${filter?if_exists}" placeholder="Знайти за тегом">
                <button type="submit" class="btn btn-primary ml-2">Пошук</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddMessage" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавити повідомлення
    </a>

    <div class="collapse" id="collapseAddMessage">

        <form method="post" action="add" enctype="multipart/form-data">


                <input type = "text" name = "text" class="form-control mt-3" placeholder="Введіть повідомлення">

                <div class = "form-row mt-3">
                    <div class="form-group col-md-3">
                        <input type = "text" name = "tag" class="form-control" placeholder="Тег">
                    </div>
                    <div class="form-group col-md-3">
                        <div class="custom-file">
                            <input type="file" name="file" class="form-control" id="customFile">
                            <label class="custom-file-label" for="customFile">Вибрати файл</label>
                        </div>
                    </div>
                </div>

                <input type = "hidden" name = "_csrf" value="${_csrf.token}">
                <button type = "submit" class="btn btn-primary">Добавити</button>


        </form>

    </div>

    <div>Список повідомлень:</div>

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
        Повідомлень нема

    </#list>
    </div>



</@c.page>