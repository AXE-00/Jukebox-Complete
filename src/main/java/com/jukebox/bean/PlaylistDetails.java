package com.jukebox.bean;

public class PlaylistDetails {

    private int songId;
    private int playListId;

    public PlaylistDetails() {

    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getPlayListId() {
        return playListId;
    }

    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }

    @Override
    public String toString() {
        return "PlaylistDetails{" +
                "song_Id=" + songId +
                ", playList_Id=" + playListId +
                '}';
    }
}
