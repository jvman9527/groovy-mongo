package github.jvman9527.groovy.mongo

import com.mongodb.WriteResult
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection

/**
 * MongoDB driver DBCollection delegation
 */
class MongoCollection {

    @Delegate
    private DBCollection dbCollection

    MongoCollection(DBCollection dbCollection) {
        this.dbCollection = dbCollection
    }

    /**
     * a more groovy findOne
     * @param queryMap
     * @param fieldsMap
     */
    Map findOne(Map queryMap, Map fieldsMap = null) {
        dbCollection.findOne(queryMap as BasicDBObject, fieldsMap as BasicDBObject)
    }

    /**
     * a more groovy find
     * @param queryMap
     * @param fieldsMap
     */
    MongoCursor find(Map queryMap, Map fieldsMap = null) {
        new MongoCursor(dbCollection.find(queryMap as BasicDBObject, fieldsMap as BasicDBObject))
    }

    /**
     * a more groovy findAndModify
     * @param args
     */
    Map findAndModify(Map args) {
        dbCollection.findAndModify(
            args.query as BasicDBObject,
            args.fields as BasicDBObject,
            args.sort as BasicDBObject,
            args.remove as boolean,
            args.update as BasicDBObject,
            args.returnNew as boolean,
            args.upsert as boolean
        ) as Map
    }

    /**
     * a more groovy insert
     */
    WriteResult insert(Map... document) {
        dbCollection.insert(document as BasicDBObject[])
    }

    /**
     * a more groovy insert
     */
    WriteResult insert(List<Map> document) {
        dbCollection.insert(document as BasicDBObject[])
    }

    /**
     * a more groovy update
     */
    WriteResult update(Map query, Map update, Boolean upsert = false, Boolean multi = false) {
        dbCollection.update(query as BasicDBObject, update as BasicDBObject, upsert, multi)
    }

}

