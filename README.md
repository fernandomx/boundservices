# Android Bound Services
App simple for demonstration the use Android Bound Services.

# Service: 
Is an application component that can perform
long-running operations in the background with no user interface.

# Yes, i said NO USER INTERFACE

These application have their own lifecycle independent 
of the activity or fragment that they were created in.
So, services are unaffected by activity or fragment 
lifecycle events (onDestroy, onPause, and so on).

By default, services operate no the same
thread that they're instantiated on.

You can use bound services when Client-Server interaction. 
The service acts as the server and someone component is a client.
The client could be an activity a fragment a smart-watch or other application.

So, when you use consistent or frequent communication between some client
and the service, in this case can be use Bound Services.
