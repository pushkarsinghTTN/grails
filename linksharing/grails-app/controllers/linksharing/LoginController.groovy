package linksharing

import javax.servlet.http.HttpSession

class LoginController {

    def index() {
        HttpSession session=request.getSession()
        if(session.getAttribute("username")==null){
            render(view:"user/index")
        }
        else{
            render("failure")
        }
    }

    def loginhandler() { }

    def logout() {

    }
}
