package linksharing


class LoginCheckInterceptor {

    public LoginCheckInterceptor() {
        matchAll().excludes(controller: 'login', action: 'loginhandler')
    }

    boolean before() {
        if (!session.user) {
            flash.error = "NO ACTIVE SESSION"
            redirect(controller: 'login', action: 'loginhandler')
        }
        true
    }

    boolean after() {true}

    void afterView() {
        // no-op
    }
}
