<%@ page import="co.SearchCO" %>
<div class="col-lg-6">
    <div class="panel panel-primary ">
        <div class="panel-heading">Inbox</div>
        <g:each in="${session.user.getUnReadResources()}" var="unReadResources">
            <div class="panel-body">
                <div class="col-lg-12">
                    <div class="col-lg-3" style="margin-top: 25px">
                        <i class="fa fa-user fa-5x" aria-hidden="true"></i>

                    </div>

                    <div class="col-lg-9">
                        <p>${unReadResources.user.getName()}  <span
                                style="color: darkgray">@${unReadResources.user.username}</span>
                            <span class="pull-right"
                                  style="margin-right: 0px;color: #007efc;font-size: small">${unReadResources.resource.topic.name}</span>
                        </p>

                        <p>${unReadResources.resource.description}</p>

                        <i class="fa fa-facebook-official fa-lg" aria-hidden="true"></i>
                        <i class="fa fa-google fa-lg" aria-hidden="true"></i>
                        <i class="fa fa-twitter fa-lg" aria-hidden="true"></i>
                        <span class="pull-right" style="margin-right: 0px;color: #007efc">
                            <g:link controller="download" action="index"
                                    style="color: #007efc;font-size: small">Download</g:link>
                            <g:link action="/topic/show.gsp" params="${[topicId:unReadResources.resource.topic.id]}"
                                    style="color: #007efc;font-size: small">View Full</g:link>
                            <g:link controller="readingItem" action="changeisRead" params="${[id:unReadResources.id]}">Mark Read</g:link>
                            <g:link controller="resource" action="show"
                                    style="color: #007efc;font-size: small">View Post</g:link>
                        </span>

                    </div>
                </div>
            </div>
        </g:each>
    </div>

</div>