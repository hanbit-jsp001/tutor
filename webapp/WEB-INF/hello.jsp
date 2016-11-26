<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hanbit.jsp.model.Info"%>
<%@page import="com.hanbit.jsp.model.UserInfo"%>
<%
	Info info = (Info) request.getAttribute("model");
	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("session");
	
	if (userInfo == null) {
		userInfo = new UserInfo("로그인하세요.");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=info.getName() %></title>
</head>
<body>
<%=userInfo.getId() %>
<input type="text" id="txtName">
<button id="btnAjax">AJAX 호출</button>
<div id="divButtons">
<%
// 넘버를 초기화
int number = info.getCountOfButtons();

for (int i=0;i<number;i++) {
%>
<button id="btn<%=i %>"><%=i %> 번째</button>
<% } %>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#btnAjax").on("click", function() {
		var url = "/welcome/" + $("#txtName").val();	
		
		$.ajax({
			url: url,
			method: "POST",
			success: function(info) {
				document.title = info.name;
				
				var buttons = "";
				
				for (var i=0;i<info.countOfButtons;i++) {
					buttons += "<button id='btn"+i+"'>"+i+"번째</button> ";
				}
				
				$("#divButtons").html(buttons);
			}
		});
	});
});
</script>
</body>
</html>









