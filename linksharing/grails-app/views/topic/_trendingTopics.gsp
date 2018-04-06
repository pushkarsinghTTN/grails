<%@ page import="resource.Resource" %>
<div class="panel panel-primary ">
    <div class="panel-heading">
        <h3 class="panel-title">
            Trending topics
            <a href="#" class="pull-right">View All</a>
        </h3>
    </div>

    <g:each in="${topic.Topic.getTrendingTopics()}" var="trendingTopics">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-3 fa fa-user fa-5x">
                        </div>

                        <div class="col-sm-9">
                            <a href="#" class="pull-left">${trendingTopics.name}</a>
                            <br>

                            <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-muted">@${trendingTopics.createdBy.username}</h6>
                                    <% subscription.Subscription subscription = subscription.Subscription.findByUserAndTopic(session.user, trendingTopics) %>
                                    <g:if test="${session.user}">
                                        <g:if test="${subscription && trendingTopics.createdBy != session.user}">
                                            <g:link controller="subscription" action="delete"
                                                    params="${[topicId: trendingTopics.id]}">Unsubscribe</g:link>
                                        </g:if>
                                        <g:elseif test="${subscription && trendingTopics.createdBy == session.user}">
                                        </g:elseif>
                                        <g:else>
                                            <g:link controller="subscription" action="save"
                                                    params="${[topicId: trendingTopics.id]}">Subscribe</g:link>
                                        </g:else>
                                    </g:if>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted pull-left">Subscriptions</h6>
                                    <h6 class="text-primary">${trendingTopics.subscriptions.size()}</h6>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted  pull-right">Post
                                        <br>
                                        <br>

                                        <p class="text-primary">${trendingTopics.resources.size()}</p>
                                    </h6>
                                </div>
                                <g:if test="${session.user}">
                                    <g:if test="${trendingTopics?.createdBy == session.user || session.user?.admin}">
                                        <span type="img" class="glyphicon glyphicon-trash pull-right fa-2x"
                                              style="margin-left: 10px;color: #007efc;"></span>
                                        <span type="img" class="fa fa-file pull-right fa-2x"
                                              style="margin-left: 10px;  margin-right: 5px;color: #007efc;"></span>
                                    </g:if>
                                    <span type="img" class="fa fa-envelope pull-right fa-2x"
                                          style="margin-left: 10px;color: #007efc;"></span>

                                    <select class="pull-right">
                                        <option value="${enumeration.Seriousness.VERYSERIOUS}">Very Serious</option>
                                        <option value="${enumeration.Seriousness.SERIOUS}">Serious</option>
                                        <option value="${enumeration.Seriousness.CASUAL}">Casual</option>
                                    </select>

                                    <g:if test="${trendingTopics.createdBy == session.user || session.user?.admin}">
                                        <div>
                                            <select class="pull-right">
                                                <option value="${enumeration.Visibility.PRIVATE}">Private</option>
                                                <option value="${enumeration.Visibility.PUBLIC}">Public</option>
                                            </select>
                                        </div>
                                    </g:if>
                                </g:if>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <div class="col-lg-12">
            <hr>
        </div>
    </g:each>
</div>