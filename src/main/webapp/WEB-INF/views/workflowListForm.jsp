<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>Workflow List</title></head>
<body>
<table cellspacing="0" cellpadding="2" id="table" class="display">
    <thead>
    <tr>
        <th width="200">Workflow Name</th>
        <th width="200">Operations</th>
    </tr>
    </thead>
    <tbody id="workflowList">
    <c:forEach items="${workflowList}" var="workflow" varStatus="loopStatus">
       <tr>
           <td>
               <c:out value="${workflowList.name}"/>
           </td>
       </tr> 
    </c:forEach>
    </tbody>
</table>   
</body>
</html>