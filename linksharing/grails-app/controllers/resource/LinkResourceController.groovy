package resource

class LinkResourceController {

    def index() { }

    def save(){
        LinkResource linkResource = new LinkResource(createdBy: session.user, description: params.linkTopicDescription, topic: params.topic , url: params.link)
        if (linkResource.save())
            flash.message="LINK RESOURCE SAVED"
        else
            flash.message="ERROR"

        forward(controller:'user', action: 'index')
    }
}
