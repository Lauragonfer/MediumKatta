#ClickerCharger

#Exercise

We want to build a new system to charge for the clicks that we get from the website.

A campaign is an investment that a customer makes on our platform. Each campaign has an id and a budget. 
We want to be able to pause and activate it. 
We want to charge for clicks. Each click has it's own id , the id of the user who made the click, the instant and
if it's premium.
When a click is premium we charge 0,05€ and if not, 0,01€.
Only when a campaign is active clicks can be charged. 
When the budget reaches 0 the campaign state changes to finished and it can't be paused or activated.

Some companies want a demo (try it) campaigns running in these are free cost and no click will be charged.
Top campaigns are these campaigns we charge for the premium clicks to 0,2€ and non-premium to 0,1€.
Campaigns standard have the same prices described before.

We consider that duplication exists when two clicks that are made by the same user on the same ad in a 15 seconds window.
In this case we don't want to charge for the clicks after the first in this period.

Sometimes bots generate fake clicks  We want that given a date where we start to find fraudulent clicks and a list of
user identifiers allows us to rollback the charges applying the following criteria.
* If the campaign is a demo campaign we do nothing as no charges are applied.
* For standard campaigns we want to refund only the clicks made by the bot.
* For premium campaigns we want to refund all the clicks if the amount is not higher than 5% of their totalbudget. 
If the amount to refund is higher than that we only want to refund the clicks made by a bot.


##Assumptions

* All Campaigns are in pause state when are created, you should activate them to use it
* When we refund the bots clicks only use the day , month and Year to compare the Date, not take care of hour
* I use Branch to separate the different Sprints, in this file you can see more information of each Sprint.
* To control the "errors" like empty date o similar, I use Exceptions 
* I use only IdUser to identify and represent a user.
 
##Test

I create one unit test and acceptance test for each type of Campaign, with this test you can check the development 

##Development Diary

###Sprint 1

  * Create the base of the APP. 
  * The Campaign have various states Pause, Active and Finished you can change between states except if the state 
  is finished. 
  * Create a system of click charges, can be Premium (more expensive) or normal
  * Apply the state pattern to resolve te states of the campaign
  * I create value Object to manage Id of the class
  * I decided to manage errors with exceptions to control all posible cases

###Sprint 2

  * Create a nes feature to check if the clicks are duplicate, if the click is duplicate only charge the first one
  * A click is duplicate if it is make by the same user, in the same advertisement and between them are less than
  15 seconds
  * Date are a little deprecated object, but i decided use it to control the dates in the Clicks
  * Crete Advertisement class to know which advertisement the user click, and change the structure of click to 
  implement this new one feature
  * Add new test to chek the new features
  * Clean some code

###Sprint 3

  * Create a New Types of Campaign Demo and Top , and change the actual one to Standard. With this changes can 
  implement the new feature to change the cost of the clicks or the system of charge them.
  * Implement the builder Pattern
  * Detected a problem with the use of Double in Budget, because is not 100% precise in the calc, make a round 
  to solve the problem but maybe Y should implement BigDecimal instead of Double 
  * Implement different types of Budget to match with the type of the campaign, and the differences between the charges
  * Refactor the way than The campaign change the state.
  * Create new different test to check de new Campaign Types
  * Clean some code
 
###Sprint 4
  * Add the Diary information to the README
  * Create a new feature to control de duplicated clicks, provided a Date when the bots start and a list of bot users.
  If it is a premium campaign refund all the clicks in that period  if the total amount of them are les than the 5% 
  of the initial Budget of the campaign.
  * I have problems with the object Date, and decided two dates are the same if Day, Month and year are equals. 
  I valorate change to use Timestamp class or use the library Joda-Time but y prefer to use this restriction of compare.   
  * I apply memento patter to check and save the initial budget, and the changes of them.
  * Clean and refactor the code.
  * Upgrade the README.md


