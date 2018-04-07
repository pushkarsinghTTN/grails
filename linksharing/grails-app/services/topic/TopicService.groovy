package topic

import grails.gorm.transactions.Transactional
import resource.Resource
import subscription.Subscription
import user.User
import vo.ResourceVO
import vo.TopicVO
import vo.UserVO

@Transactional
class TopicService {

    def serviceMethod() {

    }

    def getCompleteTopicInfo(Long topicId) {
        Topic topic = Topic.findById(topicId)
        if (topic) {
            TopicVO topicVO = new TopicVO(topicId: topicId, topicName: topic.name, topicVisibility: topic.visibility,
                    ownerName: topic.createdBy.getName(), ownerUsername: topic.createdBy.username,
                    subscriptionCount: topic.subscriptions.size(), resourcesCount: topic.resources.size())

            List<Subscription> subscribedUsers = Subscription.findAllByTopic(topic)

            List<UserVO> subscribedUserList = []
            subscribedUsers.each {
                subscribedUserList.add(new UserVO(name: it.user.getName(), username: it.user.username, subscriptionCount: it.user.subscriptions.size(),
                        resourceCount: it.user.resources.size(),topicCount: it.user.topics.size()))
            }

            List<Resource> resources = Resource.findAllByTopic(topic)

            List<ResourceVO> resourceList = []
            resources.each {
                resourceList.add(new ResourceVO(resourceId: it.id, topicId: topicId, resourceDescription: it.description,
                        ownerName: it.createdBy.getName(), ownerUsername: it.createdBy.username, topicName: it.topic.name))
            }
            def map = ["topicVO": topicVO, "subscribedUserList": subscribedUserList, "resourceList": resourceList]
            return map
        } else
            return null
    }
}
