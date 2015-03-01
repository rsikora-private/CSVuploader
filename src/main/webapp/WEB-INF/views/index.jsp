<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <link rel="stylesheet" type="text/css" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/bower_components/alertify.js/themes/alertify.core.css" />
        <link rel="stylesheet" href="resources/bower_components/alertify.js/themes/alertify.default.css" />
        <link rel="stylesheet" type="text/css" href="resources/styles/main.css">
    </head>
    <body>

        <div id="splash" class="splash">
            <span class="centered">working ...</span>
        </div>

        <div class="container">
            <h1>CSV Uploader</h1>

            <div class="jumbotron">
                <h2>Step 1</h2>
                <p>Choose csv file to import</p>

                <form method="POST" enctype="multipart/form-data">
                    <input type="file" name="file"/><br/>
                    <input id="btn-upload" class="btn btn-primary btn-lg" type="submit" value="Upload"/>
                </form>
            </div>
            <div class="jumbotron">
                <h2>Step 2</h2>
                <p>Take a look at preview</p>

                <script id="preview" type="text/x-handlebars-template">
                    <table class="table">
                        <thead>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>SSN</th>
                        <th>Date of Birth</th>
                        <th>E-mail</th>
                        </thead>
                        <tbody>
                        {{#workers}}
                        <tr>
                            <td>{{firstName}}</td>
                            <td>{{lastName}}</td>
                            <td>{{ssn}}</td>
                            <td>{{prettifyDate dob}}</td>
                            <td>{{email}}</td>
                        </tr>
                        {{/workers}}
                        </tbody>
                    </table>
                </script>

                <div id="preview-placeholder"></div>

            </div>
            <div class="jumbotron">
                <h2>Step 3</h2>
                <p>Store data in database</p>
                <button id="btn-import" class="btn btn-danger btn-lg" disabled>Import</button>
            </div>

        </div>

        <script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="resources/bower_components/handlebars/handlebars.min.js"></script>
        <script src="resources/bower_components/alertify.js/lib/alertify.min.js"></script>
        <script src="resources/scripts/app.js"></script>


    </body>
</html>
