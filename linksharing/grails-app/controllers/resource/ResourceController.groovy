package resource

import co.ResourceSearchCo
import enumeration.Visibility
import topic.Topic
import vo.RatingInfoVO
import vo.TopicVO

class ResourceController {

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
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        render("TOTAL VOTES- $ratingInfoVO.totalVotes + TOTAL SCORE- $ratingInfoVO.totalScore + AVERAGE SCORE- $ratingInfoVO.averageScore")
        render("TRENDING TOPICS-" +
                trendingTopics.each {println("$it.name + $it.visibility + $it.createdBy")})
    }
}
