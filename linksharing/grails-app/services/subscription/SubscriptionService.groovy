package subscription

import enumeration.Seriousness
import grails.gorm.transactions.Transactional
import topic.Topic

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    def saveSubscription(Map subscriptionData) {

        Topic topic = Topic.findById(subscriptionData.topicId)
        Subscription subscription = new Subscription(user: subscriptionData.subscriber, topic: topic, seriousness: Seriousness.SERIOUS)
        if (subscription.save(flush: true)) {
            log.info("Saved Successfully : $subscription")
//            session.user.addToSubscriptions(subscription)
            topic.addToSubscriptions(subscription)
            topic.save(flush: true)
            return subscription
        } else {
            log.error("Error while saving- $subscription")
            subscription.errors.allErrors.each { println it }
            return null
        }
    }

    def deleteSubscription(Map params){
        Subscription subscription = Subscription.findById(params.subscriptionId)
        if (!subscription) {
            return false
        } else {
            subscription.discard()
            subscription.delete(flush: true)
            return true
        }
    }
}
