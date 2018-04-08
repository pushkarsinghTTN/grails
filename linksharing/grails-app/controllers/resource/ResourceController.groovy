package resource

import co.ResourceSearchCo
import co.TrendingTopicsCO
import enumeration.Visibility
import resourceRating.ResourceRating
import topic.Topic
import vo.RatingInfoVO
import vo.ResourceVO
import vo.TopicVO
import vo.TrendingTopicsVO

class ResourceController {

    ResourceService resourceService

    def index() {}

    def delete() {
        if (resourceService.deleteResource(new Integer(params.resourceId))) {
            flash.message = "Deleted Successfully"
        } else {
            flash.error = "Unable To Delete"
        }

    }

    def search(ResourceSearchCo resourceSearchCo) {
        if (resourceSearchCo.q) {
            resourceSearchCo.visibility = Visibility.PUBLIC
        }
    }

    def showScore(Integer resourceId) {
        Resource resource = Resource.findById(resourceId)
        RatingInfoVO ratingInfoVO = resource.getResourceRatingInformation()
        render("TOTAL VOTES- $ratingInfoVO.totalVotes + TOTAL SCORE- $ratingInfoVO.totalScore + AVERAGE SCORE- $ratingInfoVO.averageScore")
    }


    def showPost() {
        Long resourceId = new Long(params.id)
        ResourceVO resource = resourceService.showResourcePage(resourceId)
        List<TrendingTopicsVO> trendingTopicsList = []
        if (resource) {
            if (session.user) {
                trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: session.user))
            } else {
                trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: null))
            }
            render(view: 'show', model: [resource: resource, trendingTopicsList: trendingTopicsList])
        } else
            render("RESOURCE NOT FOUND")
    }

    def storeRating() {
        Map map = [score: params.star,ratedBy: session.user, resourceId: params.id]
        if (resourceService.saveRating(map)) {
            flash.message = "Saved Succesfully"
        } else {
            flash.eror = "Rating Not Saved"
        }
        redirect(controller: 'resource', action: 'showPost', model: [params])
    }

    def changeDesciption(){
        if(resourceService.changeResourceDescription(params)){
            flash.message= "Description Changed Successfully"
        }else{
            flash.error = "Unable To Resource Description"
        }
        redirect(controller: 'user', action: 'editProfile')
    }

}
