# ParaBankTestPlaywright

UI Test scenarios:
1. Navigate to Para bank application.
2. Create a new user from user registration page (Ensure username is generated randomly and it is unique in every test
execution).
3. Login to the application with the user created in step 2.
4. Verify if the Global navigation menu in home page is working as expected.
5. Create a Savings account from “Open New Account Page” and capture the account number.
6. Validate if Accounts overview page is displaying the balance details as expected.
7. Transfer funds from account created in step 5 to another account.
8. Pay the bill with account created in step 5.
9. Add necessary assertions at each test step whenever it is needed.
    
API Test scenarios:
1. Search the transactions using “Find transactions” API call by amount for the payment transactions made in Step 8.
2. Validate the details displayed in Json response.
