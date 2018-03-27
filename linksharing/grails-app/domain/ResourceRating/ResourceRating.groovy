package ResourceRating

import Resource.Resource
import User.User

class ResourceRating {
    Resource resource
    User createdby
    Integer score
    Date lastUpdated
    Date dateCreated

    static belongsTo = [resource:Resource]

    static constraints = {
        resource(nullable: false)
        score(nullable: false, size: 1..5)
        createdby(nullable: false, unique: true)

    }
}

