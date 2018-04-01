<!DOCTYPE html>
<html lang="en">
<head>

    %{--<meta charset="UTF-8">--}%
    %{--<meta name="viewport" content="width=device-width, initial-scale=1">--}%

    %{--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--}%
    %{--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--}%

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:stylesheet src="bootstrap.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >

<g:layoutHead/>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar  ">

    <a class="navbar-brand col-lg-2 " href="#"><h2 style="margin-top: 0px">Link Sharing</h2></a>

    <p class="col-lg-7"></p>

    <g:form class="navbar-form navbar-left" action="/action_page.php">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search...">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
        </div>
    </g:form>
</nav>

<g:if test="${flash.error}">
    <g:message message="${flash.error}"></g:message>

</g:if>
<g:if test="${flash.message}">
    <g:message message="${flash.message}"></g:message>

</g:if>
%{--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--}%

<g:layoutBody/>

</body>

</html>
