import groovy.util.logging.Slf4j
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.server.StopEvent

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
 * Writer service, no protection (i.e. not thread safe)
 */
@Slf4j
class WriterService implements Service {
    private Writer sw
    private final String fileName = "/var/tmp/ws"

    @Override
    void onStart(StartEvent event) throws Exception {
        log.info 'start'
        sw = new File(fileName).newWriter()
    }

    @Override
    void onStop(StopEvent event) throws Exception {
        log.info 'stop'
        sw.close()
        if (Checker.check(new File(fileName))) {
            log.info "File ${fileName} is well formed"
        } else {
            log.error "File ${fileName} is NOT well formed"
        }
        if (!new File(fileName).delete()) log.warn 'cannot delete file'
    }

    void write() {
        log.info 'write'
        10.times {
            sw.write("Lorem\n")
            sw.flush()
            sw.write("Curabitur\n")
            sw.flush()
            sw.write("Vitae\n")
            sw.flush()
        }
        log.info 'write END'
    }
}
