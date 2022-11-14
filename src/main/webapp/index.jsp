<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <title>The Dream Team</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body class="container">
        <h1 class="text-center">The Dream Team Project</h1>
        <p class="my-3">
            Below there is a button for each of the methods that were written for the State Farm coding competition.
            Upon clicking on one you will be sent to a results page where you can see the results for said report.
        </p>
        <hr class="border border-5 border-dark">
        <form class="my-3" action="runDataTool" method="GET">
            <input class="btn btn-success my-5" type="submit" name="reportType" value="Get Number Of Open Claims"/>
            <input class="btn btn-success my-5" type="submit" name="reportType" value="Get Map Of Agent Premiums"/>
            <input class="btn btn-success my-5" type="submit" name="reportType" value="Get Customer With Highest Total Premium"/>
            <hr>
            <div class="form-group my-5">
                <label for="agentId" class="fw-bold">Agent Id</label>
                <input type="text" class="form-control"
                       name="agentId" id="agentId"/>
                <input class="btn btn-success mt-3" type="submit" name="reportType" value="Get Number Of Customers For Agent Id"/>
            </div>
            <hr>

            <div class="form-group my-5">
                <label for="stateAgent" class="fw-bold">State (Enter abbreviation ex. WI, IL ect.)</label>
                <input type="text" class="form-control"
                       name="stateAgent" id="stateAgent"/>
                <input class="btn btn-success mt-3" type="submit" name="reportType" value="Get Number Of Agents For State"/>
            </div>

            <hr>
            <div class="form-group my-5">
                <label for="customerId" class="fw-bold">Customer Id</label>
                <input type="text" class="form-control"
                       name="customerId" id="customerId"/>
                <input class="btn btn-success mt-3" type="submit" name="reportType" value="Sum Monthly Premium For Customer Id"/>
            </div>
            <hr>

            <div class="form-group my-5">
                <label for="firstName" class="fw-bold">First Name</label>
                <input type="text" class="form-control"
                       name="firstName" id="firstName"/>
                <label for="lastName" class="fw-bold">Last Name</label>
                <input type="text" class="form-control"
                       name="lastName" id="lastName"/>
                <input class="btn btn-success mt-3" type="submit" name="reportType" value="Get Number Of Open Claims For Customer Name"/>
            </div>
            <hr>
            <div class="form-group my-5">
                <label for="stateLanguage" class="fw-bold">State (Enter abbreviation ex. WI, IL ect.)</label>
                <input type="text" class="form-control"
                       name="stateLanguage" id="stateLanguage"/>
                <input class="btn btn-success mt-3" type="submit" name="reportType" value="Get Most Spoken Language For State"/>
            </div>
            <hr>
            <div class="form-group my-5">
                <label for="stateClaims" class="fw-bold">State (Enter abbreviation ex. WI, IL ect.)</label>
                <input type="text" class="form-control"
                       name="stateClaims" id="stateClaims"/>
                <input class="btn btn-success mt-3" type="submit" name="reportType" value="Get Number Of Open Claims For State"/>
            </div>
        </form>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
