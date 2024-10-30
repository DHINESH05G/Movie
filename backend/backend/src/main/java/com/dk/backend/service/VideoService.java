package com.dk.backend.service;

@Service
public class VideoService {

    private final String VIDEO_DIR = "/path/to/your/video/files";

    public Resource getVideoResource(String videoName, HttpHeaders headers) {
        try {
            File videoFile = new File(VIDEO_DIR + "/" + videoName);
            Path videoPath = videoFile.toPath();
            return new UrlResource(videoPath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error retrieving video", e);
        }
    }
}
