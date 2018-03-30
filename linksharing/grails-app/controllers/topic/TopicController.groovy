package topic

import enumeration.Visibility

class TopicController {

    def index() {}

    def show(Integer topicid) {
        Topic topic = Topic.read(topicid)
        render("CreatedBy- $topic.createdby.firstname Topicname- $topic.name")

    }

    def save(String topicname, String visibility){
        Topic topic = new Topic(createdby: session.user,topicname: topicname,visibility: Visibility.convert(visibility))
        if (!topic.save(flush: true)) {
            log.error("Error while saving- $topic")
            flash.error = "TOPIC NOT SAVED"
        }else {
            log.info("Saved Successfully : $topic")
            flash.message="TOPIC SAVED SUCCESSFULLY"
            session.user.addToTopics(topic)
            render("SUCCESS")
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
