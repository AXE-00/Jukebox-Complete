package com.jukebox.main;

import com.jukebox.bean.Song;
import com.jukebox.dao.SongDAO;
import com.jukebox.exception.ArtistNameNotFoundException;
import com.jukebox.exception.GenreNotFoundException;
import com.jukebox.exception.SongNotFoundException;
import com.jukebox.util.AudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JukeboxImpl {
    public static void main(String[] args) {


        int choice;

        String enter;
        boolean flag = false;
        Scanner input = new Scanner(System.in);


        do {
            System.out.print("To start the Jukebox Press Enter :  ");
            enter = input.nextLine();
            if (!enter.equalsIgnoreCase("")) {
                System.out.println("Press Enter to start");
            }
        } while (!enter.equalsIgnoreCase(""));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                    Starting JukeBox                                                     |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");


        do {

            SongDAO songDAO = new SongDAO();
            PlaylistOperation objectOfPO = new PlaylistOperation();

            try {
                System.out.println();

                System.out.println("""
                                 \s
                                   █░█░█ █▀▀ █░░ █▀▀ █▀█ █▀▄▀█ █▀▀   ▀█▀ █▀█   ░░█ █░█ █▄▀ █▀▀ █▄▄ █▀█ ▀▄▀
                                   ▀▄▀▄▀ ██▄ █▄▄ █▄▄ █▄█ █░▀░█ ██▄   ░█░ █▄█   █▄█ █▄█ █░█ ██▄ █▄█ █▄█ █░█           \
                        """);
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                                     SONG DETAILS                                                         ");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                List<Song> list = songDAO.getAllSongs();
                songDAO.displaySongs(list); //Displaying songs List
                System.out.println();
                System.out.println("1)   ▶️Play song");
                System.out.println("2)   \uD83D\uDCBEMy Library");
                System.out.println("3)   \uD83D\uDD0DSearch Song ");
                System.out.println("4)   \uD83D\uDEAAExit ");

                System.out.println();
                do {
                    System.out.print("Choose the Option : "); //Choose 1,2,3 to perform specific task.
                    choice = input.nextInt();                 //If option no. not matched then again asking the user to input choice.
                    if (choice > 4) {
                        System.out.println("Invalid Input!");
                    }
                } while (choice > 4);


                switch (choice) {

//  Case 1 for Audio Player or To Play song.

                    case 1 -> {
                        AudioPlayer audioPlayer = new AudioPlayer();
                        audioPlayer.choice(list); // choice here is a method inside audioPlayer
                        flag = true;
                    }

//  Case 2 to give Operation Options to User

                    case 2 -> {
                        objectOfPO.myLibrary(); //myLibrary is a method in Playlist operation
                        flag = true;
                    }

//  Case 3 to search

                    case 3 -> {
                        int choice5;
                        int press = 0;
                        boolean flag3;
                        do {

                            try {
                                do {
                                    System.out.println("Search song");
                                    System.out.println("Press 1)--> Search By Song Name");
                                    System.out.println("      2)--> Search By Artist Name");
                                    System.out.println("      3)--> Search By Genre");

                                    System.out.println();
                                    System.out.print("Enter the Category No. By which you wants to search : ");

                                    try {
                                        press = input.nextInt();
                                        System.out.println();
                                        if (press > 3) {
                                            System.out.println("Invalid input !");
                                            flag3 = true;
                                        } else {
                                            flag3 = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input. Please enter a number.");
                                        input.nextLine();
                                        flag3 = true;
                                    }
                                } while (flag3);

                                System.out.print("Enter the Name : ");
                                input.nextLine();
                                String name = input.nextLine();
                                List<Song> songData = songDAO.searchBy(press, name);
                                songDAO.displaySongs(songData);
                            } catch (ArtistNameNotFoundException | SongNotFoundException | GenreNotFoundException |
                                     InputMismatchException e) {
                                System.out.println(e);
                            }
                            System.out.println("Want to search again ? 1) Search  0) Exit");
                            choice5 = input.nextInt();

                        } while (choice5 == 1);
                        flag = true;
                    }
// Case 4 for exit
                    case 4 -> {
                        System.out.println("Thank You for using Jukebox, Visit again ✌️");
                        flag = false;
                    }
                    default -> System.out.println("Invalid Input!");
                }
            } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                System.out.println(e);
            }

        } while (flag);


    }
}


