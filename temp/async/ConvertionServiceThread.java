package com.augmentum.hrrs.parser.fileparser.async;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

//import javax.annotation.PostConstruct;



import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augmentum.hrrs.core.Exp;
import com.augmentum.hrrs.entity.po.candidate.Candidate;
import com.augmentum.hrrs.entity.po.candidate.Resume;
import com.augmentum.hrrs.entity.po.candidate.ResumeContent;
import com.augmentum.hrrs.parser.fileparser.async.ResumeInfoQueue;
import com.augmentum.hrrs.service.CommonService;
import com.augmentum.hrrs.util.StringUtil;

@Component
public class ConvertionServiceThread extends Thread {

    private static final Log log = LogFactory.getLog(ConvertionServiceThread.class);

    private CallExternalProgramSemaphore callExternalProgramSemaphore;
    public static ConvertionServiceThread convertionServiceThread;

    @Autowired
    private CommonService commonService;

    @PostConstruct
    public void init() {  
        convertionServiceThread = this;  
    }

    public ConvertionServiceThread(String name, CallExternalProgramSemaphore callExternalProgramSemaphore) {
        super();
        this.setName(name);
        this.callExternalProgramSemaphore = callExternalProgramSemaphore;
    }

    @Override
    public void run() {
        try {
            ConcurrentLinkedQueue<ResumeInfoQueue.ResumeInfo> queue = ResumeInfoQueue.get();
            
            List<String> inputFilePaths = new ArrayList<String>();
            List<String> candidateIds = new ArrayList<String>();
            List<String> outputFilePaths = new ArrayList<String>();
            List<String> tempCandidateIds = new ArrayList<String>();

            int i = 0;
            while(!queue.isEmpty()) {
                ResumeInfoQueue.ResumeInfo resumeInfo = queue.poll();
                candidateIds.add(resumeInfo.candidateId);
                tempCandidateIds.add(resumeInfo.candidateId);
                inputFilePaths.add(resumeInfo.resumeAbsolutePath);

                i++;
                if (i >= 10) {
                    List<String> tempOutputFilePaths = this.callExternalProgramSemaphore.toConvert(inputFilePaths, tempCandidateIds);
                    outputFilePaths.addAll(tempOutputFilePaths);

                    i = 0;
                    inputFilePaths.clear();
                    tempCandidateIds.clear();
                }
            }

            if (i > 0) {
                List<String> tempOutputFilePaths = this.callExternalProgramSemaphore.toConvert(inputFilePaths, tempCandidateIds);
                outputFilePaths.addAll(tempOutputFilePaths);
            }

            List<String> showHtmls = new ArrayList<String>();
            for (int j = 0; j < outputFilePaths.size(); j++) {
                String outputFilePath = outputFilePaths.get(j);
                String candidateId = outputFilePath.substring(outputFilePath.lastIndexOf(File.separator)).split("_")[0];
                String showHtml = null;

                if (candidateIds.contains(candidateId)) {
                    candidateIds.remove(candidateId);
                    showHtml = StringUtil.assembleHtml4AccessPath(outputFilePath);
                }
                showHtmls.add(showHtml);
            }

            saveAll(candidateIds.toArray(new String[candidateIds.size()]), showHtmls);
        } catch (InterruptedException e) {
            log.warn("文件转换失败，InterruptedException, message: " + e.getMessage());
        }
    }

    public void saveAll(String[] candidateId, List<String> showHtmls) {
        Exp exp = new Exp(Resume.class);
        exp.in("id", candidateId, Exp.IN);
        List<Candidate> candidates = commonService.find(exp);  //convertionServiceThread.

        List<Resume> updateList = new ArrayList<Resume>();
        for (int i = 0; i < candidates.size(); i++) {
            Resume mainResume = candidates.get(i).getMainResume();
            Resume resume = candidates.get(i).getResumes().iterator().next();
            ResumeContent resumeContent = mainResume.getResumeContent();

            String showHtml = showHtmls.get(i);
            if (showHtml != null) {
                resumeContent.setContent(showHtml.getBytes());
            } else {
                resumeContent.setContent(null);
            }

            mainResume.setResumeContent(resumeContent);
            resume.setResumeContent(resumeContent);

            updateList.add(mainResume);
            updateList.add(resume);
        }

        commonService.saveOrUpdate(updateList);//convertionServiceThread.
    }
}
