package prointern.ProinternApplication.YoutubeFetcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.YoutubeFetcher.Model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
}