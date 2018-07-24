package github.jvman9527.groovy.mongo

import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import github.jvman9527.groovy.mongo.transformer.GStringTransformer
import org.bson.BSON

/**
 * MongoDB driver DBCollection delegation
 */
class GroovyMongo {

    final static MONGO_URI_PATTERN = "^mongodb(\\+srv)?://.*"

    static {
        BSON.addEncodingHook(GString, new GStringTransformer())
    }

    @Delegate
    Mongo mongoClient

    GroovyMongo() {
        mongoClient = new MongoClient()
    }

    GroovyMongo(String host) {
        mongoClient = (host ==~ MONGO_URI_PATTERN) ?
                        new MongoClient(new MongoClientURI(host)) :
                        new MongoClient(host)
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

