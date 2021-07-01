package com.google;

import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private List<Video> playlist;
    private String name;

    VideoPlaylist (String name) {
        this.name = name;
    }

    // GETTERS
    public List<Video> getPlaylist() {
        return playlist;
    }

    public String getName() {
        return name;
    }

    // METHODS
    public void addVideo(Video video) {
        playlist.add(video);
    }

    public List<Video> getVideos() {
        return playlist;
    }
}

