package com.london.housing.controller;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smith
 */
@Controller
@RequestMapping("task")
public class WorkerController {

    @RequestMapping("/enqueue")
    public @ResponseBody String enqueue() {
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/install/initDB"));
        return "Task started";
    }

}
