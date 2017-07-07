package com.github.shipengyan.framework.util.id;

import com.github.shipengyan.framework.util.id.support.IdHexWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-07 13:27
 * @since 1.0
 */
@Slf4j
public class IdHexWorkerTest {

    @Test
    public void run() {
        final long idepo = System.currentTimeMillis() / 1000 - 3600;

        IdHexWorker worker = new IdHexWorker(1, 1, 0, idepo);

        for (int i = 0; i < 100000; i++) {
            System.out.println(worker.nextId());
        }

        System.out.println(worker);

        long nextId = worker.nextId();
        System.out.println(nextId);
        long time = worker.getIdTime(nextId);
        System.out.println(time + "->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time * 1000)));
    }
}
