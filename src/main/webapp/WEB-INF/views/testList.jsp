<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="student" dataSource="jdbc/studentdb">
    select studentId, name, course from Student
</sql:query>

<html>
<head>
    <title>display all students</title>
</head>
<body>

<h2>Results</h2>

<c:forEach var="row" items="${student.rows}">
    Name ${row.name} Course ${ row.course}<br/>
</c:forEach>

</body>
</html>