package resource

import topic.Topic

class DocumentResourceController {

    ResourceService resourceService

    def index() { }

    def save(){
        params.createdBy=session.user

        if(resourceService.saveDocumentResource(params)){
            flash.message = "DOCUMENT RESOURCE SAVED"
        }
        else{
            flash.message = "ERROR"
        }
        redirect(controller: 'user', action: 'index')
    }

    def list() {
        params.max = 10
        [documentInstanceList: DocumentResource.list(params), documentInstanceTotal: Document.count()]
    }
}