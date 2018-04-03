<div id="myModal2" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" action="#">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Email</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="inviteEmail">
                        </div>
                    </div>

                    <div class="dropdown">
                        <label class="control-label col-sm-2">Topic:</label>

                        <button class="btn btn-default dropdown-toggle" type="button"
                                data-toggle="dropdown" name="topicInvitation">Topic
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Topic1</a></li>
                            <li><a href="#">Topic2</a></li>

                        </ul>
                    </div>

                    <br>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Share</button>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Close</button>

                        </div>
                    </div>
                </g:form>

            </div>

        </div>

    </div>
</div>