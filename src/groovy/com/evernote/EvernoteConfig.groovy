package com.evernote

import grails.validation.Validateable

/**
 * Created by steve on 9/13/14.
 */
@Validateable
class EvernoteConfig {
    final String applicationName = ''
    final String consumerKey = ''
    final String consumerSecret = ''
    final com.evernote.auth.EvernoteService environment = com.evernote.auth.EvernoteService.SANDBOX

    static constraints = {
        applicationName blank: false
        consumerKey blank: false
        consumerSecret blank: false
        environment nullable: false, inList: (com.evernote.auth.EvernoteService.values() as List)
    }
}
