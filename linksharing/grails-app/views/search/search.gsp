<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>searchPage</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
<div class="container">
    <div class="col-lg-6">
        <div class="col-lg-12">
            <g:render template="/topic/trendingTopics"></g:render>
        </div>

        <div class="col-lg-12">
            <g:render template="/resource/topPosts"></g:render>

        </div>

    </div>

    <div class="col-lg-6">
        <div class="col-lg-12">
            <div class="col-lg-12">
                <div class=" panel panel-default     ">
                    <div class="panel-heading col-lg-12" style="background: #1b1e21;">
                        <div class="col-lg-5">
                            <h3 style="color:white">>Search For :"${searchObject}"</h3>
                        </div>

                        <div class="col-lg-7">
                            %{--empty--}%
                        </div>

                    </div>
                    <g:each in="${topicList}" var="topic">
                        <div class="panel-body  ">
                            <div class="col-lg-12">
                                <div class="col-lg-3" style="margin-top: 25px">
                                    <i class="fa fa-user-circle fa-5x" aria-hidden="true"></i>

                                </div>

                                <div class="col-lg-9">
                                    <div class="col-sm-12">
                                        <br>

                                        <span>${topic.createdBy.getName()} &nbsp;&nbsp;&nbsp;&nbsp;<small
                                                class="text-muted">@${topic.createdBy.username}</small>

                                            <a href="${createLink(controller:'topic',action: 'show',id: topic.id)}" class="pull-right">${topic.name}</a>
                                            <br><br>

                                            %{--<div class="col-lg-12">--}%
                                                %{--<p>akjbcadbcajncanclkaccksdm csdlndcskdncpsdmdc sdlcnklsdclkddnc sdlcnsdmcpsdpmc esdlc ldnclksdncsld sdlcnsdcklsdcklsdnc scsdncsclsd csldnfklfc</p>--}%
                                            %{--</div>--}%

                                        </span>

                                        <div>
                                            <i class="fa fa-facebook-square fa-lg" aria-hidden="true"></i>
                                            <i class="fa fa-google-plus fa-lg" aria-hidden="true"></i>
                                            <i class="fa fa-twitter-square fa-lg" aria-hidden="true"></i>
                                            <span class="pull-right" style="margin-right: 0px;color: #007efc">
                                                <a href="#" style="color: #007efc;font-size: 75%">Download</a>
                                                <a href="${createLink(controller:'topic',action: 'show',id: topic.id)}" style="color: #007efc;font-size: 75%">View Topic</a>
                                            </span>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </g:each>
                </div>

            </div>

        </div>
    </div>

</div>

</body>
</html>