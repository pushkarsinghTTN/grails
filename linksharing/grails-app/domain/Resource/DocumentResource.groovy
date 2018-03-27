package Resource

import Topic.Topic
import User.User

class DocumentResource extends Resource {
    String filepath

    DocumentResource(User createdby, String description, Topic topic, String filepath) {
        super(createdby, description, topic)
        this.filepath = filepath
    }

    static constraints = {
        filepath(unique: true)
    }
}
