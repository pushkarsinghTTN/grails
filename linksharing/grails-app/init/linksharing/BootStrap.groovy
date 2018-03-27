package linksharing

import Topic.Topic
import User.User
import enumeration.Visibility

class BootStrap {

    def init = { servletContext ->

        List<User> users = createusers()
        log.info("Admin is valid- ${users.first().validate()}")
        log.info("Admin has errors while validating- ${users.first().hasErrors()}")

        log.info("User is valid- ${users.last().validate()}")
        log.info("User has errors while validating- ${users.last().hasErrors()}")

        //Q4
        /*if (User.count() == 0) {
            println("Creating Users")
            List<User> users = createusers()
        }*/

        createtopic()

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

    void createtopic(){
        List<User> allusers = User.findAll()
        allusers.each {
            if (it.topics==null) {
                User temp=it
                temp.topics=[]
                (1..5).each {
                    Topic topic = new Topic("Topic ${it} by ${temp.username}",Visibility.PUBLIC,temp)
                    temp.topics.add(topic)
                    log.info("Topic has errors while validating- ${topic.hasErrors()}")
                }
                it.save()
            }
        }
    }

}
