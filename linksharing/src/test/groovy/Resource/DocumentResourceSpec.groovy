package Resource

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DocumentResourceSpec extends Specification implements DomainUnitTest<DocumentResource> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }

    void "check unique source"(){
        String filepath="/abc/def"
        setup:
        DocumentResource documentResource = new DocumentResource()
        documentResource.filepath=filepath

        when:
        documentResource.save()

        then:
        Resource.findAll(filepath).size()==1

        when:
        DocumentResource newdocumentResource = new DocumentResource()
        newdocumentResource.filepath=filepath
        newdocumentResource.save()

        then:
        Resource.findAll(filepath).size()==1
    }
}
