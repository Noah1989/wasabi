package org.wasabi.test

import org.junit.Test as spec
import org.wasabi.serializers.JsonSerializer
import kotlin.test.assertEquals

public class SerializerSpecs {

    @spec fun canSerialize_should_return_true_when_given_a_media_type_that_serializer_can_process() {

        val jsonSerializer = JsonSerializer()

        assertEquals(true, jsonSerializer.canSerialize("application/json"))
        assertEquals(true, jsonSerializer.canSerialize("application/vnd.wasabi+json"))
        assertEquals(false, jsonSerializer.canSerialize("application/vnd.wasabi+xml"))

    }

}
    