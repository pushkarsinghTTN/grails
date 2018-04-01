package login

import resource.Resource
import user.User
import vo.ResourceVO

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
            flash.message="ENTER THE CORRECT PASSWORD AND TRY AGAIN"
            render(view: 'message')
        }  else if (!username || !password) {
            flash.message="ENTER THE CORRECT CREDENTIALS AND TRY AGAIN"
            render(view: 'message')
        }else {
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
            flash.message="SUCCESSFULLY REGISTERED"
            session.user = newuser
            forward(controller: 'User', action: 'index')
        }
    }

    def logout() {
        session.invalidate()
        flash.error = "USER LOGGED OUT"
        redirect(controller: 'login', action: 'index')
    }

//    def home(){
//        render(view: 'index')
//    }

    def topPosts(){
        List<ResourceVO> topPosts = Resource.getTopPost()
        println("$topPosts.id + $topPosts.createdBy + $topPosts.topicName")
    }

}
