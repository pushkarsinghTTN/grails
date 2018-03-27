package ReadingItem

import Resource.Resource
import User.User

class ReadingItem {
    User user
    Boolean isRead
    Resource resource
    Date lastUpdated
    Date dateCreated

    ReadingItem(User user, Boolean isRead, Resource resource) {
        this.user = user
        this.isRead = isRead
        this.resource = resource
    }
    static belongsTo = [user:User, resource:Resource]

    static constraints = {
        user(nullable: false)
        isRead(nullable: false)
        resource(nullable: false, unique: 'user')
    }
}
