package resource

class ResourceController {

    def index() { }

    def delete(Integer resourceid) {
        Resource proxyresource = Resource.load(resourceid)
        proxyresource.discard()
        proxyresource.delete(flush:true)
        render("Deleted Successfully")
    }
}
