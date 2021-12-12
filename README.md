# users
created to register users on the system
=======
# Users
Project created with objective of register users on the system and later makes it available to be displayed through API, making it usefull for third party software.

#User's manual
When initialized, the system will guide you how to navigate in the program as showed below. The options available are:

    0 - EXIT
    1 - CREATE
    2 - READ
    3 - UPDATE
    4 - DELETE
    CHOOSE AN ACTION TO BE EXECUTED:

###Create (option 1):

In this option is defined an action to load some users by default.

    CHOOSE AN ACTION ABOVE TO BE EXECUTED:
    1
    1 | Lionel | Messi | Thu May 10 00:00:00 IST 1990 | lionelmessi@barcelona.com | male | married | 0
    2 | Cristiano | Ronaldo | Mon Feb 29 00:00:00 GMT 1988 | cristianoronaldo@juventus.com.br | female | married | 0

###Read (option 2):

Selecting option Read will exhibit a guide message The identifier code (ID) from the user has to be informed and the user data will be related as showed:

    CHOOSE AN ACTION ABOVE TO BE EXECUTED:
    2
    READING - INFORM USER ID:
    1
    1 | Lionel | Messi | Thu May 10 00:00:00 IST 1990 | lionelmessi@barcelona.com | male | married | 0

###Update (option 3):

Choosing update will also ask to inform the user ID. Once done it's going to present all the fields and its has to be filled again:

    CHOOSE AN ACTION ABOVE TO BE EXECUTED:
    3
    UPDATING - INFORM USER ID:
    1
    id:
    firstName:
    Lioooonellllll
    lastName:
    Messi
    birthDay:
    10-05-1985
    gender:
    MALE
    email:
    leionelmessi_updated@psg.com
    maritalStatus:
    MARRIED
    idPartner: 
    0
    ...
    ...

and so on. After this, the updated user data will be displayed:

    1 | Lioooonellllll  | Messi | Fri May 10 00:00:00 IST 1985 | leionelmessi_updated@psg.com | male | married | 0

###Delete (option 4):

Message below is displayed. Inform ID user to be deleted.

    CHOOSE AN ACTION ABOVE TO BE EXECUTED:
    4
    DELETING - INFORM USER ID:
    1
    User deleted.

Once done user will no longer be available to read:
        
    CHOOSE AN ACTION ABOVE TO BE EXECUTED:
    2
    READING - INFORM USER ID:
    1
    User is not listed.

###Exit (option 0): Finalize the program. 

    CHOOSE AN ACTION ABOVE TO BE EXECUTED:
    0
    Program finalize by user.   
>>>>>>> develop
