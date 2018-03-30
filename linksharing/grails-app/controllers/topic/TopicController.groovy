package topic

class TopicController {

    def index() {}

    def show(Integer topicid) {
        Topic topic = Topic.read(topicid)
        render("CreatedBy- $topic.createdby.firstname Topicname- $topic.name")

    }

    def delete(Integer topicid) {
        Topic proxytopic = Topic.load(topicid)
        proxytopic.discard()
        proxytopic.delete(flush:true)
        render("Deleted Successfully")
    }

}
