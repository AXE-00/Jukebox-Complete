package com.jukebox.dao;

import com.jukebox.bean.Songs;
import com.jukebox.exception.ArtistNameNotFoundException;
import com.jukebox.exception.GenreNotFoundException;
import com.jukebox.exception.SongNotFoundException;
import com.jukebox.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SongDAO {
    static Scanner input = new Scanner(System.in);

    public static Connection connection = DatabaseConnectionUtil.getConnection();


    // Getting all the songs present in the database
    public List<Songs> getAllSongs() throws SQLException {

        List<Songs>songsData = new ArrayList<>();
        String sql = "SELECT * FROM songs";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int song_Id = resultSet.getInt(1);
            String song_name = resultSet.getString(2);
            String artist_Name = resultSet.getString(3);
            String genre = resultSet.getString(4);
            String duration = resultSet.getString(5);
            String filePath = resultSet.getString(6);
            Songs songs = new Songs(song_Id, song_name, artist_Name, genre, duration, filePath);
            songsData.add(songs);
        }

        return songsData;
    }

    // Display those songs using this display method.
    public void displaySongs(List<Songs> list){
        System.out.println("+---------------------------------------------------------------------------------------------+");

        System.out.format("|\t%-5s|\t%-30s|\t%-17s|\t%-17s|\t%-10s|\n", "id", "Title", "Artist", "Genre", "Duration");

        System.out.println("+---------------------------------------------------------------------------------------------+");

        for (Songs song : list) {
            System.out.format("|\t%-5d|\t%-30s|\t%-17s|\t%-17s|\t%-10s|\n", song.getSongId(), song.getSongName(),
                    song.getArtistName(), song.getGenre(), song.getDuration());
        }
        System.out.println("+---------------------------------------------------------------------------------------------+");
    }


    // Search by song name, Artist Name, and Genre

    public List<Songs> searchBy(int press, String name) throws SQLException, ArtistNameNotFoundException,SongNotFoundException,GenreNotFoundException {

        List<Songs> searchList = new ArrayList<>();

        String val = "";
        switch (press) {
            case 1:
                val = "song_name";
                break;
            case 2:
                val = "Artist_Name";
                break;
            case 3:
                val = "Gener";
                break;
            default:
                System.out.println("invalid input");
        }
        String sql = "SELECT * FROM songs WHERE " + val + " LIKE '%" + name + "%';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int song_Id = resultSet.getInt(1);
            String song_name = resultSet.getString(2);
            String artist_Name = resultSet.getString(3);
            String genre = resultSet.getString(4);
            String duration = resultSet.getString(5);
            String filePath = resultSet.getString(6);
            Songs songs = new Songs(song_Id, song_name, artist_Name, genre, duration, filePath);
            searchList.add(songs);
        }
        if (searchList.size() == 0 && press == 1) {
            throw new SongNotFoundException("Song not Found");
        }
        if (searchList.size() == 0 && press == 2) {
            throw new ArtistNameNotFoundException("Artist not Found");
        }
        if (searchList.size() == 0 && press == 3) {
            throw new GenreNotFoundException("Genre not Found");
        }

        return searchList;
    }

    public String songToPlay() throws SQLException{
        String filepath = "";
        System.out.print("Enter the id of the song you wants to Play : ");
        int id = input.nextInt();
        String sql="SELECT filepath FROM SONGS WHERE SONG_ID = "+id+";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            filepath = resultSet.getString(1);
        }
        return filepath;

    }
}
