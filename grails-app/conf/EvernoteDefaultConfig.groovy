import com.evernote.auth.EvernoteService

grails.validateable.packages = ['com.evernote.EvernoteConfig']
evernote {
    applicationName = ''
    environment = EvernoteService.SANDBOX
    consumer {
        key = ''
        secret = ''
    }
}
