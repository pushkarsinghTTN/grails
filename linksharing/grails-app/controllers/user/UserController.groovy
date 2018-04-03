package user

import enumeration.Visibility
import subscription.Subscription
import topic.Topic

class UserController {

    def index() {
        render(view: 'index')
    }

    def show(Long id) {
        Topic topic = Topic.findById(id)
        if (!topic) {
            flash.error = "NO SUCH TOPIC"
            redirect(controller: 'login', action: 'index')
        }
        else{
            if(topic.visibility==Visibility.PUBLIC){
                render("SUCCESS")
            }
            else{
                Subscription subscription=Subscription.findByUserAndTopic(session.user,topic)
                if(subscription){
                    render("SUCCESS")
                }
                else{
                    flash.error = "YOU ARE NOT SUBSCRIBED TO THIS TOPIC"
                    redirect(controller: 'login', action: 'index')
                }
            }
        }
    }
}