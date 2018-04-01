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
        List<ResourceVO> topPosts = ResourceRating.createCriteria().list(){
            projections{
                createAlias('resource', 'r')
                groupProperty('r.id')
                property('r.createdBy')
                property('r.topic.name')
                count('r.id', 'count')
            }
            order('count', 'desc')
            maxResults(5)
        }
        return topPosts
    }

    static List<Resource> getRecentShares(){

        List<Resource> recentShares = Resource.createCriteria().list {
            order("dateCreated", "desc")
            maxResults(2)

        }
        return recentShares
    }
}
