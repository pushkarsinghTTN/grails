package linksharing

import User.User

class BootStrap {

    def init = { servletContext ->

        List<User> users = createusers()

    }
    def destroy = {
    }

    List<User> createusers() {

        List<User> users = []

        User admin = new User("Pushkar", "Singh", "pushkar.singh", "pushkar180195", "pushkar.singh@tothenew.com",null, true, true);
        /*//Q1
        if(admin.validate()){
            admin.save()
            users.add(admin)
            }
        */

        //Q2
        if (admin.validate()) {
            admin.save(flush: true, failOnError: true)
            users.add(admin)
        }


        User user = new User("Archit", "Chauhan", "archit.chauhan", "archit1234", "archit.chauhan@tothenew.com",null, false, true);
        /*//Q1
        if(user.validate()){
            user.save()
            users.add(user)
            }
        */

        //Q2
        if (user.validate()) {
            user.save(flush: true, failOnError: true)
            users.add(user)
        }

        return users
    }

}
