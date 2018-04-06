package readingItem

import user.User

class ReadingItemController {

    def index() { }

    def changeisRead(){
        String str="UPDATE ReadingItem set isRead=true where id=${params.id}"
        println(str)
        if(ReadingItem.executeUpdate(str)){
            redirect(controller:'user', action: 'index')
        }
        else
            render("ERROR")

    }}
