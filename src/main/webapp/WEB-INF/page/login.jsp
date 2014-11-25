<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.verphen.filter.Token"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>防止表单重复提交</title>  
    <script type="text/javascript">
				<!--
					var checkSubmitFlg = true;
					function checkSubmit() {
						if (true == checkSubmitFlg) {
							document.theForm.btnSubmit.disable = true;
							document.theForm.submit();
							checkSubmitFlg = false;
						} else {
							alert("你已经提交 了表单，请不要重复提交！");
						}
					}
				//-->
				</script>  
</head>  
  
<body>  
<%
  	Token processor = Token.getInstance();
  	String token = processor.getToken(request);
  %>  
<form action="handler" name="theForm" method="post">  
   <table>  
    <tr>  
     <td>用户名：</td>  
     <td><input type="text" name="username" /></td>  
    </tr>  
    <tr>  
     <td>邮件地址：</td>  
     <td>  
      <input type="text" name="email" />  
      <input type="hidden" name="ltai701" value="<%=token%>" />  
     </td>  
    </tr>  
    <tr>  
     <td><input type="reset" value="重填" /></td>  
     <td><input type="button" value="提交" name="btnSubmit"
					onclick="checkSubmit()" /></td>  
    </tr>  
   </table>  
</form> 
</body>
</html>