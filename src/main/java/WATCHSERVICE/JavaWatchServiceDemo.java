package WATCHSERVICE;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by SCAL on 2017/1/25.
 */
public class JavaWatchServiceDemo {
    private static final Logger logger = LogManager.getLogger(JavaWatchServiceDemo.class);
    private Path path = null;
    private WatchService watchService = null;

    private void initToMonitor(String pathToMonitor) {
        path = Paths.get(pathToMonitor);
        try {
            watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            logger.info("now has initialized and the watch service has started!");
            logger.info("watching......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getChangeInfo() {
        WatchKey key = null;
        while (true) {
            try {
                key = watchService.take();
                for (WatchEvent event : key.pollEvents()) {
                    WatchEvent.Kind kind = event.kind();
                    logger.info("Event on " + event.context().toString() + " is " + kind);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            boolean reset = key.reset();
            if (!reset) {
                logger.info("the service is breaking!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        String pathToMonitor = "D:\\QQ消息\\file-in-cloud";
        JavaWatchServiceDemo javaWatchServiceDemo = new JavaWatchServiceDemo();
        javaWatchServiceDemo.initToMonitor(pathToMonitor);
        javaWatchServiceDemo.getChangeInfo();

    }
}
