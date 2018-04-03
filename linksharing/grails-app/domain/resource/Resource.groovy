package resource

import co.ResourceSearchCo
import readingItem.ReadingItem
import resourceRating.ResourceRating
import topic.Topic
import user.User
import vo.RatingInfoVO
import vo.ResourceVO

abstract class Resource {

    User createdBy
    String description
    Topic topic
    Date lastUpdated
    Date dateCreated

    RatingInfoVO ratingInfo;

    void setRatingInfo() {
        this.ratingInfo = getResourceRatingInformation()
    }

    static transients = ['ratingInfo']

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings: ResourceRating, readingItems: ReadingItem]

    static constraints = {
        description(type: 'text')
    }

    static namedQueries = {
        search { ResourceSearchCo resourceSearchCo ->
            if (resourceSearchCo.topicId)
                eq('topic.id', resourceSearchCo.topicId)
            if (resourceSearchCo.visibility)
                eq('topic.visibility', resourceSearchCo.visibility)
        }
    }

    Integer totalVotes() {
        Integer totalVotes = ResourceRating.createCriteria().count() {
            eq('resource', this)
        }
        return totalVotes
    }

    Integer totalScore() {
        Integer totalScore = ResourceRating.createCriteria().get() {
            projections {
                sum('score')
            }
            eq('resource', this)
        }
        return totalScore
    }

    Double averageScore() {
        Double averageScore = ResourceRating.createCriteria().get() {
            projections {
                avg('score')
            }
            eq('resource', this)
        }
        return averageScore
    }

    RatingInfoVO getResourceRatingInformation() {
        RatingInfoVO ratingInfoVO = new RatingInfoVO()
        ratingInfoVO.totalVotes = totalVotes()
        ratingInfoVO.totalScore = totalScore()
        ratingInfoVO.averageScore = averageScore()
        return ratingInfoVO
    }

    static List<ResourceVO> getTopPost(){
        //todo correct query
        List<ResourceVO> topPosts = ResourceRating.createCriteria().list{
            projections{
                createAlias('resource', 'r')
                groupProperty('r.id')
                property('r.createdBy')
                property('r.topic')
                count('r.id', 'count')
            }
            order('count', 'desc')
            maxResults(5)
        }
        List result = []
        topPosts.each{
            result.add(new ResourceVO(id: it[0],createdBy: it[1],topic: it[2],count: it[3]))
        }
        println("Returning top posts : " + result)
        return result
    }

    static List<Resource> getRecentShares(){

        List<Resource> recentShares = Resource.createCriteria().list {
            order("dateCreated", "desc")
            maxResults(2)

        }
        println("about to return")
        return recentShares
    }

    static findLinkOrDocument(Resource resource){

    }
}
