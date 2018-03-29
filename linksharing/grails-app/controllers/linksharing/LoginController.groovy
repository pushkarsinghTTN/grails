package linksharing

import user.User

class LoginController {

    def index() {
        if (session.user) {
            forward(controller: "User", action: "index")
        } else {
            if(!flash.error)
                redirect(action:"index")
        }
    }

    def loginhandler(String username, String password) {
        User user1 = User.findByUsernameAndPassword(username, password)
        if(user1){
            if (user1.active) {
                session.user = user1
            }
            else {
                flash.error = "YOUR ACCOUNT IS NOT ACTIVE"
            }
            redirect(action:"index")
        }

    }

    def logout() {
        session.invalidate()
        forward(controller: "login", action: "index")
    }
}
