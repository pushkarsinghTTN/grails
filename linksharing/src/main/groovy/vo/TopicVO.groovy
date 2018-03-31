package vo

import enumeration.Visibility
import user.User

class TopicVO {
    Integer id
    String name
    Visibility visibility
    Integer count
    User createdBy
}
