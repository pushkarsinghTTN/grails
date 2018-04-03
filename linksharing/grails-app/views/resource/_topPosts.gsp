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
                            style="margin-right: 0px;color: #007efc;font-size: small">${topPosts.topic.name}</span>
                    </p>

                    <p>${topPosts.topic.name}</p>


                    <i class="fa fa-facebook-official fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-google fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-twitter fa-lg" aria-hidden="true"></i>
                    <span class="pull-right" style="margin-right: 0px;color: #007efc"><a href="#"
                                                                                         style="color: #007efc;font-size: small">View Topic</a>
                    </span>

                </div>
            </div>
        </g:each>
    </div>
</div>