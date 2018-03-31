package subscription

import enumeration.Seriousness
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

    def update(Integer subscriptionId, String seriousness) {
        seriousness=Seriousness.convert(seriousness)
        Subscription subscription= Subscription.findByIdAndSeriousness(subscriptionId,seriousness)
        if(subscription){
            if(subscription.save(flush:true)){
                log.info("Saved Successfully : $subscription")
                render("SUCCESS")
            }else
            {
                log.error("Error while Saving : $subscription")
                render("FAILURE")
            }
        }else
            render("SUBSCRIPTION NOT FOUND")
    }

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
