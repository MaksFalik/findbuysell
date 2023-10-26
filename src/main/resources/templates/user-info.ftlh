<!DOCTYPE html>
<html>
<head>
    <title>FindBySell</title>
</head>
<body>
<h1>FindBySell</h1><hr>
<h3>Подробная информация о продовце ${user.name}</h3>
<b>Номер телефона: </b> ${user.numberPhone}<br>
<b>Почта: </b> ${user.email}<br>
<b>Имя: </b> ${user.name}<br>

<h4>Объявление пользователя ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b>${product.price} руб. |
            <a href="/product/${product.id}"></a>
        </p>
    </div>
    <#else>
    <h3>Объявлений нет</h3>
</#list>


</body>
</html>