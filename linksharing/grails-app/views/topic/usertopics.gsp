<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">

</head>

<body>
<div class="container">
    <div class="col-lg-6">
        <div class="panel panel-primary ">
            <div class="panel-heading">
                <h3 class="panel-title">
                    Your Topics
                </h3>
            </div>

            <g:if test="${session.user.getSubscriptions()}">
            <g:each in="${session.user.getSubscriptions()}" var="userTopics">
                <g:render template="/topic/topicBriefInfo"></g:render>
            </g:each>
            </g:if>
            <g:else>
                You Are Not Subscribed To Any Topic
            </g:else>
        </div>
    </div>
</div>
</body>
</html>