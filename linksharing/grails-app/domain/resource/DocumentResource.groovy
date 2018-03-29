package resource

import topic.Topic
import user.User

class DocumentResource extends Resource {
    String filepath

    static constraints = {
        filepath(unique: true)
    }
}
