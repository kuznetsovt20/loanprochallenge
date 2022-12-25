Feature: Login feature test

Scenario: Succesfull login with valid credentials
Given Launch Chrome browser
When Go to page "https://beta-loanpro.simnang.com/client/app/login.html?"
Then Assert validate controls exist
When Enter user name as "loanproqaautomatedapitesting+atestpractice@gmail.com" and password as "%Wd5u50Q1?121" and login to application
Then User land on page "https://beta-loanpro.simnang.com/client/app/index.php#/t_/797/loan/menu"
And Assert validations of controls
And Validate results that loans displayed are with status "Approved", total number of results is 6
And Close browser