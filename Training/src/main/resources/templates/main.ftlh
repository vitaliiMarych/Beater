<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

    <div>
        <@l.logout/>
    </div>

    <div>
        <a href="/user">Список користувачів</a>
    </div>

    <div>
        <form method="post" action="add">
            <input type = "text" name = "text" placeholder="Введіть повідомлення">
            <input type = "text" name = "tag" placeholder="Тег">
            <input type = "hidden" name = "_csrf" value="${_csrf.token}">
            <button type = "submit">Добавити</button>
        </form>
    </div>

    <div>Знайти повідомлення:</div>

    <form method="get" action="/main">
        <input type = "text" name = "filter" value = "${filter?if_exists}">
        <button type="submit">Пошук</button>
    </form>

    <div>Список повідомлень:</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <strong>${message.authorName}:</strong><br>
            <span>${message.text}</span>
            <i>${message.tag}</i><br><br>
        </div>

    <#else>
        Повідомлень нема

    </#list>



</@c.page>