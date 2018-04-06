package user

import co.SearchCO
import readingItem.ReadingItem
import resource.Resource
import subscription.Subscription
import topic.Topic

class User {
    String firstname
    String lastname
    String username
    String password
    String confirmpassword
    String email
    byte[] photo
    Boolean admin
    Boolean active
    String name
    Date lastUpdated
    Date dateCreated
    List<ReadingItem> readingItems = []


    String getName() {
        this.name = this.firstname + " " + this.lastname
        return name
    }

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    static constraints = {
        email(email: true, nullable: false, blank: false, unique: true)
        password(nullable: false, blank: false, size: 5..15, validator: { password, obj ->
            println "runnig password validation > " + obj.confirmpassword
            if (obj.confirmpassword) {
                println "inside validation"
                def password2 = obj.confirmpassword
                return (password == password2) ? true : ['invalid.matchingpasswords']
            }
        })
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
        username(nullable: false, blank: false)
        photo(nullable: true, sqlType: 'longBlob')
        admin(nullable: true)
        active(nullable: true)
    }

    static mapping = {
        sort id: 'desc'
        subscriptions lazy: false
    }

    static transients = ['name', 'confirmpassword']

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    List<ReadingItem> getUnReadResources(/*SearchCO searchCO*/) {

        List<ReadingItem> unReadItems = ReadingItem.createCriteria().list(max: 10, offset: 0) {
            eq('isRead', false)
            eq('user', this)
            /*if (searchCO.q) {
                ilike('resource.description', "%searchCO.q%")
            }*/
        }
        return unReadItems
    }

    List<Topic> getSubscribedTopic() {
        List<Topic> subscribedTopics = []
        if (this.subscriptions) {
            this.subscriptions.each {
                    subscribedTopics.add(it.topic)
            }
        }
        return subscribedTopics
    }

    Integer getSubscriptionCount() {
        if (this.subscriptions)
            return this.subscriptions.size()
        else
            return 0
    }

    Integer getTopicCount() {
        if (topics)
            return this.topics.size()
        else
            return 0
    }

    List<String> getUserTopics() {
        List<String> userTopics = []
        if (this.topics) {
            this.topics.each {
                userTopics.add(it.name)
            }
        }
        return userTopics
    }

}
