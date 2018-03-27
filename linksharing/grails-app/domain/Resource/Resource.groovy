package Resource

import ReadingItem.ReadingItem
import ResourceRating.ResourceRating
import Topic.Topic
import User.User

abstract class Resource {

    User createdby
    String description
    Topic topic
    Date lastUpdated
    Date dateCreated

    static belongsTo = [createdby:User, topic:Topic]
    static hasMany = [ratings:ResourceRating, readingItems:ReadingItem]

    static constraints = {
        description(type:'text')
    }
}
