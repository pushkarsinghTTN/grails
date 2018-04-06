package vo

import topic.Topic
import user.User

class ResourceVO {
    Integer id
    Integer count
    String description
    User createdBy
    Topic topic


    @Override
    public String toString() {
        return "ResourceVO{" +
                "id=" + id +
                ", count=" + count +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                '}';
    }
}
