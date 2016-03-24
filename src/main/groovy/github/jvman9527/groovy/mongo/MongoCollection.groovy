package github.jvman9527.groovy.mongo

import groovy.json.JsonSlurper

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection

/**
 * MongoDB driver DBCollection delegation
 */
class MongoCollection {

    @Delegate
    private DBCollection dbCollection

    public MongoCollection(DBCollection dbCollection) {
        this.dbCollection = dbCollection
    }

    /**
     * a more groovy findOne
     * @param queryString
     * @param fieldsString
     */
    def findOne(String queryString, String fieldsString = null) {
        Map queryMap = toMap(queryString)
        Map fieldsMap = toMap(fieldsString)
        findOne(queryMap, fieldsMap)
    }

    /**
     * a more groovy findOne
     * @param queryMap
     * @param fieldsMap
     */
    def findOne(Map queryMap, Map fieldsMap = null) {
        dbCollection.findOne(queryMap as BasicDBObject, fieldsMap as BasicDBObject)
    }

    /**
     * a more groovy find
     * @param queryString
     */
    def find(String queryString, String fieldsString = null) {
        find(toMap(queryString), toMap(fieldsString))
    }

    /**
     * a more groovy find
     * @param queryMap
     * @param fieldsMap
     */
    def find(Map queryMap, Map fieldsMap = null) {
        new MongoCursor(dbCollection.find(queryMap as BasicDBObject, fieldsMap as BasicDBObject))
    }

    /**
     * a more groovy findAndModify
     * @param args
     */
    def findAndModify(Map args) {
        dbCollection.findAndModify(
            args.query as BasicDBObject,
            args.fields as BasicDBObject,
            args.sort as BasicDBObject,
            args.remove as boolean,
            args.update as BasicDBObject,
            args.returnNew as boolean,
            args.upsert as boolean
        )
    }

    /**
     * json string to map util
     * @param jsonString
     */
    private Map toMap(String jsonString) {
        jsonString ? new JsonSlurper().parseText(jsonString) : null
    }

}
