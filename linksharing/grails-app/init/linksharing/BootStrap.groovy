package linksharing

import Resource.DocumentResource
import Resource.LinkResource
import Resource.Resource
import Topic.Topic
import User.User
import enumeration.Visibility

class BootStrap {

    def init = { servletContext ->

        List<User> users = createUsers()
        log.info("Admin is valid- ${users.first().validate()}")
        log.info("Admin has errors while validating- ${users.first().hasErrors()}")

        log.info("User is valid- ${users.last().validate()}")
        log.info("User has errors while validating- ${users.last().hasErrors()}")

        //Q4
        /*if (User.count() == 0) {
            println("Creating Users")
            List<User> users = createUsers()
        }*/

        createTopic()
        createResources()

    }
    def destroy = {
    }

    List<User> createUsers() {

        List<User> users = []
        User admin = new User("Pushkar", "Singh", "pushkar.singh", "pushkar180195", "pushkar.singh@tothenew.com", null, true, true);
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

        User user = new User("Archit", "Chauhan", "archit.chauhan", "archit1234", "archit.chauhan@tothenew.com", null, false, true);
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

    void createTopic() {
        List<User> allusers = User.findAll()
        allusers.each {
            if (it.topics == null) {
                User temp = it
                temp.topics = []
                (1..5).each {
                    Topic topic = new Topic("Topic ${it} by ${temp.username}", Visibility.PUBLIC, temp)
                    temp.topics.add(topic)
                    topic.save(flush:true)
                    log.info("Topic has errors while validating- ${topic.hasErrors()}")
                }
                temp.save()
            }
        }
    }

    void createResources() {

        List<Topic> topicList = Topic.findAll()
        topicList.each {
//            println("iterating  the topics list")
            Topic temp = it
            if (!Resource.findByTopic(temp)) {
//                println("inside if statement")
                (1..2).each {
                    LinkResource linkResource = new LinkResource(temp.createdby, "This resource is created by ${temp.createdby.name} for topic- ${temp.name}", temp, "www.${temp.createdby.name}.com/${temp.name}/${it}")
                    DocumentResource documentResource = new DocumentResource(temp.createdby, "This resource is created by ${temp.createdby.name} for topic ${temp.name}", temp, "/${temp.createdby.name}/${temp.name}/${it}")
                    if (linkResource.validate()) {
                        linkResource.save(flush:true)
                    }
                    log.info("LinkResource has errors while validating- ${linkResource.hasErrors()}")
                    if (documentResource.validate()) {
                        documentResource.save(flush:true)
                    }
                    log.info("DocumentResource has errors while validating- ${documentResource.hasErrors()}")
                }
            }
        }
    }

}
