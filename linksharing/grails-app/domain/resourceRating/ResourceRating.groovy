package resourceRating

import resource.Resource
import user.User

class ResourceRating {
    Resource resource
    User createdby
    Integer score
    Date lastUpdated
    Date dateCreated

    static belongsTo = [resource: Resource]

    static constraints = {
        resource(nullable: false)
        score(nullable: false, min: 0, max: 5)
        createdby(nullable: false)
    }
}

