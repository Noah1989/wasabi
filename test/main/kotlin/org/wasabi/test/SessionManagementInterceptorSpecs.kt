 package org.wasabi.test

import org.junit.Test as spec
 import kotlin.test.assertEquals
 import org.wasabi.interceptors.enableSessionSupport
 import org.junit.Ignore

 public class SessionManagementInterceptorSpecs: TestServerContext() {

    class CustomSession(val name: String) {

    }
    @Ignore("Session Management not implemented yet") @spec fun should_store_session_data_in_the_session_object() {

        TestServer.appServer.enableSessionSupport()
        TestServer.appServer.get("/set_session", {
            val session = request.session!!

            session.data = CustomSession("Joe")})

        get("http://localhost:${TestServer.definedPort}/set_session", hashMapOf())
        val response = get("http://localhost:${TestServer.definedPort}/get_session", hashMapOf())

        assertEquals("Joe", response.body)
    }

}
    