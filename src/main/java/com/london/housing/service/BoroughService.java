package com.london.housing.service;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.london.housing.entity.Borough;
import com.london.housing.repository.BoroughRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author KuznetsovNE/ 20.05.2015.
 */
@Service
public class BoroughService implements Serializable {

    @Autowired
    private BoroughRepository boroughRepository;

    public void saveBoroughs(List<Borough> boroughs) {
        for (Borough borough : boroughs) {
            Queue queue = QueueFactory.getDefaultQueue();
            queue.add(TaskOptions.Builder.withPayload(new Work(borough))
                    .etaMillis(System.currentTimeMillis() + 5000));
        }
    }

    private class Work implements DeferredTask {

        private Borough borough;

        Work(Borough borough) {
            this.borough = borough;
        }

        @Override
        public void run() {
            System.out.println("Start Saving borough: " + borough.getName());
            boroughRepository.save(borough);
        }
    }
}
