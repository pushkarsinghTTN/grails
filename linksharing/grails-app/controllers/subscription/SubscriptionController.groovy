package subscription

import topic.Topic

class SubscriptionController {

    def index() {}

    def save(Integer topicId){
        Topic topic=Topic.findById(topicId)
        Subscription subscription = new Subscription(createdby: session.user,topic: topic)
        if (!subscription.save(flush: true)) {
            log.error("Error while saving- $subscription")
            render("SUBSCRIPTION NOT DONE")
        }else {
            log.info("Saved Successfully : $subscription")
            flash.message="SUBSCRIPTION ADDED SUCCESSFULLY"
            session.user.addToSubscriptions(subscription)
            topic.addToSubscriptions(subscription)
            topic.save(flush:true)
            render("SUCCESS")
        }
        session.user.save(flush: true)

    }

    def update() {}

    def delete(Integer subscriptionId) {
        Subscription subscription = Subscription.findById(subscriptionId)
        if (!subscription) {
            flash.error = "NO SUCH SUBSCRIPTION IN OUR DATABASE"
            render("NO SUCH SUBSCRIPTION IN OUR DATABASE")
        } else {
            subscription.discard()
            subscription.delete(flush: true)
            render("DELETED SUCCESSFULLY")
        }
    }
}
