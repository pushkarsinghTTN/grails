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
// else if (session.user && flash.error) {
//            render(view: 'error')
//        } else {
//            flash.error = "PLEASE LOGIN BY PASSING PARAMETERS IN URL WHILE CALLING"
//            render(view: 'error')
//        }
        else {
            log.info("NO SESSION USER FOUND")
            render(view: 'index')
        }
    }

    def loginhandler() {
        String username = params.loginusername
        String password = params.loginpassword
        User user1 = User.findByUsernameAndPassword(username, password)
        if (user1) {
            if (user1.active) {
                session.user = user1
//                flash.error=null
//                flash.message="YOU LOGGED IN SUCCESSFULLY"
                forward(controller: "user", action: "index")
            } else {
                flash.error = "YOUR ACCOUNT IS INACTIVE"
                render(view: 'error')
            }
        } else if (User.findByUsername(username)) {
            flash.message = "ENTER THE CORRECT PASSWORD AND TRY AGAIN"
            render(view: 'message')
        } else if (!username || !password) {
            flash.message = "ENTER THE CORRECT CREDENTIALS AND TRY AGAIN"
            render(view: 'message')
        } else {
            flash.error = "USER NOT FOUND"
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
            flash.error = "Unable to register user"
        }
    }

    def logout() {
        session.invalidate()
        flash.error = "USER LOGGED OUT"
        redirect(controller: 'login', action: 'index')
    }

    def forgotPassword() {
        println ">>>>>>>>>>${params.username}"
        println ">>>>>>>>>>${params.newPassword}"
        User user= User.findByUsername(params.username)
        if(user) {
            user.password = params.newPassword
            user.confirmpassword = params.confirmNewPassword
            if (user.save(flush: true)) {
                session.user = user
                forward(controller: 'User', action: 'index')
            }
            else{

                flash.error =  user.errors
                render(view: 'forgotPassword')
            }
        }

    }

    def topPosts() {
        List<ResourceVO> topPosts = Resource.getTopPost()
        println("$topPosts.id + $topPosts.createdBy + $topPosts.topicName")
    }

}
