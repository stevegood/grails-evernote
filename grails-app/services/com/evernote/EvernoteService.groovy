package com.evernote

import com.evernote.auth.EvernoteAuth
import com.evernote.clients.ClientFactory
import com.evernote.clients.NoteStoreClient
import com.evernote.edam.notestore.NoteFilter
import com.evernote.edam.type.Note
import com.evernote.edam.type.NoteSortOrder
import com.evernote.edam.type.Notebook
import grails.transaction.Transactional

import javax.annotation.PostConstruct

@Transactional
class EvernoteService {

    EvernoteConfig evernoteConfig
    def grailsApplication

    @PostConstruct
    void init() {
        def cfg = grailsApplication.mergedConfig.grails.plugin.evernote
        evernoteConfig = new EvernoteConfig(
                applicationName: cfg.applicationName,
                consumerKey: cfg.consumer.key,
                consumerSecret: cfg.consumer.secret
        )
    }

    protected NoteStoreClient getNoteStore(String authToken) throws ConfigurationException {
        if (!evernoteConfig.validate()) {
            throw new ConfigurationException(ConfigurationException.INVALID_OR_INCOMPLETE)
        }

        EvernoteAuth auth = new EvernoteAuth(evernoteConfig.environment, authToken)
        ClientFactory factory = new ClientFactory(auth)
        factory.createNoteStoreClient()
    }

    List<Notebook> listNotebooks(String authToken) {
        getNoteStore(authToken).listNotebooks()
    }

    List<Note> listNotesForNotebook(String authToken, Notebook notebook, int offset = 0, int max = 100) {
        NoteFilter noteFilter = new NoteFilter(notebookGuid: notebook.guid, order: NoteSortOrder.CREATED.value, ascending: true)
        getNoteStore(authToken).findNotes(noteFilter, offset, max).notes
    }
}
