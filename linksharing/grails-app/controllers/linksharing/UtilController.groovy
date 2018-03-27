package linksharing

class UtilController {

    def index() {
        log.info("This is to maintain logs")
        log.error("This is for logging errors")
        log.warn("warning started")
        log.debug("debugging started")

        log.info "request params: $params"

        render "hello"
    }
}
