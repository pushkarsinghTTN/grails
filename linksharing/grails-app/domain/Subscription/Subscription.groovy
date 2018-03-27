package Subscription

import Topic.Topic
import User.User
import enumeration.Seriousness

class Subscription {
    User user
    Topic topic
    Seriousness seriousness
    Date lastUpdated
    Date dateCreated

    //static belongsTo = [user:User,topic:Topic]

    static constraints = {
        user(nullable: false)
        topic(nullable: false)
        seriousness(nullable: false)

    }
}

