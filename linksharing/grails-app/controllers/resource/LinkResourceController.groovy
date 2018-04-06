package resource

import topic.Topic

class LinkResourceController {

    def index() {}

    def save() {
        Resource linkResource = new LinkResource(createdBy: session.user, description: params.linkTopicDescription, topic: Topic.findByName(params.topicName), url: params.link)
        if (linkResource.save(flush: true)) {
            log.info("Saved Successfully : $linkResource")
            flash.message = "LINK RESOURCE SAVED"
        } else {
            log.error("Error while saving : $linkResource")
            linkResource.errors.allErrors.each{println(it)}
            flash.message = "ERROR"
        }
        redirect(controller: 'user', action: 'index')
    }
}
