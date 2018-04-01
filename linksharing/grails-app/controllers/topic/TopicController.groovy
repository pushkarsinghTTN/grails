package topic

import co.ResourceSearchCo
import enumeration.Visibility
import resource.Resource

class TopicController {

    def index() {}

//    def show(Integer topicid) {
//        Topic topic = Topic.read(topicid)
//        render("CreatedBy- $topic.createdBy.firstname Topicname- $topic.name")
//
//    }

    def show(ResourceSearchCo resourceSearchCo){
        Topic topic = Resource.search(resourceSearchCo)
        render("CreatedBy- $topic.createdBy.firstname Topicname- $topic.name")

    }

    def save(String topicname, String visibility){
        Topic topic = new Topic(createdBy: session.user,name: topicname,visibility: Visibility.convert(visibility))
        if (!topic.save(flush: true)) {
            log.error("Error while saving- $topic")
            flash.error = "TOPIC NOT SAVED"
        }else {
            log.info("Saved Successfully : $topic")
            flash.message="TOPIC SAVED SUCCESSFULLY"
            session.user.addToTopics(topic)
            render("SUCCESS")
        }
        session.user.save(flush: true)

    }

    def delete(Integer topicid) {
        Topic proxytopic = Topic.load(topicid)
        if(!proxytopic){
            flash.error="NO SUCH TOPIC IN OUR DATABASE"
            render("NO SUCH TOPIC IN OUR DATABASE")
        }
        else{proxytopic.discard()
        proxytopic.delete(flush:true)
        render("DELETED SUCCESSFULLY")}
    }

}
