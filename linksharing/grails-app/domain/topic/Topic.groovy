package topic

import resource.Resource
import subscription.Subscription
import user.User
import enumeration.Seriousness
import enumeration.Visibility

class Topic {
    String name
    Visibility visibility
    User createdby
    Date lastUpdated
    Date dateCreated

    static belongsTo = [createdby:User]
    static hasMany = [subscriptions:Subscription, resources:Resource]

    static constraints = {
        name(unique: 'createdby', blank: false, nullable: false,)
        visibility(nullable: false)
        createdby(nullable: false)
    }

    static mapping = {sort name: 'asc'}

    def afterInsert(){
        Topic.withNewSession {
            this.subscriptions = []
            Subscription subscription = new Subscription(user: createdby,topic: this,seriousness: Seriousness.VERYSERIOUS)
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

