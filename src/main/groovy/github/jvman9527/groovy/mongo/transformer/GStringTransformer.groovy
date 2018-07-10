package github.jvman9527.groovy.mongo.transformer

import org.bson.Transformer

class GStringTransformer implements Transformer {

    @Override
    Object transform(Object objectToTransform) {
        (objectToTransform as GString).toString()
    }

}
