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

### Mechanisms for Complying with the Backup Requirement
User Interfaces, Tools, processes, resources and files will be used to meet the backup requirement.

#### User Interface
The controls and records pertaining to backup/restore will only be accessible through the Backup/Restore menu.
There are two mutually exclusive ways of accessing the Backup/Restore Menu.
1. From the Backup button in the Main Menu.
2. The Backup button in the Navigation Bar.
The Navigation Bar is not present in the Main Menu so we need the Backup Button in both places.

##### Backup User Interface Layout
1. The Backup Button is removed from the Navigation Bar when we are in the Backup Menu.
2. Messages about the last 3 backup operations appear in an immutable Messages pane. The Messages Pane is at the top of the page.
3. A Dropdown list of successful backups in descending order of date. Selecting an item from this dropdown brings the BackupTransactionRecord 
page for the item.
4. A Backup button which launches the Backup process.

#### Tools
Java's native Serialization features might provide some of this requirement. Ideally we would use a database probably SQLite
for storing the records. Using a RDBMS is not required for enabling backup but making the operations consistent is easier to 
support with a RDMBS especially for restoring correctly. We will use an SQLLite implementation that supports exporting the database
to JSON or XML. 

#### Processes
There are two processes. Both processes are ACID transactions.
##### Backup Process 
This can only be launched by clicking the Backup button in the Backup/Restore menu. A Popup appears showing percent completing of the backup. The only results are success or failure.
Regardless of outcome control returns to the Backup Main Menu.

1. Success
   1. A message is at the top of the Messages stack reporting backup was successfully made.
   2. The new backup is at the top of the Backup History Dropdown.
2. Failure
   1. A message describing the failure's nature appears at the top of the Messages stack.
   2. If the backup fails a message describing the failure appears in the messages pane. 

##### Restore Process
The RestoreBackup process can only be launched by Clicking a record in Backup History Dropdown. Clicking a record brings the
BackupTransactionRecord pane to the foreground.

##### Layout of BackupTransactionRecord Pane
1. Textbox which shows date, size, and location of backup.
2. A button to delete the backup.
3. A button to restore from the backup.

Clicking the Restore button brings a Confirmation Dialog warning that restoring the backup will overwrite the current records
If the user confirms this a progress bar shows. All other system operations are halted until the restoration process completes.

##### Restoration Outcomes
Restorations are atomic, They either succeed or fail. Regardless of outcome control returns to Backup Main Menu
so we can see the messages. The only difference between them is the message describing the restoration outcome.

#### Canceling Backups and Restorations
Pressing the cancellation button during a backup or restoration rolls back operations up to that point and returns 
control to the Backup/Restore Menu.

#### Deletion
Deleting a backup deletes the directory of the backup. The backup will be removed from the Backup History Dropdown
but a record of the deletion, the backupId and DateTime of the deletion will be in the Deletion History Dropdown.

#### Resources
A directory will be made for each backup in the backups directory. The backup folder's name will be an id and timestamp.


1. Success
   1. A message is at the top of the Messages stack reporting backup was successfully made.
   2. The new backup is at the top of the Backup History Dropdown.
2. Failure
   1. A message describing the failure's nature appears at the top of the Messages stack.
   2. If the backup fails a message describing the failure appears in the messages pane.
   

### How Our System Complies with Backup Requirement
Our system will have a Backup/Restore feature accessible from the Main Menu and the Navigation Bar.
1. Clicking the Backup/Restore button on either the Main Menu or Navigation Bar will bring the Backup/Restore menu to the foreground.
2. The Backup/Restore Pane will have controls, records, and a messages pane.
3. Controls
   1. Button to launch the backup process.
      1. 
      2. 
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