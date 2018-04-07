package user

import co.TrendingTopicsCO
import enumeration.Visibility
import subscription.Subscription
import topic.Topic
import vo.InboxVO
import vo.SubscriptionsVO
import vo.TrendingTopicsVO

class UserController {

    def index() {
        if (session.user) {
            List<InboxVO> unReadResourcesList = session.user.getUnReadResources()
            List<SubscriptionsVO> subscriptionsList = session.user.getUserSubscriptions()
            List<TrendingTopicsVO> trendingTopicsList= Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: session.user))
            render(view: 'index', model: [unReadResourcesList: unReadResourcesList, subscriptionsList: subscriptionsList,trendingTopicsList:trendingTopicsList])
        } else
            redirect(controller: 'login', action: 'index')
    }

//    def show(Long id) {
//        Topic topic = Topic.findById(id)
//        if (!topic) {
//            flash.error = "NO SUCH TOPIC"
//            redirect(controller: 'login', action: 'index')
//        }
//        else{
//            if(topic.visibility==Visibility.PUBLIC){
//                render("SUCCESS")
//            }
//            else{
//                Subscription subscription=Subscription.findByUserAndTopic(session.user,topic)
//                if(subscription){
//                    render("SUCCESS")
//                }
//                else{
//                    flash.error = "YOU ARE NOT SUBSCRIBED TO THIS TOPIC"
//                    redirect(controller: 'login', action: 'index')
//                }
//            }
//        }
//    }
}