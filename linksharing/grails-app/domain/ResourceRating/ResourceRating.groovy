package ResourceRating

import Resource.Resource
import User.User

class ResourceRating {
    //Resource resource
    User createdby
    Integer score
    Date lastUpdated
    Date dateCreated

    ResourceRating(Resource resource, User createdby, Integer score) {
        this.resource = resource
        this.createdby = createdby
        this.score = score
    }
    static belongsTo = [resource: Resource]

    static constraints = {
        resource(nullable: false)
        score(nullable: false, min: 0, max: 5)
        createdby(nullable: false, unique: true)

    }
}

