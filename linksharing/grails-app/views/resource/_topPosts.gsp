<div class=" panel panel-default     ">
    <div class="panel-heading  col-lg-12" style="background: #007efc">
        <h3 style="color:white " class="col-lg-8">Top Posts</h3>


        <select name="time" class=" col-lg-3 " style=" margin-top: 25px">
            <option value="today">Today</option>
            <option value="1 week">1 Week</option>
            <option value="1 month">1 Month</option>
            <option value="1 year">1 Year</option>
        </select>

    </div>

    <div class="panel-body  ">
        <g:each in="${resource.Resource.getTopPost()}" var="topPosts">
            <div class="col-lg-12">
                <div class="col-lg-3" style="margin-top: 25px">
                    <i class="fa fa-user fa-5x" aria-hidden="true"></i>

                </div>

                <div class="col-lg-9">

                    <p>${topPosts.createdBy.getName()}<span
                            style="color: darkgray">@${topPosts.createdBy.username}</span><span
                            class="pull-right"
                            style="margin-right: 0px;font-size: small">${topPosts.description}</span>
                    </p>


                    <i class="fa fa-facebook-official fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-google fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-twitter fa-lg" aria-hidden="true"></i>
                    <span class="pull-right" style="margin-right: 0px;color: #007efc">
                        <g:link controller="resource" action="show"
                                style="color: #007efc;font-size: small">View Post</g:link>
                        <g:if test="${session.user}">
                        %{--<g:link controller="readingItem" action="changeisRead" id="${}--}%
                        %{--style="color: #007efc;font-size: small">Mark Read</g:link>--}%
                            <g:link action="/topic/show.gsp" params="${[topic: topPosts.topic]}"
                                    style="color: #007efc;font-size: small">View Topic</g:link>
                            <g:link controller="download" action="index"
                                    style="color: #007efc;font-size: small">Download</g:link>
                        </g:if>
                    </span>

                </div>
            </div>
        </g:each>
    </div>
</div>