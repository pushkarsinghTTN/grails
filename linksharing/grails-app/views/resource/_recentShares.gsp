<div class=" panel panel-default     ">
    <div class="panel-heading " style="background: #007efc">
        <p>

        <h3 style="color:white">Recent Shares</h3></p>
    </div>

    <div class="panel-body  ">
        <div class="col-lg-12">
            <g:each in="${resource.Resource.getRecentShares()}" var="recentShares">
                <div class="col-lg-3" style="margin-top: 25px">
                    <i class="fa fa-user fa-5x" aria-hidden="true"></i>

                </div>

                <div class="col-lg-9">

                    <p>${recentShares.createdBy.name}  <span
                            style="color: darkgray">@${recentShares.createdBy.username}</span><span
                            class="pull-right"
                            style="margin-right: 0px;color: #007efc;font-size: small"><a href="${createLink(controller: 'topic', action: 'show',id: recentShares.topic.id)}">${recentShares.topic.name}</a></span>
                    </p>

                    <p>${recentShares.description}</p>

                    <i class="fa fa-facebook-official fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-google fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-twitter fa-lg" aria-hidden="true"></i>
                    <span class="pull-right" style="margin-right: 0px;color: #007efc">
                        <a href="${createLink(controller:'resource',action:'show',id:recentShares.id)}">View Post</a>
                    </span>
                </div>
                <div class="col-lg-12">
                    <hr>
                </div>
            </g:each>
        </div>

    </div>
</div>