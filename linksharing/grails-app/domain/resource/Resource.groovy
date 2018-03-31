package resource

import co.ResourceSearchCo
import readingItem.ReadingItem
import resourceRating.ResourceRating
import topic.Topic
import user.User

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

    static namedQueries = {
        search{ResourceSearchCo resourceSearchCo->
            if(resourceSearchCo.topicId)
                eq('topic.id',resourceSearchCo.topicId)
        }
    }
}
