create database JukeBox1;
use jukebox1;

create table Songs(
Song_Id int auto_increment primary key,
Song_name varchar(30) not null,
Artist_Name varchar(30) not null,
Gener varchar(20),
Duration varchar(6),
FilePath varchar(100) not null);

insert into Songs(Song_name, Artist_Name, Gener,Duration,FilePath)
 values('Scam 1992','Achnit Thakkar','Indian Pop','00:30',"src/main/resources/Scam 1992 Ringtone.wav"),
	   ('Blue Bird','Ikimono-gakari','Japanese Rock',"03:33","src/main/resources/blue-Bird-naruto.wav"),
       ('The Nights',"Avicii","Folktronica","02:53","src/main/resources/Avicii-The-Nights.wav"),
       ("Deva Deva",'Arijit Singh','Indian Pop','04:39',"src/main/resources/Deva Deva.wav"),
	   ('Phir se Ud Chala','Mohit Chauhan','Indian Pop','04:36',"src/main/resources/Phir-Se-Ud-Chala.wav"),
       ('Marali Mareyagi','Sadhna Sangam','Kannada Romantic','04:40',"src/main/resources/METAMORPHOSIS (Sped Up).wav"),
       ('Metamorphosis','Interworld','Sigma','01:50',"src/main/resources/METAMORPHOSIS (Sped Up).wav"),
       ('Free Tibet','Vini Vici RMX','Trans','04:55',"src/main/resources/Free Tibet(Vini Vici RMX)(WeedSong).wav"),
	   ('Do ya like X Resonance','Lucentix','Remix','02:45',"src/main/resources/do ya like x resonance __ sped up.wav"),
       ('Kalesh Chori','DG IMMORTALS','Hindi Rap','03:37',"src/main/resources/KALESHI CHORI.wav"),
       ('Sleeping on the blacktop','Colter Wall','CountrySide','03:12',"src/main/resources/Sleeping on the Blacktop.wav"),
       ('Undiporaadhey','sid sriram','Telugu Romatic','02:53',"src/main/resources/Undiporaadhey - SenSongsMp3.Co.wav"),
       ('Unravel','TK','Japanese pop','03:57',"src/main/resources/Unravel (Tokyo Ghoul).wav");
       
      
       
       
create table Playlist(
Playlist_Id int auto_increment primary key,
Playlist_Name varchar(25));

create table SongInPlaylist (
SongId int,foreign key (SongId) references Songs (Song_Id),
PlayListId int, foreign key (PlayListId) references Playlist (Playlist_Id));
select * from songs;


delete from songs;
drop table songs;
       
      
       