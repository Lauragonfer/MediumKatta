#ClickerCharger




##Development Diary

###Sprint 1

  - Create the base of the APP. 
  * The Campaign have various states Pause, Active and Finished you can change between states except if the state 
  is finished. 
  * Create a system of click charges, can be Premium (more expensive) or normal
  * Apply the state pattern to resolve te states of the campaign

###Sprint 2
  * Create a nes feature to check if the clicks are duplicate, if the click is duplicate only charge the first one
  * A click is duplicate if it is make by the same user, in the same advertisement and between them are less than
  15 seconds
  * Crete Advertisement class to know which advertisement the user click, and change the structure of click to 
  implement this new one feature
  * Add new test to chek the new feature
  * Clean some code

###Sprint 3

  * Create a New Types of Campaign Demo and Top , and change the actual one to Standard. With this changes can 
  implement the new feature to change the cost of the clicks or the system of charge them.
  * Detected a problem with the use of Double in Budget, because is not 100% precise in the calc, make a round 
  to solve the problem but maybe Y should implement BigDecimal instead of Double 
  * Implement different types of Budget to match with the type of the campaign, and the differences between the charges
  * Refactor the way than The campaign change the state.
  * Create new difrent test to check de new Campaign Types
  * Clean some code
 
###Sprint 4
  * Add the Diary information to the README


