package com.augmentum.hrrs.parser.fileparser.async;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ResumeInfoQueue {

    /**
     * key => candidateId
     * value => resumeAbsolutePath
     */
    public class ResumeInfo {
        public String candidateId;
        public String resumeAbsolutePath;

        ResumeInfo(String candidateId, String resumeAbsolutePath) {
            this.candidateId = candidateId;
            this.resumeAbsolutePath = resumeAbsolutePath;
        }
    }

    private static ConcurrentLinkedQueue<ResumeInfo> queue = new ConcurrentLinkedQueue<ResumeInfo>();

    public static void add(ResumeInfo object) {
        queue.offer(object);
    }

    public static ConcurrentLinkedQueue<ResumeInfo> get() {
        return queue;
    }
}
