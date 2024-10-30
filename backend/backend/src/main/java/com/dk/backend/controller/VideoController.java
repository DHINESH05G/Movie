package com.dk.backend.controller;

import com.dk.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/{videoName}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String videoName, @RequestHeader HttpHeaders headers) throws IOException {
        Resource video = (Resource) videoService.getVideoResource(videoName, headers);
        Path videoPath = Paths.get(video.getURI());
        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType((Resource) videoPath).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }
}