package resource

import readingItem.ReadingItem
import user.User

class LinksharingTagLib {

    static namespace = "ls"
    static defaultEncodeAs = [taglib: 'text']

    def read = {
        attrs, body ->
            if (!session.user) {
                def resourceId = attrs?.id
                println resourceId
                Integer userId = session.user.id
                println(userId)
                User user = User.get(userId)
                println(user)
                Resource resource = Resource.get(resourceId)
                println(resource)
                List<ReadingItem> readingItems = ReadingItem.findAllByResource(resource)
                readingItems.each {
                    if (it.user == user) {
                        if (it.isRead) {
                            out << "<a href='/readingItem/changeIsRead/${it.id}/${it.isRead}'style=\"color: #007efc;font-size: small\">Unread</a>"
                        } else
                            out << "<a href='/readingItem/changeIsRead/${it.id}/${it.isRead}'style=\"color: #007efc;font-size: small\">Mark Read</a>"

                    }
                }
            }
    }
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
}
