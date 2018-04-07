package topic

class TopicController {

    def index() {}

//    def show(Integer topicid) {
//        Topic topic = Topic.read(topicid)
//        render("CreatedBy- $topic.createdBy.firstname Topicname- $topic.name")
//
//    }

//    def show(ResourceSearchCo resourceSearchCo){
//        Topic topic = Resource.search(resourceSearchCo)
//        render("CreatedBy- $topic.createdBy.firstname Topicname- $topic.name")
//        User user = User.read(session.user.id)
//        render(user.topics)
//
//    }
    def show(){
        Long topicId= new Long(params.id)
        Topic topic = Topic.findById(topicId)
        println topic
        render(view: 'show', model: [topic : topic])
    }

    def save(){
        Topic newtopic = new Topic(createdBy: session.user,name: params.topicName,visibility: params.topicVisibility)
        if (!newtopic.save(flush: true)) {
            log.error("Error while saving- $newtopic")
            newtopic.errors.allErrors.each {println it}
            flash.error = "TOPIC NOT SAVED"
        }else {
            log.info("Saved Successfully : $newtopic")
            flash.message="TOPIC SAVED SUCCESSFULLY"
            session.user.addToTopics(newtopic)
            flash.message="SUCCESSFULLY SAVED"
            //session.user.save(flush: true)
           }
        redirect(controller:'user',action:'index')



    }

    def delete() {
        Topic proxytopic = Topic.load(params.id)
        if(!proxytopic){
            flash.error="NO SUCH TOPIC IN OUR DATABASE"
            render("NO SUCH TOPIC IN OUR DATABASE")
        }
        else{proxytopic.discard()
        proxytopic.delete(flush:true)
        //render("DELETED SUCCESSFULLY")
        redirect(controller: 'user',action: 'index')}
    }

}
