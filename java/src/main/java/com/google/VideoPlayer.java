package com.google;

import java.util.*;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  public static Video videoNowPlaying = null;
  private static boolean videoPaused = false;
  private static List<VideoPlaylist> playlists = new ArrayList<>();
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public String formatVideoInfo(Video video) {
    String title = video.getTitle();
    String id = video.getVideoId();
    List<String> tags = video.getTags();
    String tagsString = String.join(" ", tags);

    return String.format("%s (%s) [%s]", title, id, tagsString);
  }

  public void showAllVideos() {
    System.out.printf("Here's a list of all available videos:\n");
    List<Video> videos = videoLibrary.getVideos();
    Collections.sort(videos, Comparator.comparing(Video::getTitle));
    for (Video video : videos) {
      System.out.println(formatVideoInfo(video));
    }
  }

  public void playVideo(String videoId) {
    videoPaused = false;
    if (videoLibrary.getVideo(videoId) == null) {
      System.out.println("Cannot play video: Video does not exist");
    }
    else {
      if (videoNowPlaying != null) {
        System.out.println(String.format("Stopping video: %s", videoNowPlaying.getTitle()));
      }
      videoNowPlaying = videoLibrary.getVideo(videoId);
      System.out.println(String.format("Playing video: %s", videoNowPlaying.getTitle()));
    }
  }

  public void stopVideo() {
    videoPaused = false;
    if (videoNowPlaying == null) {
      System.out.println("Cannot stop video: No video is currently playing");
    }
    else {
      System.out.println(String.format("Stopping video: %s", videoNowPlaying.getTitle()));
      videoNowPlaying = null;
    }
  }

  public void playRandomVideo() {
    List<Video> videos = videoLibrary.getVideos();
    if (videos.isEmpty()) {
      System.out.println("No videos available");
    }
    else {
      // Get random video from library
      Random random = new Random();
      int index = random.nextInt(videos.size());
      playVideo(videos.get(index).getVideoId());
    }
  }

  public void pauseVideo() {
    if (videoPaused) {
      System.out.println(String.format("Video already paused: %s", videoNowPlaying.getTitle()));
    }
    else {
      if (videoNowPlaying == null) {
        System.out.println("Cannot pause video: No video is currently playing");
      }
      else {
        System.out.println(String.format("Pausing video: %s", videoNowPlaying.getTitle()));
        videoPaused = true;
      }
    }
  }

  public void continueVideo() {
    if (videoNowPlaying == null) {
      System.out.println("Cannot continue video: No video is currently playing");
    }
    else {
      if (!videoPaused) {
        System.out.println("Cannot continue video: Video is not paused");
      }
      else {
        videoPaused = false;
        System.out.println(String.format("Continuing video: %s", videoNowPlaying.getTitle()));
      }
    }
  }

  public void showPlaying() {
    if (videoNowPlaying == null) {
      System.out.println("No video is currently playing");
    }
    else {
      if (videoPaused) {
        System.out.println(String.format("Currently playing: %s - PAUSED", formatVideoInfo(videoNowPlaying)));
      }
      else {
        System.out.println(String.format("Currently playing: %s", formatVideoInfo(videoNowPlaying)));
      }
    }
  }

  public void createPlaylist(String playlistName) {
    if (playlists.isEmpty()) {
      if (playlists.contains(playlistName)) {
        System.out.println("Cannot create playlist: A playlist with the same name already exists");
      }
      else {
        playlists.add(new VideoPlaylist(playlistName));
        System.out.println(String.format("Successfully created playlist: %s", playlistName));
      }
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    // Check if playlist exists
    if (!playlists.contains(playlistName)) {
      System.out.println(String.format("Cannot add video to %s: Playlist does not exist", playlistName));
    }
    // Check if video exists
    if (!videoLibrary.getVideos().contains(videoId)) {
      System.out.println(String.format("Cannot add video to %s: Video does not exist", playlistName));
    }
    else {
      Video video = videoLibrary.getVideo(videoId);
      for (VideoPlaylist playlist: playlists) {
        if (playlist.getName().toLowerCase() == playlistName.toLowerCase()) {

          // Check if video exists in playlist
          List<Video> videos = playlist.getPlaylist();
          if (videos.contains(video)) {
            System.out.println(String.format("Cannot add video to %s: Video already added", playlistName));
          }
          else {
            playlist.addVideo(video);
            System.out.println(String.format("Added video to %s: %s", playlistName, video.getTitle()));
          }
        }
      }
    }
  }

  public void showAllPlaylists() {
    if (playlists.isEmpty()) {
      System.out.println("No playlists exist yet");
    }
    else {
      for (VideoPlaylist playlist : playlists) {
        System.out.println(playlist.getName());
      }
    }
  }

  public void showPlaylist(String playlistName) {
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}