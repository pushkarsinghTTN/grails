package Resource

class LinkResource extends Resource{
    String url

    static constraints = {
        url(unique: true)
    }
}
