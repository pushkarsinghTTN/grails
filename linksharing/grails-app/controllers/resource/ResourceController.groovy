package resource

import co.ResourceSearchCo
import enumeration.Visibility
import resourceRating.ResourceRating
import topic.Topic
import vo.RatingInfoVO
import vo.TopicVO

class ResourceController {

    ResourceService resourceService

    def index() {}

    def delete(Integer resourceid) {
        Resource proxyresource = Resource.load(resourceid)
        if (!proxyresource) {
            throw new Exception("NO SUCH RECORD IN OUR DATABASE")
        } else {
            proxyresource.discard()
            proxyresource.delete(flush: true)
            render("Deleted Successfully")
        }
    }

    def search(ResourceSearchCo resourceSearchCo) {
        if (resourceSearchCo.q) {
            resourceSearchCo.visibility = Visibility.PUBLIC
        }
    }

    def show(Integer resourceId) {
        Resource resource = Resource.findById(resourceId)
        RatingInfoVO ratingInfoVO = resource.getResourceRatingInformation()
        render("TOTAL VOTES- $ratingInfoVO.totalVotes + TOTAL SCORE- $ratingInfoVO.totalScore + AVERAGE SCORE- $ratingInfoVO.averageScore")
    }

    def findTrendingTopics() {
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        render("TRENDING TOPICS-" +
                trendingTopics.each { println("$it.name + $it.visibility + $it.createdBy") })
    }

    def showPost() {
        Long resourceId = new Long(params.id)
        Resource resource = resourceService.showResourcePage(resourceId)
        if (resource)
            render(view: 'show', model: [resource: resource])
        else
            render("RESOURCE NOT FOUND")
    }

    def storeRating(){
        Integer score = new Integer(params.star)
        Resource resource = Resource.findById(params.resourceId)
        ResourceRating resourceRating = ResourceRating.findByCreatedByAndResource(session.user,resource)
        if(resourceRating){
            resourceRating.score=score
            if(resourceRating.save(flush:true))
                render("UPDATED SCORES")
            else
                render("SCORE UPDATION FAILED")
        }
        else {
            resourceRating = new ResourceRating(createdBy: session.user, resource: resource, score: score)
            if (resourceRating.save(flush: true))
                render("SUCCESS")
            else {
                log.error("Error while saving : $resourceRating")
                resourceRating.errors.allErrors.each {println(it)}
                render("FAILURE")
            }
        }
    }

}
