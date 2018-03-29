package user

import enumeration.Visibility
import topic.Topic

class UserController {

    def index() {
        render("$session.user.username")
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
        }
    }
}