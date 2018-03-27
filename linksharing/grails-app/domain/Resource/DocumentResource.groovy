package Resource

class DocumentResource extends Resource {
    String filepath

    static constraints = {
        filepath(unique: true)
    }
}
