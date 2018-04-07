package resource

import grails.gorm.transactions.Transactional
import topic.Topic

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    def saveLinkResource(Map linkResourceData) {
        Resource linkResource = new LinkResource(createdBy: linkResourceData.createdBy, description: linkResourceData.linkTopicDescription, topic: Topic.findByName(linkResourceData.topicName), url: linkResourceData.link)
        if (linkResource.save(flush: true)) {
            log.info("Saved Successfully : $linkResource")
            return linkResourceData
        } else {
            log.error("Error While Saving : $linkResource")
            return null
        }

    }

    def saveDocumentResource(Map documentResourceData) {
        Resource documentResource = new DocumentResource(createdBy: documentResourceData.createdBy, description: documentResourceData.documentResourceDescription, topic: Topic.findByName(documentResourceData.topicName))
        if (documentResource.save(flush: true)) {
            log.info("Saved Successfully : $documentResource")
            return documentResource
        } else {
            log.error("Error While Saving : $documentResource")
            return null
        }
    }

    def showResourcePage(Long resourceId){
        Resource resource = Resource.findById(resourceId)
        if(resource)
            return resource
        else
            return null

    }


}
