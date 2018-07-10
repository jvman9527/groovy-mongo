package github.jvman9527.groovy.mongo

import com.mongodb.BasicDBObject

class AggregationPipelineBuilder {

    List<Map> pipelines = []

    AggregationPipelineBuilder $match(Map query) {
        pipelines << ([$match: query] as BasicDBObject)
        this
    }

}
