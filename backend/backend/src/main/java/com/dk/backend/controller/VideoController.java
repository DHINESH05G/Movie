package com.dk.backend.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/{videoName}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String videoName, @RequestHeader HttpHeaders headers) {
        Resource video = videoService.getVideoResource(videoName, headers);
        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }
}

