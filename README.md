# General

A siple fetcher with lots of assumptions.
Little toy I made for a friend that wanted to use their cloud app api.
The business requirement is that they wanted to be able to call some endpoint
that they found convient and convert the data to csv so they can open it in
Excel and manipulate it.

## General flow
Basic premise is that you add the url you will call, the api key you need to
use, any body parameters you need to pass and call it.

I have two areas to show the json from the original request and/or
any errors that may occur. That is mainly for debuggin purposes and my friend 
being able to tell me what went wrong (different locations).

### GUI Differences
* The java app will save it to the location you specify in the GUI.
* The site will ask you to save it as if it's downloading it.

### JSON 2 CSV conversion
The json is converted to CSV with different methods in the java app and site.
Both flatten nested objects.
Java splits arrays to columns.
Site keeps it as one field and prints it.

## TODO
* convert timestamps to date time that excel can undertand [java]
* add api key to header and not in body [both]
* revisit time and timezones and differences [both]
  * Site does a kinda naive but fairly sound/safe calculation about it
* Check CORS [site]