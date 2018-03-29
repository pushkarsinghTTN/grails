package linksharing

import readingItem.ReadingItem
import resource.DocumentResource
import resource.LinkResource
import resource.Resource
import resourceRating.ResourceRating
import subscription.Subscription
import topic.Topic
import user.User
import enumeration.Seriousness
import enumeration.Visibility
import util.Utility

class BootStrap {

    def init = { servletContext ->
        println "BootStrap."
        List<User> users = createUsers()
        println "users = $users"
        log.info("Admin is valid- ${users.first().validate()}")
        log.info("Admin has errors while validating- ${users.first().hasErrors()}")

        log.info("User is valid- ${users.last().validate()}")
        log.info("User has errors while validating- ${users.last().hasErrors()}")


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
        User admin = new User()
        admin.firstname = "Pushkar"
        admin.lastname = "Singh"
        admin.username = "pushkar.singh"
        admin.password = "pushkar180195"
        admin.confirmpassword = "pushkar180195"
        admin.email = "pushkar.singh@tothenew.com"
        admin.photo = null
        admin.admin = true
        admin.active = true

        admin.readingItems = []

        if (admin.validate()) {
            admin.save(flush: true, failOnError: true)
            users.add(admin)
        }

        User user = new User()
        user.firstname = "Archit"
        user.lastname = "Chauhan"
        user.username = "archit.chauhan"
        user.password = "archit1234"
        user.confirmpassword = "archit1234"
        user.email = "archit.chauhan@tothenew.com"
        user.photo = null
        user.admin = false
        user.active = false

        user.readingItems = []

        if (user.validate()) {
            user.save(flush: true, failOnError: true)
            users.add(user)
        }

        return users
    }


    void createTopic() {
        List<User> allusers = User.findAll()
        allusers.each {
            if (!it.topics) {
                User temp = it
                (1..5).each {
                    Topic topic = new Topic(name: "Topic ${it} by ${temp.username}", visibility: Visibility.PUBLIC, createdby: temp)
                    if (it == 1)
                        topic.visibility = Visibility.PRIVATE
                    if (!topic.save(flush: true))
                        log.error("Error while saving- $topic")
                    else {
                        log.info("Saved Successfully : $topic")
                        temp.addToTopics(topic)
                    }
                    temp.save(flush: true)
                }
            }
        }
    }


    void createResources() {

        List<Topic> topicList = Topic.findAll()
        topicList.each {
            Topic temp = it

            if (!Resource.findByTopic(temp)) {
                (1..2).each {
                    LinkResource linkResource = new LinkResource(createdby: temp.createdby, description: "This resource is created by ${temp.createdby.name} for topic ${temp.name}", topic: temp, url: "www.${temp.createdby.name}.com/${temp.name}/${it}")
                    DocumentResource documentResource = new DocumentResource(createdby: temp.createdby, description: "This resource is created by ${temp.createdby.name} for topic ${temp.name}", topic: temp, filepath: "/${temp.createdby.name}/${temp.name}/${it}")
                    if (linkResource.save(flush: true)) {
                        temp.addToResources(linkResource)
                        temp.createdby.addToResources(linkResource)
                        log.info("Saved Successfully : $linkResource")
                    } else
                        log.error("Error while saving : $linkResource")

                    if (documentResource.save(flush: true)) {
                        temp.addToResources(documentResource)
                        temp.createdby.addToResources(documentResource)
                        log.info("Saved Successfully : $documentResource")
                    } else
                        log.error("Error while saving : $documentResource")
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
                        Subscription subscription = new Subscription(user: temp, topic: topic, seriousness: Seriousness.SERIOUS)
                        if (!subscription.save(flush: true)) {
                            log.error("Error while saving : $subscription")
                        } else {
                            log.info("Saved Successfully : $subscription")
                            temp.addToSubscriptions(subscription)
                            topic.addToSubscriptions(subscription)
                        }
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
                    ReadingItem readingItem = new ReadingItem(user: user, isRead: true, resource: it.topic.resources[0])
                    if (!user.readingItems.contains(readingItem)) {
                        if (!readingItem.save(flush: true)) {
                            log.error("Error while saving : $readingItem")
                        } else {
                            log.info("Saved Succesfully: $readingItem")
                            user.addToReadingItems(readingItem)
                            it.topic.resources[0].addToReadingItems(readingItem)
                        }
                    }
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
                ResourceRating resourceRating = new ResourceRating(resource: it.resource, createdby: it.user, score: random.nextInt(6))
                if (!resourceRating.save(flush: true)) {
                    log.error("Error while saving : $resourceRating")
                    resourceRating.errors.allErrors.each { println it }
                } else
                    log.info("Saved Successfully : $resourceRating")
            }
        }
    }

}
