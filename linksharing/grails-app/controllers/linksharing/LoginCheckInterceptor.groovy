package linksharing


class LoginCheckInterceptor {

    public LoginCheckInterceptor(){
        matchAll().excludes(controller: 'login')
    }

    boolean before() { true }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
