package login

import grails.gorm.transactions.Transactional
import user.User

@Transactional
class LoginService {

    def serviceMethod() {

    }

    User registerUser(Map userData) {
        User user = new User(firstname: userData.firstname, lastname: userData.lastname,
                email: userData.email, username: userData.username, password: userData.password,
                confirmpassword: userData.confirmpassword)
        if (user.save(flush: true)) {
            log.info("User created : $user")
            return user
        } else {
            return null
        }

    }
}
