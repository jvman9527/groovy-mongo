package github.jvman9527.groovy.mongo

import com.mongodb.AggregationOptions
import com.mongodb.AggregationOutput
import com.mongodb.Cursor
import com.mongodb.ReadPreference
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
     * a more groovy distinct
     * @param fieldName
     * @param query
     */
    List distinct(String fieldName, Map query) {
        dbCollection.distinct(fieldName, query as BasicDBObject)
    }

    /**
     * a more groovy distinct
     * @param fieldName
     * @param query
     */
    List distinct(Map query, String fieldName) {
        dbCollection.distinct(fieldName, query as BasicDBObject)
    }

    /**
     * a more groovy count
     * @param query
     * @return
     */
    Long count(Map query) {
        dbCollection.count(query as BasicDBObject)
    }

    /**
     * a more groovy findAndModify
     * @param query
     * @param fields
     * @param sort
     * @param remove
     * @param update
     * @param returnNew
     * @param upsert
     */
    Map findAndModify(Map query, Map fields, Map sort, boolean remove, Map update, boolean returnNew, boolean upsert) {
        dbCollection.findAndModify(
            query as BasicDBObject,
            fields as BasicDBObject,
            sort as BasicDBObject,
            remove,
            update as BasicDBObject,
            returnNew,
            upsert
        ) as Map
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

    /**
     * a more groovy remove
     */
    WriteResult remove(Map query) {
        dbCollection.remove(query as BasicDBObject)
    }

    /**
     * a more groovy remove
     */
    WriteResult removeAll() {
        remove([:])
    }

    /**
     * old school aggregate support
     * @param ops
     * @return
     */
    AggregationOutput aggregate(List<Map> ops) {
        dbCollection.aggregate(asBasicDBList(ops))
    }

    /**
     * old school aggregate support
     * @param ops
     * @param readPreference
     * @return
     */
    AggregationOutput aggregate(List<Map> ops, ReadPreference readPreference) {
        dbCollection.aggregate(asBasicDBList(ops), readPreference)
    }

    /**
     * old school aggregate support
     * @param ops
     * @param aggregationOptions
     * @return
     */
    Cursor aggregate(List<Map> ops, AggregationOptions aggregationOptions) {
        dbCollection.aggregate(asBasicDBList(ops), aggregationOptions)
    }

    /**
     * old school aggregate support
     * @param ops
     * @param aggregationOptions
     * @param readPreference
     * @return
     */
    Cursor aggregate(List<Map> ops, AggregationOptions aggregationOptions, ReadPreference readPreference) {
        dbCollection.aggregate(asBasicDBList(ops), aggregationOptions, readPreference)
    }

    /**
     * a more groovy aggregate
     * @param pipelineBuilderConfig
     * @return
     */
    Cursor aggregate(@DelegatesTo(AggregationPipelineBuilder) final Closure pipelineBuilderConfig) {
        AggregationPipelineBuilder aggregationPipelineBuilder = new AggregationPipelineBuilder()
        aggregationPipelineBuilder.with pipelineBuilderConfig
        aggregate(aggregationPipelineBuilder.pipelines, AggregationOptions.builder().build())
    }

    private static asBasicDBList(List<Map> mapList) {
        mapList.collect { it as BasicDBObject }
    }

}

