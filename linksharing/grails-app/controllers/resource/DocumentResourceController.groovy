package resource

class DocumentResourceController {

    def index() { }

    def save(){
        DocumentResource documentResource = new DocumentResource(createdBy: session.user, description: params.description, topic: params.topic)
        if (documentResource.save())
            flash.message="DOCUMENT RESOURCE SAVED"
        else
            flash.message="ERROR"

        forward(controller:'user', action: 'index')
    }
}