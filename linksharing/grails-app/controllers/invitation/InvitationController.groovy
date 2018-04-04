package invitation

class InvitationController {

    def index() {
        flash.message="INVITE SENT SUCCESSFULLY"
        redirect(controller:'user',action:'index')
    }
}
