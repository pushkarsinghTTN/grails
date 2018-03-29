package login

import user.User

class LoginController {
    static defaultAction = "home"

    def index() {
        if (session.user && !flash.error) {
            forward(controller: "User", action: "index")
        } else if (session.user && flash.error) {
            render(view: 'error')
        } else {
            flash.error = "PLEASE LOGIN BY PASSING PARAMETERS IN URL WHILE CALLING"
            render(view: 'error')
        }
    }

    def loginhandler() {
        String username=params.loginusername
        String password=params.loginpassword
        //render("$username $password")
        User user1 = User.findByUsernameAndPassword(username, password)
        if (user1) {
            if (user1.active) {
                session.user = user1
                flash.error=null
                forward(controller: "Login", action: "index")
            } else {
                flash.error = "YOUR ACCOUNT IS INACTIVE"
                render(view: 'error')
            }
        } else if (!username || !password) {
            flash.error = "PLEASE LOGIN BY PASSING PARAMETERS IN URL WHILE CALLING"
            render(view: 'error')
        } else {
            flash.error = "USER NOT FOUND"
            redirect(controller: 'Login', action: "index")
        }
    }

    def register() {
        User newuser = new User(firstname: params.firstname, lastname: params.lastname,
                email: params.email, username: params.username, password: params.password,
                confirmpassword: params.confirmpassword)
        if (newuser.validate()) {
            newuser.save(flush: true, failOnError: true)
            session.user = newuser
            forward(controller: 'User', action: 'index')
        }
    }

    def logout() {
        session.invalidate()
        flash.error = "USER LOGGED OUT"
        render(view: 'error')
    }

    def home(){
        render(view: 'index')
    }

}
