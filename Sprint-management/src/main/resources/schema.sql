create table Sprints(Sprint_Id int primary key auto_increment,Sprint_Name varchar(20),
Start_Date date,End_Date date check (End_Date > Start_Date),Project_Code int,Created_On date);

create table Meeting_Platforms(Id int primary key,Name varchar(50));

create table Meetings(Id int primary key auto_increment ,Meeting_Link varchar(200),
Meeting_Date date,Meeting_Time time,Meeting_Type varchar(20) ,Sprint_Id int,
Meeting_Password varchar(20),Created_On date,Status varchar(20) ,Updated_On date,
Meeting_Platform_Id int,foreign key (Meeting_Platform_Id) references Meeting_Platforms(Id),
foreign key (Sprint_Id) references Sprints(Sprint_Id));	

create table users(
user_name varchar(40),
password varchar(40) not null,
role varchar(40) not null
);	