# Non-functional Requirements

## Non-functional requirement 1: Backup
Our system will have records about
1. Expiration dates and quantities of pantry items.
2. Weekly grocery lists
3. Recipes
4. Meal plans
The way all these records interact to solve the user's problems means our system has a database. This does not imply a 
relational SQL database. But to assure the records are not tightly coupled to a particular UI or architecture it should 
be possible to import/export the records. Supporting backup/restore operations can make our application RESTful so people
can use it from anywhere.

### Other Nonfunctional Requirements Provided by a Backup Feature 
Adding backup functionality also supports nonfunctional requirements for
* Disaster recovery
* Resilience
* Configuration management

### How Our System Complies with Backup Requirement
Our system will have a Backup/Restore feature accessible from the Main Menu and the Navigation Bar.
1. Clicking the Backup/Restore button on either the Main Menu or Navigation Bar will bring the Backup/Restore menu to the foreground.
2. The Backup/Restore Pane will have controls, records, and a messages pane.
3. Controls
   1. Button to launch the backup process.
      1. If the backup is successful control returns to the Backup/Restore Pane. A reporting the success will be above the list of backups. The backup will show at the top of the stack.
      2. If the backup fails a message describing the failure appears in the messages pane. Control returns back to the Backup/Restore Menu
   2. Dropdown list of backups in descending order of date. Clicking on a drop down item opens a BackupRecord Pane.
      1. The BackupRecord Pane will show
         2. Date of the backup.
         3. Location of the backup
         4. A button for restoring the backup.
         5. A button for deleting the backup.
         6. A Back-To-Backup/Restore arrow in the system's Navigation Bar.
   3. If a restore is going to wipe the current system a popup must appear warning the current data will be lost. If the popup's Continue button is selected launch the Restore process. Otherwise return to the BackupItem page.
2. Records
Records are show in the Dropdown where they can be selected.
3. Messages
These are listed above the Records Dropdown. Only the last two messages will be displayed. 

## Non-functional requirement 2: Durability
Most of our use cases change the system by either creating, updating, or deleting records. It is essential any
and writes are committed. There are two aspects of durability.
1. Persistence during a session.
2. Persistence across restarts and shutdowns.
Durability on a running system is easy to support with either critical sections and locking or using a relational database
because they provide Atomicity, Immutability, Consistency, and Durability organically. A relational database would also
provide persistence across restarts and shutdowns.

## Non-functional requirement 3: Usability

Explain specifically how you will account for this requirement in your application.

# Use case name

## Actors
1. Actor 1
2. Actor 2

## Use case goal

## Primary Actor

## Preconditions

## Basic flow

## Alternative flows

### Alternative flow 1

### Alternative flow 2