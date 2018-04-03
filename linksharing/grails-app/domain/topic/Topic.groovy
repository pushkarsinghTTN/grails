package topic

import resource.Resource
import subscription.Subscription
import user.User
import enumeration.Seriousness
import enumeration.Visibility

class Topic {
    String name
    Visibility visibility
    User createdBy
    Date lastUpdated
    Date dateCreated

    static belongsTo = [createdBy: User]
    static hasMany = [subscriptions: Subscription, resources: Resource]

    static constraints = {
        name(unique: 'createdBy', blank: false, nullable: false,)
        visibility(nullable: false)
        createdBy(nullable: false)
    }

    static mapping = {
        sort name: 'asc'
        subscriptions lazy: false
    }

    def afterInsert() {
        Topic.withNewSession {
            this.subscriptions = []
            Subscription subscription = new Subscription(user: createdBy, topic: this, seriousness: Seriousness.VERYSERIOUS)
            if (subscription.validate()) {
                this.subscriptions.add(subscription)
                subscription.save(flush: true)
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

    static getTrendingTopics() {
        List<Topic> trendingTopics = Resource.createCriteria().list() {
            projections {
                createAlias('topic', 't')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                count('t.id', 'count')
                property('t.createdBy')
            }
            eq('t.visibility', Visibility.PUBLIC)
            order('count', 'desc')
            order('t.name', 'asc')
            maxResults(5)
        }

        return trendingTopics
    }

    List<User> getSubscribedUsers(){
        List<User> subscribedUsers=this.subscriptions.user.toList()
        return subscribedUsers
    }
}