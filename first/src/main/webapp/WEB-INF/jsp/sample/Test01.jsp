<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="http://ajax.googleapis.com/ajax/libs/1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		var name = $('#name');
		var greeting = $('#greeting');
		
		name.bind('keydown', function(){
			setTimeout(greeting.text('HELLO,' + name.val() + '!!!'),0);
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<input id="name" value=""/>
	<p id="greeting"></p>
</body>
</html>