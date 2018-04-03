package readingItem

import resource.Resource
import user.User

class ReadingItem {
    User user
    Boolean isRead
    Resource resource
    Date lastUpdated
    Date dateCreated

    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        user(nullable: false)
        isRead(nullable: false)
        resource(nullable: false, unique: 'user')
    }

    static updateIsRead(Boolean isRead, Long id) {
        try {
            executeUpdate("UPDATE ReadingItem set isRead=:isRead1 where id=:id1", [isRead1 = isRead, id1 = id])
            return true
        }
        catch (Exception exp) {
            log.error("Error while updating: " + exp.message)
            return false
        }
    }
}
