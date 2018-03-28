package user

import readingItem.ReadingItem
import resource.Resource
import subscription.Subscription
import topic.Topic

class User {
    String firstname
    String lastname
    String username
    String password
    String email
    Byte photo
    Boolean admin
    Boolean active
    String name
    Date lastUpdated
    Date dateCreated

    String getName() {
        this.name = this.firstname + " " + this.lastname
        return name
    }
    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    static constraints = {
        email(email: true, nullable: false, blank: false)
        password(nullable: false, blank: false, size: 5..15)
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
        username(nullable: false, blank: false)
        photo(nullable: true, sqlType: 'longBlob')
        admin(nullable: true)
        active(nullable: true)
        //readingItems(default: [])
    }

    static transients = ['name']


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
