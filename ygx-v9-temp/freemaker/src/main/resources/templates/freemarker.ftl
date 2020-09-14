<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    name:<span>${product.name}</span>
    //Use ?date, ?time, or ?datetime to tell FreeMarker the exact type.
    birthday:<span>${product.birthday?date}</span>
    birthday:<span>${product.birthday?time}</span>
    birthday:<span>${product.birthday?datetime}</span>
</body>
</html>