package Topic

import Resource.Resource
import Subscription.Subscription
import User.User
import enumeration.Seriousness
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

    def afterInsert(){
        Topic.withNewSession {
            this.subscriptions = []
            Subscription subscription = new Subscription(createdby, this, Seriousness.VERYSERIOUS)
            if (subscription.validate()) {
                this.subscriptions.add(subscription)
                subscription.save(flush:true)
            }
            log.info("Subscription has errors while validating- ${subscription.hasErrors()}")
        }
    }


    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}

