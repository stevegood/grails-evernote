package com.evernote

/**
 * Created by steve on 9/13/14.
 */
class ConfigurationException extends Exception {
    static String INVALID_OR_INCOMPLETE = 'Invalid or incomplete configuration'
    ConfigurationException(String message) {
        super(message)
    }
}
