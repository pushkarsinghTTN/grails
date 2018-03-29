package linksharing

import javax.servlet.http.HttpSession

class LoginController {

    def index() {
        HttpSession session=request.getSession()
        if(session.getAttribute("username")==null){
            forward(controller: "User",action:"index")
        }
        else{
            render("failure")
        }
    }

    def loginhandler(String username, String password) {
        render("username- $username password- $password")
    }

    def logout() {
        HttpSession session=request.getSession()
        session.invalidate()
        forward(controller:"Login",action: "index")
    }
}
