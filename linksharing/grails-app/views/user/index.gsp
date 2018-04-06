<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">

</head>

<body>

<div class="container">
    <div class="col-lg-6">
        <div class="panel panel-primary col-lg-12">
            <div class="panel-body">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-3 fa fa-user fa-5x">
                        </div>

                        <div class="col-sm-9">

                            <p>${session.user.getName()}<br>
                                <small class="text-muted">@${session.user.username}</small>
                            </p>

                            <div class="row">

                                <h6 class="text-muted col-sm-6">Subscriptions
                                    <p class="text-primary">${session.user.getSubscriptionCount()}</p>
                                </h6>

                                <h6 class="text-muted col-sm-6">Topics
                                    <p class="text-primary">${session.user.getTopicCount()}</p>
                                </h6>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <br><br><br><br>

        <div class="col-lg-12"><br></div>
        <br><br><br><br>

<g:render template="subscriptions"></g:render>

        <br>

        <div class="col-lg-12"><br></div>
        <br>

        <g:render template="/topic/trendingTopics"></g:render>

    </div>

    <g:render template="/user/inbox"></g:render>

</div>

</body>
</html>