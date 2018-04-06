package resource

import topic.Topic

class DocumentResourceController {

    def index() { }

    def save(){
        Resource documentResource = new DocumentResource(createdBy: session.user, description: params.documentResourceDescription, topic: Topic.findByName(params.topicName))
        if (documentResource.save(flush: true)) {
            log.info("Saved Successfully : $documentResource")
            flash.message = "DOCUMENT RESOURCE SAVED"
        } else {
            log.error("Error while saving : $documentResource")
            flash.message = "ERROR"
        }
        redirect(controller: 'user', action: 'index')
    }
}