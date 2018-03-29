package user

class UserController {

    def index() {
        render("$session.user.username")
    }

    def show(Long id){

    }
}