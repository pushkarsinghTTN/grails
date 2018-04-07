package login

import resource.Resource
import user.User
import vo.ResourceVO

class LoginController {

    LoginService loginService

    def index() {
        if (session.user) {
            log.info("REDIRECTING TO USER INDEX")
            forward(controller: "User", action: "index")
        }
        else {
            log.info("NO SESSION USER FOUND")
            render(view: 'index')
        }
    }

    def loginhandler() {
        User user= loginService.loginUser(params)
        if (user) {
            if (user.active) {
                session.user = user
                forward(controller: "user", action: "index")
            } else {
                flash.error = "YOUR ACCOUNT IS INACTIVE"
                render(view: 'error')
            }
        }else {
            flash.error = "INCORRECT USERNAME OR PASSWORD"
            redirect(controller: 'Login', action: "index")
        }
    }

    def register() {
        User user = loginService.registerUser(params)
        if (user) {
            flash.message = "SUCCESSFULLY REGISTERED"
            session.user = user
            forward(controller: 'User', action: 'index')
        } else {
            flash.error = "UNABLE TO REGISTER USER"
        }
    }

    def logout() {
        session.invalidate()
        flash.error = "USER LOGGED OUT"
        redirect(controller: 'login', action: 'index')
    }

    def forgotPassword() {
        User user= User.findByUsername(params.username)
        if(user) {
                session.user = user
            flash.message="PASSWORD CHANGED SUCCESSFULLY"
                forward(controller: 'User', action: 'index')
            }
            else{
                flash.error = "UNABLE TO CHANGE THE PASSWORD"
                render(view: 'forgotPassword')
            }
        }

}
