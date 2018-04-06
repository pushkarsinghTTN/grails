package topic

import resource.Resource
import subscription.Subscription
import user.User
import enumeration.Seriousness
import enumeration.Visibility
import vo.ResourceVO

class Topic {
    String name
    Visibility visibility
    User createdBy
    Date lastUpdated
    Date dateCreated

    static belongsTo = [createdBy: User]
    static hasMany = [subscriptions: Subscription, resources: Resource]

    static constraints = {
        name(unique: true, blank: false, nullable: false,)
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
            if (subscription.save(flush: true)) {
                log.info("Saved Successfully- $subscription")
//                this.addToSubscriptions(subscription)
//                this.createdBy.addToSubscriptions(subscription)
            } else
                log.error("Subscription has errors while validating- $subscription")
        }
    }


    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }


//    static getTrendingTopics() {
//        List<Topic> trendingTopics = Resource.createCriteria().list() {
//            projections {
//                createAlias('topic', 't')
//                groupProperty('t.id')
//                property('t.name')
//                property('t.visibility')
//                count('t.id', 'count')
//                property('t.createdBy')
//            }
//            eq('t.visibility', Visibility.PUBLIC)
//            order('count', 'desc')
//            order('t.name', 'asc')
//            maxResults(5)
//        }
//
//        List<Topic> result=[]
//        trendingTopics.each {result.add(new Topic(name: it[1],visibility: it[2],createdBy: it[4]))}
//
//        return result
//    }

    static getTrendingTopics() {
        List<Topic> trendingTopics = Resource.createCriteria().list() {
            projections {
                groupProperty('topic')
//                property('t.name')
//                property('t.visibility')
                count('topic.id', 'count')
                //property('t.createdBy')
            }
//            eq('t.visibility', Visibility.PUBLIC)
            order('count', 'desc')
            //order('topic.name', 'asc')
            maxResults(5)
        }

        List<Topic> result=[]
        trendingTopics.each {result.add(it[0])}

        return result
    }

    List<User> getSubscribedUsers() {
        List<User> subscribedUsers = this.subscriptions.user.toList()
        return subscribedUsers
    }

    Integer getSubscriptionCount() {
        if (subscriptions)
            return subscriptions.size()
        else
            return 0
    }
}