package com.jukebox.dao;

import com.jukebox.bean.PlaylistDetails;
import com.jukebox.bean.Song;
import com.jukebox.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaylistDetailsDAO {

    private static final Connection connection = DatabaseConnectionUtil.getConnection();
    PlaylistDetails playlistDetails = new PlaylistDetails();

    public String addSongInPlaylist(int songId, int playId) throws SQLException {
        playlistDetails.setSongId(songId);
        playlistDetails.setPlayListId(playId);
        int rows;
        String sql = "insert into songInPlaylist(songId,playlistId) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, playlistDetails.getSongId());
        statement.setInt(2, playlistDetails.getPlayListId());
        rows = statement.executeUpdate();
        if (rows > 0) return "Added successfully! ";

        else return "Song not Added !";

    }

    // To check the song in song Playlist
    public List<Song> songsInPlaylist(List<Integer> list) throws SQLException {
        List<Song> list1 = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        String sql;
        while (iterator.hasNext()) {
            int id = iterator.next();
            sql = "SELECT * FROM songs WHERE song_id =" + id + ";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int songId = resultSet.getInt(1);
                String songName = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String genre = resultSet.getString(4);
                String duration = resultSet.getString(5);
                String fileP = resultSet.getString(6);
                Song song = new Song(songId, songName, artistName, genre, duration, fileP);
                list1.add(song);
            }
        }

        return list1;
    }

    // Here we are getting the Song id from the song In Playlist table.
    public List<Integer> grabSongIdFromDetails(int playId) throws SQLException {
        List<Integer> list = new ArrayList<>();
        int songId;

        String sql = "SELECT songId FROM songInPlaylist WHERE playlistId = " + playId + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            songId = resultSet.getInt(1);
            list.add(songId);
        }
        return list;
    }
}
