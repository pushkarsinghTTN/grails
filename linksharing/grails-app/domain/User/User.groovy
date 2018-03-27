package User

class User{
    String firstname
    String lastname
    String username
    String password
    String email
    Byte photo
    Boolean admin
    Boolean active
    Date lastUpdated
    Date dateCreated

    User(String firstname, String lastname, String username, String password, String email, Byte photo, Boolean admin, Boolean active) {
        this.firstname = firstname
        this.lastname = lastname
        this.username = username
        this.password = password
        this.email = email
        this.photo = photo
        this.admin = admin
        this.active = active
    }
    //static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    static constraints = {
        email(email: true, nullable: false, blank: false)
        password(nullable: false, blank: false, size: 5..15)
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
        username(nullable: false, blank: false)
        photo(nullable: true,sqlType:'longBlob')
        admin(nullable: true)
        active(nullable: true)
    }

    static transients = ['firstname','lastname']


}
