package org.wasabi.test

import org.junit.Test as spec
import org.wasabi.test.TestServer
import org.wasabi.test.get
import kotlin.test.assertEquals
import org.wasabi.http.CacheControl

public class CachingSpecs: TestServerContext() {

    spec fun setting_cache_control_should_set_cache_control_header_in_response() {

        TestServer.appServer.get("/cachePolicy",{
            response.cacheControl = "no-cache"
            response.send("no-cache")
        } )

        val response = get("http://localhost:${TestServer.definedPort}/cachePolicy", hashMapOf())

        val cacheControlHeader = response.headers.firstOrNull({ it.getName() == "Cache-Control" })?.getValue()

        assertEquals("no-cache", cacheControlHeader)




    }

}