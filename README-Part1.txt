In this MP you will be working with and adding functionality to a chat bot. When
done correctly, chat bots can engage a user with conversation that is dependent
on their responses.

Task 1
Functionality for the chat bot is stored within ChatBot.java. ChatBotRunner.java
contains code that will allow the use to interact with the chat bot. Run
ChatBotRunner.java and have a conversation.

How does the chat bot respond to the following phrases:

My mother and I talked last night.
I said no!
The weather is nice.
Do you know my brother?

Task 2
Take a look at the code inside ChatBot.java. Notice how the if statements select
a response to return. Take a look at the getRandomResponse() method and note how
it functions.

Task 3
Edit the code to have the chatbot respond "Tell me more about your pets" when
the statement contains the word "dog" or "cat".

Task 4
Edit the code to have the chatbot to respond favorably when the name of your
teacher is used.

Task 5
Check that the statement has at least one character. You can use the trim()
method to remove white space from the beginning and end and then check the new
string's length. If there are no characters, the chatbot should prompt the user
to enter something.

Task 6
Add two more noncommittal responses to the possible random responses.

Task 7
Pick three more keywords, such as "no" and "brother" and edit the getResponse()
method to respond to each of these.

Question 1
What are some unintended issues that can occur with the chatbot?
The chatbot can't recognize certain key words when they are the first word.
It also recognizes them when they are inside other words.
Not only that, but it also can't detect key words when they are capitalized differently than intended.
