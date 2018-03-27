package Resource

import Topic.Topic
import User.User

class LinkResource extends Resource{
    String url

    LinkResource(User createdby, String description, Topic topic, String url) {
        super(createdby, description, topic)
        this.url = url
    }
    static constraints = {
        url(unique: true)
    }
}
