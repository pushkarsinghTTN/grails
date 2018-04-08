package user

import co.TrendingTopicsCO
import enumeration.Visibility
import subscription.Subscription
import topic.Topic
import vo.InboxVO
import vo.SubscriptionsVO
import vo.TopicVO
import vo.TrendingTopicsVO
import vo.UserVO

class UserController {

    UserService userService

    def index() {
        if (session.user) {
            List<InboxVO> unReadResourcesList = session.user.getUnReadResources()
            List<SubscriptionsVO> subscriptionsList = session.user.getUserSubscriptions()
            List<TrendingTopicsVO> trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: session.user))
            render(view: 'index', model: [unReadResourcesList: unReadResourcesList, subscriptionsList: subscriptionsList, trendingTopicsList: trendingTopicsList])
        } else
            redirect(controller: 'login', action: 'index')
    }

    def editProfile() {
        Map map = userService.showProfile(new String(session.user.username))
        render(view: 'editProfile', model: [user: map.userInformation, userTopics: map.userTopics, userPosts: map.userPosts])
    }

    def updateUser() {
        if (userService.changeUserCredentials(params, new String(session.user.username))) {
            flash.message = "Updation Successful"
        } else
            flash.error = "Unable To Update Credentials"
        redirect(controller: 'user', action: 'index')
    }

    def changePassword() {
        if (userService.changePassword(params, new String(session.user.username))) {
            flash.message = "Password Changed Successfully"
        } else
            flash.error = "Unable To Change password"

        redirect(controller: 'user', action: 'index')
    }


}