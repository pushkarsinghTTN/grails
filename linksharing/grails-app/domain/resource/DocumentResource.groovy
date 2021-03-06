package resource

import constants.Constants
import topic.Topic
import user.User

class DocumentResource extends Resource {
    String filepath
    String contentType
    String fileName

    static transients = ['contentType', 'fileName']

    static constraints = {
        filepath(unique: true,blank:false)
        contentType(matches: Constants.DOCUMENT_CONTENT_TYPE)
    }

    String toString() {
        "Document Resource filepath: ${filepath}"
    }

    String getFileName() {
        filepath.substring(filepath.lastIndexOf('/'))
    }

    def deleteResource() {
        String path = this.filepath
        Boolean status = new File(path).delete()
        if (status) {
            this.delete(flush: true)
            return true
        }
        return false
    }
}
