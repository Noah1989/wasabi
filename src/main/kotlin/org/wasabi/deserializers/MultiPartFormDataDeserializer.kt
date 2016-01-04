package org.wasabi.deserializers

import io.netty.handler.codec.http.multipart.Attribute
import io.netty.handler.codec.http.multipart.FileUpload
import io.netty.handler.codec.http.multipart.InterfaceHttpData
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType
import java.util.HashMap

public class MultiPartFormDataDeserializer : Deserializer("application/x-www-form-urlencoded(;.*)?", "multipart/form-data(;.*)?") {

    // Not much more we can do here without uglyness, if its not a
    // List<InterfaceHttpData> we should let the exception bubble and
    // correctly return a 500 as something bad has happened...
    @Suppress("UNCHECKED_CAST")
    override fun deserialize(input: Any): HashMap<String, Any> {
        var bodyParams = HashMap<String, Any>()
        parseBodyParams(input as List<InterfaceHttpData>, bodyParams)
        return bodyParams
    }

    private fun parseBodyParams(httpDataList: List<InterfaceHttpData>, bodyParams: HashMap<String, Any>) {
        for(entry in httpDataList) {
            addBodyParam(entry, bodyParams)
        }
    }

    private fun addBodyParam(httpData: InterfaceHttpData, bodyParams: HashMap<String, Any>) {
        // TODO: Add support for other types of attributes (namely file)
        when (httpData.getHttpDataType()) {
            HttpDataType.Attribute -> {
                val attribute = httpData as Attribute
                bodyParams[attribute.getName().toString()] = attribute.getValue().toString()
            }
            HttpDataType.FileUpload -> {
                val upload = httpData as FileUpload
                bodyParams[upload.getName().toString()] = upload.getString().toString()
            }
        }
    }


}