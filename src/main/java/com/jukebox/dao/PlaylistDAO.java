package com.jukebox.dao;

import com.jukebox.bean.Playlist;
import com.jukebox.bean.PlaylistDetails;
import com.jukebox.bean.Song;
import com.jukebox.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistDAO {

    private static final Connection connect = DatabaseConnectionUtil.getConnection();
    static Scanner input = new Scanner(System.in);
    PlaylistDetailsDAO playList = new PlaylistDetailsDAO();
    PlaylistDetails playlistDetails = new PlaylistDetails();
    SongDAO songDAO = new SongDAO();

    // This method will check whether the Playlist exist or not and return a boolean value
    public boolean playlistExist(String name) throws SQLException {

        String sql = "select * from playlist where playlist_name ='" + name + "';";
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            System.out.println("Playlist already exist!");

            return false;
        } else {
            // if playlist doesn't exist.
            return true;
        }

    }

    public void createPlaylist(String name) throws SQLException {
        int row, choice3;

        String sql = "INSERT INTO PLAYLIST (PLAYLIST_NAME) Values (?)";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, name);
        row = statement.executeUpdate();
        if (row > 0) {
            System.out.println("PlayList created Successfully");
            System.out.println();

            do {
                System.out.println("Enter 1 to add song & 0 to exit");
                choice3 = input.nextInt();
                if (choice3 == 1) {
                    System.out.println("Enter the Song Id you wants to add : ");
                    List<Song> list = songDAO.getAllSongs();
                    songDAO.displaySongs(list);
                    playlistDetails.setSongId(input.nextInt());

                    System.out.println("Enter the PlayList id : ");
                    List<Playlist> l_Data = getAllDetails();
                    displayList(l_Data);
                    playlistDetails.setPlayListId(input.nextInt());

                    String action = playList.addSongInPlaylist(playlistDetails.getSongId(), playlistDetails.getPlayListId());
                    System.out.println(action);
                }
            } while (choice3 != 0);

        }

    }

    public String deletePlaylist(Playlist playlist) throws SQLException {
        int rows, row;
        String sql = "Delete from songInPlaylist where PlaylistId =" + playlist.getPlaylistId() + ";";
        Statement statement = connect.createStatement();
        rows = statement.executeUpdate(sql);
        if (rows > 0 || rows == 0) {  // if rows==0, means that there is no playlist available in songInPlaylist

            String sql1 = "Delete from playlist where Playlist_Id =" + playlist.getPlaylistId() + ";";
            Statement statement1 = connect.createStatement();
            row = statement1.executeUpdate(sql1);
            if (row > 0) {
                return "Playlist deleted!";
            }

        }
        return "playlist not deleted!";
    }

    // To get the details of the PlayList Table
    public List<Playlist> getAllDetails() throws SQLException {

        List<Playlist> list = new ArrayList<>();
        String sql = "SELECT * FROM PLAYLIST";
        //Statement object is used to execute SQL statement
        Statement statement = connect.createStatement();

        //ResultSet returns a table representing database.
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int playListId = resultSet.getInt(1);
            String playlistName = resultSet.getString(2);

            //Now Playlist object is calling the Parameterized Constructor and storing the values in the list
            Playlist playlist = new Playlist(playListId, playlistName);
            list.add(playlist);
        }
        return list;

    }

    public void displayList(List<Playlist> listDisplay) {
        System.out.println("+--------------------------+");
        System.out.format("|\t%-5s|\t%-15s|\t\n", "Id", "PlayList Name");
        System.out.println("+--------------------------+");
        for (Playlist list : listDisplay) {
            System.out.format("|\t%-5s|\t%-15s|\t\n", list.getPlaylistId(), list.getPlaylistName());
        }
        System.out.println("+--------------------------+");
    }
}

