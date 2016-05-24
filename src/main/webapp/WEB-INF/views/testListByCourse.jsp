<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>display students by course</title>

    <sql:query var="student" dataSource="jdbc/studentdb">
        select studentId, name, course from Student where course = "science"
    </sql:query>
</head>
<body>
    <c:forEach var="row" items="${student.rows}">
        Name ${row.name} Course ${ row.course}<br/>
    </c:forEach>

</body>
</html>
