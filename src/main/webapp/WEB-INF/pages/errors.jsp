<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
Error page

 <div id="status_message">${errMsg}</div>

 <!--
     Failed URL: ${url}
     Exception:  ${exception.message}
         <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
     </c:forEach>
     -->