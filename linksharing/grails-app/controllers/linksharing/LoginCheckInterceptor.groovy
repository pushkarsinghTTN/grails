package linksharing


class LoginCheckInterceptor {

    public LoginCheckInterceptor() {
        matchAll().excludes(controller: 'login')
    }

    boolean before() { true }

    boolean after() {
        if (!session.user) {
            flash.error = "NO ACTIVE SESSION"
            redirect(controller: 'login', action: 'loginhandler')
        }
    }

    void afterView() {
        // no-op
    }
}
