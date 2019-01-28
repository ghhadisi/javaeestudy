<%--
  Created by IntelliJ IDEA.
  User: ssdeepin
  Date: 19-1-28
  Time: 下午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form >

    <table>
      <tbody>
      <tr>
        <td >
          name:
        </td>
        <td>
          <input type="text" name="username"  id="username" onblur="checkNameExist()" onchange="checkNameExist()"/>
        </td>


      </tr>
      <tr>
        <td>
          password:
        </td>
        <td>
          <input type="password" name="password" />
        </td>
      </tr>
      <tr>
        <td>


          <input type="checkbox"  name="atuo_login" checked="true"/>自动登入
        </td>
        <td>
          <input type="submit" value="提交" />
        </td>
      </tr>
      </tbody>
    </table>

  </form>
  </body>
</html>
