package com.niit.recruiter.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.niit.recruiter.model.Resume;
import com.niit.recruiter.repository.ResumeRespository;

@Service
public class ResumeService {
	
	@Autowired
	private ResumeRespository resumeRepo;
	public Resume storeFile(MultipartFile file,Resume resume) {
        // Normalize file name
        String fileName;
       // Resume resumeFile=null;
        try {
        	fileName=StringUtils.cleanPath(file.getOriginalFilename());
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                System.out.println("file Name contains invalid character");
            }

            //resumeFile = new Resume(fileName, file.getContentType(), file.getBytes()); 
            resume.setFileName(fileName);
            resume.setFileType(file.getContentType());
            resume.setData(file.getBytes());
        } catch (Exception ex) {
            System.out.println("Error: Could Not Store File "+ex.getMessage());
        }
        return resumeRepo.save(resume);
    }

    public Resume getFile(int fileId) {
        return resumeRepo.findById(fileId).get();
    }
}
