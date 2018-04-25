package github.jvman9527.groovy.mongo

import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

/**
 * MongoDB driver DBCollection delegation
 */
class GroovyMongo {

    @Delegate
    Mongo mongoClient

    GroovyMongo() {
        mongoClient = new MongoClient()
    }

    GroovyMongo(String host) {
        mongoClient = new MongoClient(host)
    }

    GroovyMongo(String host, Integer port) {
        mongoClient = new MongoClient(host, port)
    }

    GroovyMongo(MongoClientURI mongoClientURI) {
        mongoClient = new MongoClient(mongoClientURI)
    }

    MongoDB getDB(String name) {
        new MongoDB(mongoClient.getDB(name))
    }

}

