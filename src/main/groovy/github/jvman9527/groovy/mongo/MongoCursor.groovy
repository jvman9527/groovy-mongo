package github.jvman9527.groovy.mongo

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor

/**
 * MongoDB driver DBCursor extension
 */
class MongoCursor {

    @Delegate
    DBCursor dbCursor

    public MongoCursor(DBCursor dbCursor) {
        this.dbCursor = dbCursor
    }

    /**
     * a more groovy sort
     * @param fields
     */
    public MongoCursor sort(Map fields) {
        dbCursor.sort(fields as BasicDBObject)
        this
    }

}
