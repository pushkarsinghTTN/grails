package resource

class ResourceController {

    def index() {}

    def delete(Integer resourceid) {
        Resource proxyresource = Resource.load(resourceid)
        if (!proxyresource) {
            throw new Exception("NO SUCH RECORD IN OUR DATABASE")
        } else {
            proxyresource.discard()
            proxyresource.delete(flush: true)
            render("Deleted Successfully")
        }
    }

}
