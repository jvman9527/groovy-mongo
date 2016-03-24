package github.jvman9527.groovy.mongo

import com.mongodb.Mongo
import com.mongodb.MongoClient

/**
 * MongoDB driver DBCollection delegation
 */
class GroovyMongo {

    @Delegate
    Mongo mongoClient

    public GroovyMongo() {
        mongoClient = new MongoClient()
    }

    public GroovyMongo(String host) {
        mongoClient = new MongoClient(host)
    }

    public GroovyMongo(String host, Integer port) {
        mongoClient = new MongoClient(host, port)
    }

    MongoDB getDB(String name) {
        new MongoDB(mongoClient.getDB(name))
    }

}
