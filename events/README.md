## event module

this is a basic module created just for a place to fetch and identify all the kafka events used in the university managemnet system

## set of events
 course enroll event-produced by course-service consumed by student-service
  department event -produced by department-service consumed by faculty-service
  Faculty created event -produced by Faculty-service consumed by email-service
  Id card event -produced by student-service consumed by email-service
  leave event -produced by attendance-service consumed by email-service
  low attendnace event -produced by attendance-service consumed by email-service
  payslipgenertaed event -produced by faculty-service consumed by email-service
  Register request event -produced by auth-service consumed by faculty-service,student-service
  send scholarship event -produced by student-service conumed by email-service
  studentcreated event -produced by student-service consumed by email-service
