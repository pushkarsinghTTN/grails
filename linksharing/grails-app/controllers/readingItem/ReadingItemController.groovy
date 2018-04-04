package readingItem

import user.User

class ReadingItemController {

    def index() { }

//    def changeisRead(Long id, Boolean isRead){
//        if(ReadingItem.updateIsRead(isRead, id)){
//            render("SUCCESS")
//        }
//        else
//            render("ERROR")
//
//    }
//
//    static updateIsRead(Boolean isRead, Long id) {
//        try {
//            ReadingItem.executeUpdate("UPDATE ReadingItem set isRead=:isRead1 where id=:id1", [isRead1 = isRead, id1 = id])
//            return true
//        }
//        catch (Exception exp) {
//            log.error("Error while updating: " + exp.message)
//            return false
//        }
//    }
}
