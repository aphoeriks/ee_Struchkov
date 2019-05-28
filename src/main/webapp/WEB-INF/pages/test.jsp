<%--
  Created by IntelliJ IDEA.
  User: mikhail.struchkov
  Date: 21.05.2019
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Title</title>
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
        function testPostaddFlower() {

            $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/rest/flower",
            data: JSON.stringify({ name: 'testFlower'}),
                dataType: 'json',
            contentType: 'application/json; charset=UTF-8',

            success: function(data) {
                if(data.status == 'ok') alert(data.message);
                else alert('Failed adding flower: ' + data.status + ', ' + data.message);
            }
        });
    }
</script>
<body>
<button onclick="testPostaddFlower('testFlower')">Click me</button>
</body>
</html>
