import { Time } from "@angular/common";

export class Meetingsdto {

    id!:number;
    meetingLink!:string;
    meetingDate!:Date;
    meetingTime!:Time;
    meetingType!: string;
    sprintId!:number;
    meetingPassword!:string;
    createdOn!:Date;
    status!:string; 
    updatedOn!:Date;
    meetingPlatformId!:number;

}