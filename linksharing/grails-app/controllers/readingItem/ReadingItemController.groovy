package readingItem

import user.User

class ReadingItemController {

    def index() { }

    def changeisRead(Long id, Boolean isRead){
        if(ReadingItem.updateIsRead(isRead, id)){
            render("SUCCESS")
        }
        else
            render("ERROR")

    }
}
