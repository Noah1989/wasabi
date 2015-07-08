package org.wasabi.deserializers

import java.util.HashMap

abstract public class Deserializer(vararg val mediaTypes: String) {
    open fun canDeserialize(mediaType: String): Boolean {
        for (supportedMediaType in mediaTypes) {
            if (mediaType.matches(supportedMediaType.toRegex())) {
                return true
            }
        }
        return false
    }
    abstract fun deserialize(input: Any): HashMap<String, Any>
}