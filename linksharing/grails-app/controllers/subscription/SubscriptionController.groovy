package subscription

class SubscriptionController {

    def index() {}

    def save() {}

    def update() {}

    def delete(Integer subscriptionid) {
        Subscription subscription = Subscription.findById(subscriptionid)
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
