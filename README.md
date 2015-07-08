GamedayPlanner
==============

Final Project for Fundamentals of Mobile Application Development 

This branch contains the project as I submitted it, and contains incomplete features. I have decided to downscale the project to only include the functions included with Parking Screen (activity_parking) below. I feel the other functions aren't necessary, and that it would be more useful for the user to have an app which simply allows them to select their parking lot and be navigated to it. I have recently began workign on this and it can be found in the master branch

APP Details:
Home Screen (acivity_home_screen):
  "My Tickets" Button: User clicks this button and is sent to activity_my_tickets
  "Purchase Tickets" Button: User clicks this and broswer is launcher where they can purchase tickets to Rangers games
  
My Tickets (activity_my_tickets):
  "Launch Gallery" Button: User clicks this to launch gallery. I wanted to set it up so the user could then select an image from the gallery and ticket would appear in my app, but I couldn't get it working. In order to make the ticket appear, the user must go to their gallery, and "share" an image with my app. From there, they are brought back to this screen and can click their ticket to proceed. As of now, the user must do this in order to continue. After clicking their ticket, they are sent to activity_parking
  
Parking Screen (activity_parking):
  User can interact with the interface to select the lot they wish to park in. The lists displayed in the "Lot" spinner are determined by the type of lot selected. If the user selects that they wish to tailgate, they can enter the amount of time desired. My intentions were to add this time to the amount of time it would take them to walk from the parking lot to the stadium to get "time outside stadium". The app would then add this to the travel time in order to tell the user the time they would need to leave for the stadium to get their desired amount of taigating in before the game starts. I didn't fully finish this feature.
  
  "Let's Go" button: launches navigation and takes user to selected lot
  
  "Car Finder" button: I intended this button to be used to save the useres gps location, and they could navigate back to this location after the game to help them find their car. I did nto implement this feature. 
  
  "View Ticket" Button: Sends user to activity_view_ticket 
  
View Ticket (activity view ticket): I had hoped to use this to allow users to show their ticket on a phone to enter the stadium. I don't believe Rangers Stadium allows this yet, though. 
  
