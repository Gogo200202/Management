# Management system application
This is project for importing the csv file into database and working with it 

## Problem
Create an application that identifies the pair
of employees who have worked together 
on common projects for the longest period of 
time and the time for each of those projects.

## Routes List

| Method   | URI        | Action                                                                                       |
|----------|------------|----------------------------------------------------------------------------------------------|
| `PUT`    | ` Update?={id} ` | `App\Http\Controllers\HomeController@Update`                                                 |
| `Delete` | `Delete?={id}` | `App\Http\Controllers\HomeController@Delete `                                                |
| `POST`   | `Add?={id}` | `App\Http\Controllers\HomeController@Add `                                                   |
| `GET`    | `All`      | `App\Http\Controllers\HomeController@All `                                                   |
| `GET`    | `pairOfEmployeesWorkedTogetherForTheLongestPeriodOfTime` | `App\Http\Controllers\HomeController@pairOfEmployeesWorkedTogetherForTheLongestPeriodOfTime` |  

## Algorithm
First we get all the project id from the database so that we can use
the id for lather. We create Map for storing projects to employees and
the employees that he worked with. We get with who he was worked with
by using the project id and the time frame if someone started working after
him, we will add this employ to the map and add the current employ to it 
so that they both know that they worked together.
After that we create new map to get pair of employees to get the total hours 
that they worked