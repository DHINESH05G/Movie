package com.dk.backend.service;

import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;

@Service
public class VideoService {

    private final String VIDEO_DIR = "/path/to/your/video/files";

    public Resource getVideoResource(String videoName, HttpHeaders headers) {
        try {
            File videoFile = new File(VIDEO_DIR + "/" + videoName);
            Path videoPath = videoFile.toPath();
            return (Resource) new UrlResource(videoPath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error retrieving video", e);
        }
    }
}
