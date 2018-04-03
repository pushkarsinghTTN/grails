<div id="myModal4" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share document</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" controller="DocumentResource" action="save">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Document</label>

                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="document">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2">Description:</label>

                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5"
                                      name="documentResourceDescription"></textarea>
                        </div>
                    </div>

                    <div class="dropdown">
                        <label class="control-label col-sm-2">Topic:</label>

                        <button class="btn btn-default dropdown-toggle" type="button"
                                data-toggle="dropdown" name="documentResourceTopic">Topic
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">topictype1</a></li>
                            <li><a href="#">topictype2</a></li>
                            <li><a href="#">topictype3</a></li>

                        </ul>
                    </div>

                    <div class="form-group">
                        <br>

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