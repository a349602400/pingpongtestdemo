package com.mytest.pongservice.factory;


import com.mytest.pongservice.listener.FileListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.monitor.Monitor;
import java.io.File;


@Slf4j
@Component
public class FileListenerFactory {

    @Autowired
    FileListener fileListener;

    /**
     * Get a thread listening to the directory
     *
     * @return Execution thread
     */
    public FileAlterationMonitor getMonitor() {
        //Create file filter
        //Prefix filter
        IOFileFilter prefixFileFilter = FileFilterUtils.prefixFileFilter("hello");
        // Suffix filter
        IOFileFilter suffixFileFilter = FileFilterUtils.suffixFileFilter(".txt");
        // Both are satisfied with one
        IOFileFilter filter = FileFilterUtils.or(prefixFileFilter, suffixFileFilter);
        // Assemble the filter
        FileAlterationObserver observer = new FileAlterationObserver(new File("D:\\test"), filter);
        //  Add listener to listener
        observer.addListener(fileListener);
        // Return listener
        return new FileAlterationMonitor(10, observer);//Control polling time
    }


}

