package subscription

import enumeration.Seriousness
import topic.Topic
import user.User

class SubscriptionController {

    def index() {}

    def save(){
        Topic topic=Topic.findById(params.topicId)
        Subscription subscription = new Subscription(createdby: session.user,topic: topic)
        if (!subscription.save(flush: true)) {
            log.error("Error while saving- $subscription")
            subscription.errors.allErrors.each {println it}
            render("SUBSCRIPTION NOT DONE")
        }else {
            log.info("Saved Successfully : $subscription")
            flash.message="SUBSCRIPTION ADDED SUCCESSFULLY"
            redirect(controller: 'user',action: 'index')
//            session.user.addToSubscriptions(subscription)
//            topic.addToSubscriptions(subscription)
//            topic.save(flush:true)
//            session.user.save(flush: true)
//            render("SUCCESS")
        }
    }

    def update(Integer subscriptionId, String seriousness) {
        seriousness=Seriousness.convert(seriousness)
        Subscription subscription= Subscription.findById(subscriptionId)
        if(subscription){
            subscription.seriousness=seriousness
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

    def delete() {
        //println(params.topicId)
        Topic topic= Topic.findById(params.topicId)
        Subscription subscription = Subscription.findByUserAndTopic(session.user,topic)
        //User user= session.user
        //session.user.removeFromSubscriptions(subscription)
        if (!subscription) {
            flash.error = "NO SUCH SUBSCRIPTION IN OUR DATABASE"
            render("NO SUCH SUBSCRIPTION IN OUR DATABASE")
        } else {
            subscription.discard()
            subscription.delete(flush: true)
            redirect(controller:'user',action:'index')
            //render("DELETED SUCCESSFULLY")
        }
    }
}
