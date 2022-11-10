<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <title>The Dream Team</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body class="container">
        <h1 class="text-center">Results</h1>
        <c:if test="${results instanceof Map<?,?>}" >
            <table class="table table-bordered mx-auto">
                <thead>
                    <tr>
                        <th>Agent Id</th>
                        <th>Total Premium</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="Map.Entry<Integer,Double> result" items="${results.entrySet()}">
                    <tr>
                        <td>${result.getKey()}</td>
                        <td>$${result.getValue()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${results instanceof Customer}" >
            <div class="text-center">
                <ul>
                    <li>Id - ${results.id}</li>
                    <li>First Name - ${results.firstName}</li>
                    <li>Last Name - ${results.lastName}</li>
                    <li>Age - ${results.age}</li>
                    <li>State - ${results.state}</li>
                    <li>Region - ${results.region}</li>
                    <li>Primary Language - ${results.primaryLanguage}</li>
                    <li>Secondary Language - ${results.secondaryLanguage}</li>
                    <li>Agent Id - ${results.agentId}</li>
                </ul>
            </div>
        </c:if>
        <c:if test="${!results instanceof Map<?,?> && !results instanceof Customer}" >
            <div class="text-center">
                <p>${results}</p>
            </div>
        </c:if>

        <a href="index.jsp">Back to Home</a>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
