package linksharing

import ReadingItem.ReadingItem
import Resource.DocumentResource
import Resource.LinkResource
import Resource.Resource
import ResourceRating.ResourceRating
import Subscription.Subscription
import Topic.Topic
import User.User
import enumeration.Seriousness
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
        subscribeTopics()
        createReadingItems()
        createResourceRatings()

    }
    def destroy = {
    }

    List<User> createUsers() {

        List<User> users = []
        User admin = new User("Pushkar", "Singh", "pushkar.singh", "pushkar180195", "pushkar.singh@tothenew.com", null, true, true);
        admin.topics = []
        admin.subscriptions = []
        admin.readingItems = []
        admin.resources = []
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
        user.topics = []
        user.subscriptions = []
        user.readingItems = []
        user.resources = []
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
            if (it.topics.size() == 0) {
                User temp = it
                (1..5).each {
                    Topic topic = new Topic("Topic ${it} by ${temp.username}", Visibility.PUBLIC, temp)
                    temp.topics.add(topic)
                    topic.save(flush: true)
                    log.info("Topic has errors while validating- ${topic.hasErrors()}")
                }
                temp.save(flush: true)
            }
        }
    }

    void createResources() {

        List<Topic> topicList = Topic.findAll()
        topicList.each {
//            println("iterating  the topics list")
            Topic temp = it
            temp.resources = []
            if (!Resource.findByTopic(temp)) {
//                println("inside if statement")
                (1..2).each {
                    LinkResource linkResource = new LinkResource(temp.createdby, "This resource is created by ${temp.createdby.name} for topic- ${temp.name}", temp, "www.${temp.createdby.name}.com/${temp.name}/${it}")
                    DocumentResource documentResource = new DocumentResource(temp.createdby, "This resource is created by ${temp.createdby.name} for topic ${temp.name}", temp, "/${temp.createdby.name}/${temp.name}/${it}")
                    if (linkResource.validate()) {
                        linkResource.save(flush: true)
                        temp.resources.add(linkResource)
                    }
                    log.info("LinkResource has errors while validating- ${linkResource.hasErrors()}")
                    if (documentResource.validate()) {
                        documentResource.save(flush: true)
                        temp.resources.add(documentResource)
                    }
                    log.info("DocumentResource has errors while validating- ${documentResource.hasErrors()}")
                }
            }
            temp.save(flush: true)
        }
    }

    void subscribeTopics() {
        List<User> userList = User.findAll()
        List<Topic> topicList = Topic.findAll()
        userList.each {
            User temp = it
            for (Topic topic : topicList) {
                if (topic.createdby != temp) {
                    if (!Subscription.findByUserAndTopic(temp, topic)) {
                        Subscription subscription = new Subscription(temp, topic, Seriousness.SERIOUS)
                        if (subscription.validate())
                            subscription.save(flush: true)
                        log.info("Subscription has errors while validating- ${subscription.hasErrors()}")
                    }
                }
            }
        }
    }

    void createReadingItems() {
        List<Subscription> subscriptionList = Subscription.findAll()
        List<User> userList = User.findAll()
        subscriptionList.each {
            for (User user : userList) {
                if (it.user == user && !user.topics.contains(it.topic)) {
                    ReadingItem readingItem = new ReadingItem(user, true, it.topic.resources[0])
                    if (readingItem.validate() && !user.readingItems.contains(readingItem))
                        readingItem.save(flush: true)
                    log.info("ReadingItem has errors while validating- ${readingItem.hasErrors()}")
                }
            }
        }
    }

    void createResourceRatings() {
        Random random = new Random()
        List<ReadingItem> readingItemList = ReadingItem.findAll()
        println readingItemList.size()
        readingItemList.each {
            if (it.isRead) {
                //println("about to create")
                ResourceRating resourceRating = new ResourceRating(it.resource, it.user, random.nextInt(6))
                if (resourceRating.validate()) {
                    resourceRating.save(flush: true)
                    //println("created")
                }
                log.info("ResourceRating has errors while validating- ${resourceRating.hasErrors()}")
            }
        }
    }

}
