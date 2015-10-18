import groovy.util.logging.Slf4j
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.server.StopEvent
import groovyx.gpars.agent.Agent

/*
 * Created by Chiaki Chikame on 10/18/15.
 *
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/txt/copying/ for more details.
 */

/**
 * Writer service, protected by Agent
 */
@Slf4j
class AgentWriterService implements Service {
    private Agent<Writer> sw
    private final String fileName = "/var/tmp/aws"

    @Override
    void onStart(StartEvent event) throws Exception {
        log.info 'start'
        sw = new Agent(new File(fileName).newWriter())
    }

    @Override
    void onStop(StopEvent event) throws Exception {
        log.info 'stop'
        sw { it.close() }
        sw.await()
        if (Checker.check(new File(fileName))) {
            log.info "File ${fileName} is well formed"
        } else {
            log.error "File ${fileName} is NOT well formed"
        }
        if (!new File(fileName).delete()) log.warn 'cannot delete file'
    }

    void write() {
        log.info 'write'
        sw { Writer w ->
            10.times {
                w.write("Lorem\n")
                w.flush()
                w.write("Curabitur\n")
                w.flush()
                w.write("Vitae\n")
                w.flush()
            }
        }
        // Since I don't need to know if the writing is successful,
        // I won't call sw.await() here.
        log.info 'write END'
    }
}
