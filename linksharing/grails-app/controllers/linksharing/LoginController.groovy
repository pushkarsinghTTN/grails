package linksharing

import user.User

import javax.servlet.http.HttpSession

class LoginController {

    def index() {
        if(session.getAttribute("user")==null){
            forward(controller: "User",action:"index")
        }
        else{
            render("Welcome- $session.user.username")
        }
    }

    def loginhandler(String username, String password) {
        User user1=User.findByUsernameAndPassword(username,password)
        if(user1.active){
            session.user=user1
            redirect(controller: "Login",action: "index")
        }
    }

    def logout() {
        session.invalidate()
        forward(controller:"Login",action: "index")
    }
}
