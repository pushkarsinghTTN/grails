package Topic

import Resource.Resource
import Subscription.Subscription
import User.User
import enumeration.Visibility

class Topic {
    String name
    Visibility visibility
    Date lastUpdated
    Date dateCreated

    Topic(String name, Visibility visibility, User createdby) {
        this.name = name
        this.visibility = visibility
        this.createdby = createdby
    }

    static belongsTo = [createdby:User]
    static hasMany = [subscriptions:Subscription, resources:Resource]

    static constraints = {
        name(unique: 'createdby', blank: false, nullable: false,)
        visibility(nullable: false)
        createdby(nullable: false)
    }


}

