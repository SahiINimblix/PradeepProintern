package prointern.ProinternApplication.YoutubeFetcher.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.YoutubeFetcher.Model.Video;
import prointern.ProinternApplication.YoutubeFetcher.Repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
    	List<Video> listOfVideos = videoRepository.findAll();
    	if(listOfVideos==null) throw new DetailsNotFoundException("No videos availbale right now.");
    	return listOfVideos;
    }

    public String saveVideo(Video video) {
        Video video1 = videoRepository.save(video);
        if(video1 == null) throw new OperationFailedException("Unable to save.");
        return "Video saved successfully";
    }
}