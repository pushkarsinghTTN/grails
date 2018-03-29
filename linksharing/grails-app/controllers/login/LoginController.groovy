package login

import user.User

class LoginController {
    //static defaultAction = "loginhandler"

    def index() {
        if (session.user) {
            forward(controller: "User", action: "index")
        } else {
            flash.error = "PLEASE LOGIN BY PASSING PARAMETERS IN URL WHILE CALLING"
            render(view: 'index')
        }
    }

    def loginhandler(String username, String password) {
        User user1 = User.findByUsernameAndPassword(username, password)
        if (user1) {
            if (user1.active) {
                session.user = user1
                forward(controller: "Login",action: "index")
            } else {
                flash.error= "YOUR ACCOUNT IS INACTIVE"
                render(view: 'index')
            }
        }
        else if (!username || !password) {
            flash.error = "PLEASE LOGIN BY PASSING PARAMETERS IN URL WHILE CALLING"
            render(view: 'index')
        }
        else {
            flash.error = "USER NOT FOUND"
            redirect(controller: 'Login', action: "index")
        }
    }

def logout() {
    session.invalidate()
    flash.error = "USER LOGGED OUT"
    render(view: 'index')
}

}
