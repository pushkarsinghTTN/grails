package readingItem

import user.User

class ReadingItemController {

    def index() { }

    def changeisRead(Long id, Boolean isRead){
        if(ReadingItem.executeUpdate("UPDATE RadingItem set isRead=:isRead1 where id=:id1",
                [isRead1=isRead,id1=id])){
            render("SUCCESS")
        }
        else
            render("ERROR")

    }
}
