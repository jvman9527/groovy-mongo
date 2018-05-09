package github.jvman9527.groovy.mongo

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor

/**
 * MongoDB driver DBCursor extension
 */
class MongoCursor {

    @Delegate
    DBCursor dbCursor

    MongoCursor(DBCursor dbCursor) {
        this.dbCursor = dbCursor
    }

    /**
     * a more groovy sort
     * @param fields
     */
    MongoCursor sort(Map fields) {
        dbCursor.sort(fields as BasicDBObject)
        this
    }

    /**
     * a more groovy limit
     * @param limit
     */
    MongoCursor limit(int limit) {
        dbCursor.limit(limit)
        this
    }

}

