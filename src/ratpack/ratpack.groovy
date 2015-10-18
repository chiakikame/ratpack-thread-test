/*
 * Created by Chiaki Chikame on 10/18/15.
 *
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/txt/copying/ for more details.
 */

import static ratpack.groovy.Groovy.*;
import ratpack.exec.Blocking;

ratpack {
    bindings {
        bindInstance(WriterService, new WriterService())
        bindInstance(SyncWriterService, new SyncWriterService())
        bindInstance(AgentWriterService, new AgentWriterService())
    }

    serverConfig {
        port(8080)
    }

    handlers {
        all {
            Blocking.get {
                registry.get(SyncWriterService).write()
            }.then {}
            Blocking.get {
                registry.get(AgentWriterService).write()
            }.then {}
            Blocking.get {
                registry.get(WriterService).write()
            }.then {}
            response.status(200)
        }
    }
}