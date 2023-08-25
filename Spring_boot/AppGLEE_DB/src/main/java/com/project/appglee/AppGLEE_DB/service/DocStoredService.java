package com.project.appglee.AppGLEE_DB.service;


import com.project.appglee.AppGLEE_DB.entity.DocStored;
import com.project.appglee.AppGLEE_DB.repository.DocStoredRepository;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@Service
@Transactional
public class DocStoredService {
    private DocStoredRepository repo;
    private FileStorageService storageService;

    public DocStoredService(DocStoredRepository repo, FileStorageService storageService) {
        this.repo = repo;
        this.storageService = storageService;
    }

    public GenericResponse<Iterable<DocStored>> list() {
        return new GenericResponse<Iterable<DocStored>>(TYPE_RESULT, RESP_OK, CORRECT_OPERATION, repo.list());
    }


    public GenericResponse find(Long aLong) {
        return null;
    }


    public GenericResponse save(DocStored obj) {
        String fileName = (repo.findById(obj.getId())).orElse(new DocStored()).getFileName();

        String originalFilename = obj.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        fileName = storageService.storeFile(obj.getFile(), fileName);

        obj.setFileName(fileName);
        obj.setExtension(extension);

        return new GenericResponse(TYPE_DATA, RESP_OK,CORRECT_OPERATION,repo.save(obj));
    }

    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {
        Resource resource = storageService.loadResource(completefileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocStored doc = repo.findByFileName(fileName).orElse(new DocStored());
        return download(doc.getCompleteFileName(), request);
    }


    public GenericResponse delete(Long aLong) {
        return null;
    }

    public HashMap<String, Object> validate(DocStored obj) {
        return null;
    }
}
