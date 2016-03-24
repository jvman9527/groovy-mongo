package github.jvman9527.groovy.mongo

import com.mongodb.DB

/**
 * MongoDB driver DB delegation
 */
class MongoDB {

    @Delegate
    DB db

    public MongoDB(DB db) {
        this.db = db
    }

    /**
     * MongoCollection getter hook
     * @param name MongoDB collection name
     */
    def getProperty(String name) {
        new MongoCollection(db.getCollection(name))
    }

}
