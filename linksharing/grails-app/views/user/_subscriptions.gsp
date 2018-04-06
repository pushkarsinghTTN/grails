<div class="panel panel-primary ">
    <div class="panel-heading"><h3 class="panel-title">
        Subscriptions
        <a href="#" class="pull-right">View All</a>
    </h3></div>

    <g:if test="${session.user}">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <g:each in="${session.user.getSubscribedTopic()}" var="subscribedTopics">
                        <div class="row">
                            <div class="col-sm-3 fa fa-user fa-5x">
                            </div>

                            <div class="col-sm-9">
                                <g:link controller="topic" action="show" class="pull-left">${subscribedTopics.name}</g:link>
                                <br>

                                <div class="row">
                                    <div class="col-sm-6">
                                        <h6 class="text-muted">@${subscribedTopics.createdBy.username}</h6>
                                        <g:link controller="subscription" action="delete" params="${[topicId: subscribedTopics.id]}">Unsubscribe</g:link>
                                    </div>

                                    <div class="col-sm-3">
                                        <h6 class="text-muted pull-left">Subscriptions</h6>
                                        <h6 class="text-primary">${subscribedTopics.getSubscriptionCount()}</h6>
                                    </div>

                                    <div class="col-sm-3">
                                        <h6 class="text-muted  pull-right">Post
                                            <br>
                                            <br>

                                            <p class="text-primary">${subscribedTopics.resources.size()}</p>
                                        </h6>
                                    </div>
                                    <span type="img" class="glyphicon glyphicon-trash pull-right fa-2x"
                                          style="margin-left: 10px;color: #007efc;"></span>
                                    <span type="img" class="fa fa-file pull-right fa-2x"
                                          style="margin-left: 10px;  margin-right: 5px;color: #007efc;"></span>
                                    <span type="img" class="fa fa-envelope pull-right fa-2x"
                                          style="margin-left: 10px;color: #007efc;"></span>

                                    <select class="pull-right">
                                        <option>Serious</option>
                                        <option>Casual</option>
                                        <option>Very Serious</option>
                                    </select>

                                    <div>
                                        <select class="pull-right">
                                            <option>Private</option>
                                            <option>Public</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </g:if>
    <g:else>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-3 fa fa-user fa-5x">
                        </div>

                        <div class="col-sm-9">
                            <a href="#" class="pull-left">Grails</a>
                            <br>

                            <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-muted">@pushkar.singh</h6>
                                    <a href="#">Unsubscribe</a>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted pull-left">Subscriptions</h6>
                                    <h6 class="text-primary">50</h6>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted  pull-right">Post
                                        <br>
                                        <br>

                                        <p class="text-primary">30</p>
                                    </h6>
                                </div>
                                <span type="img" class="fa fa-envelope pull-right fa-2x"
                                      style="margin-left: 10px;color: #007efc;"></span>

                                <select class="pull-right">
                                    <option>Serious</option>
                                    <option>Casual</option>
                                    <option>Very Serious</option>
                                </select>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:else>
</div>