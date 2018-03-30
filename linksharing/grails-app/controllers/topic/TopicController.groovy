package topic

class TopicController {

    def index() {}

    def show(Integer topicid) {
        Topic topic = Topic.read(topicid)
        render("CreatedBy- $topic.createdby.firstname Topicname- $topic.name")

    }

    def save(Topic topic, String seriousness){
        if (!topic.save(flush: true))
            log.error("Error while saving- $topic")
        else {
            log.info("Saved Successfully : $topic")
            session.user.addToTopics(topic)
        }
        session.save(flush: true)

    }

    def delete(Integer topicid) {
        Topic proxytopic = Topic.load(topicid)
        proxytopic.discard()
        proxytopic.delete(flush:true)
        render("Deleted Successfully")
    }

}
