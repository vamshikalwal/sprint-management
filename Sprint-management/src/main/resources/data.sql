insert into Sprints (Sprint_Name,Start_Date,End_Date,Project_Code,Created_On) 
values ('Sprint-01','2024-04-30','2024-05-10',1,'2024-04-30');
insert into Meeting_Platforms values (1,'Google Meet'),(2,'Microsoft Teams'),(3,'Zoom'),(4,'Skype');

insert into users values('vamshikalwal@gmail.com','vamshivam@1','admin');
insert into users values('admin2@cognizant.com','admin123','admin');

insert into Meetings (Meeting_Link,Meeting_Date,Meeting_Time,Meeting_Type,
Sprint_Id,Meeting_Password,Created_On,Status,Updated_On,Meeting_Platform_Id) values 
('https://meeting1','2024-04-30','00:20:00','SprintPlanning',1,'meeting1@1','2024-04-30','Completed',null,1);