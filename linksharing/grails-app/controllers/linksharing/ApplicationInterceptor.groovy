package linksharing


class ApplicationInterceptor {

    public ApplicationInterceptor(){
        matchAll()
    }

    boolean before() {
        log.info("INCOMING REQUEST: ${params.toString()}")
        if (!session.user) {
            redirect(action: 'login')
            return false
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
