package com.mastercoding.journalapp

import android.app.Application



//So this journal user will act as a subclass to allow the application to run it before.
//And to prepare the instance of this application at passing the context of this application.
//So we need to create two fields here the name, the user name, in order to get the current user name
// and the ID.
class JournalUser : Application() {

    var username: String? = null
    var userId: String? = null
//So we said that whenever you have a database and connectivity and like a user to get in this case,
//the authentication, we need to prepare a subclass to be running in the background and the first launching
//of the application to be preparing the current user, the connection and return only one singletorn of this class
    companion object{
    //So because it's a singleton and following the singleton design pattern, I'm going to use the companion
    //object and create an instance of this class.
        var instance: JournalUser? = null
        get() {
            if (field == null){
                // create a new instance from journal user
                field = JournalUser()
            }
            return field
        }
        private set
    }
}