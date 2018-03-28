package resource

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LinkResourceSpec extends Specification implements DomainUnitTest<LinkResource> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }

    void "check unique link"(){
        String url="abc.com"
        setup:
        LinkResource linkResource = new LinkResource()
        linkResource.url=url

        when:
        linkResource.save()

        then:
        Resource.findAll(url).size()==1

        when:
        LinkResource newlinkResource = new LinkResource()
        newlinkResource.url=url
        newlinkResource.save()

        then:
        Resource.findAll(url).size()==1
    }
}
