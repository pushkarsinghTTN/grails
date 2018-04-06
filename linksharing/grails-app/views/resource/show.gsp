<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">

</head>

<body>
<div class="container">
    <div class="col-lg-7">

        <div class="col-lg-12">
            <div class="col-lg-12">
                <div class=" panel panel-default     " >

                    <div class="panel-body  ">
                        <div class="col-lg-12">
                            <div class="col-lg-3" style="margin-top: 25px">
                                <i class="fa fa-user-circle fa-5x" aria-hidden="true"></i>

                            </div>

                            <div class="col-lg-9">
                                <div class="col-sm-12">
                                    <br>

                                    <div class="col-lg-5">
                                        <span>${resource.createdBy.getName()} <small
                                                class="text-muted">@${resource.createdBy.username}</small>
                                        </span>

                                    </div>

                                    <div class="col-lg-7" style="text-align: right">
                                        <p><a href="${createLink(controller: "topic", action: "show", id: resource.topic.id)}" class="pull-right">${resource.topic.name}</a></p><br>

                                        %{--<p>2:45PM, 22FEB 2014</p>--}%
                                        <i class="fa fa-heart" style="font-size:20px;"></i>
                                        <i class="fa fa-heart" style="font-size:20px;"></i>
                                        <i class="fa fa-heart" style="font-size:20px;"></i>
                                        <i class="fa fa-heart-o" style="font-size:20px;"></i>
                                        <i class="fa fa-heart-o" style="font-size:20px;"></i>

                                    </div>
                                    <div class="col-lg-12"> <br><br></div>





                                    <div class="col-lg-12">
                                        <div class="col-lg-12">
                                            <p>${resource.description}</p>
                                        </div>
                                    </div>
                                    <div class="col-lg-12"> <br></div>






                                </div>

                            </div>
                            <div class="col-lg-12" style="text-align: ">
                                <i class="fa fa-facebook-square fa-lg" aria-hidden="true"></i>
                                <i class="fa fa-google-plus fa-lg" aria-hidden="true"></i>
                                <i class="fa fa-twitter-square fa-lg" aria-hidden="true"></i>
                                <span class="pull-right" style="margin-right: 0px;color: #007efc">
                                    <g:if test="${session.user}">
                                    <a href="#" style="color: #007efc;font-size: 90%">Download</a>
                                    </g:if>
                                </span>
                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </div>

    </div>

    <div class="col-lg-5">
        <g:render template="/topic/trendingTopics"></g:render>
    </div>
</div>

</body>
</html>