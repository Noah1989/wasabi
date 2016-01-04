package org.wasabi.routing

import org.wasabi.http.Request
import org.wasabi.http.Response
import io.netty.handler.codec.http.HttpMethod
import java.util.HashMap


public class Route(val path: String, val method: HttpMethod, val params: HashMap<String, String>, vararg val handler: RouteHandler.() -> Unit)

