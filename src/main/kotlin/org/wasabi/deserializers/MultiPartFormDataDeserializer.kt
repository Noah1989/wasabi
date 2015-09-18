package org.wasabi.deserializers

import io.netty.handler.codec.http.multipart.Attribute
import io.netty.handler.codec.http.multipart.FileUpload
import io.netty.handler.codec.http.multipart.InterfaceHttpData
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType
import java.util.HashMap

public class MultiPartFormDataDeserializer : Deserializer("application/x-www-form-urlencoded(;.*)?", "multipart/form-data(;.*)?") {
    val bodyParams = HashMap<String, Any>()

    override fun deserialize(input: Any): HashMap<String, Any> {
        parseBodyParams(input as List<InterfaceHttpData>)
        return bodyParams
    }

    private fun parseBodyParams(httpDataList: List<InterfaceHttpData>) {
        for (entry in httpDataList) {
            addBodyParam(entry)
        }
    }

    private fun addBodyParam(httpData: InterfaceHttpData) {
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