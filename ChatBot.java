public class ChatBot{

  /*
  * Get a default greeting
  * @return a greeting
  */
  public String getGreeting(){
    return "Hello, let's talk.";
  }

  /*
  * Gives a response to a user statement
  * @param statement
  * @return a response based on the rules given
  */
  public String getResponse(String statement){
    String response = "";
    statement.trim();
    if(findKeyword(statement, "no", 0)>=0){
      response = "Why so negative?";
    }else if(findKeyword(statement, "mother", 0)>=0 || findKeyword(statement, "father", 0)>=0 || findKeyword(statement, "sister", 0)>=0 || statement.indexOf("brother")>0){
      response = "Tell me more about your family.";
    }else if(findKeyword(statement, "dog", 0)>=0 || findKeyword(statement, "cat", 0)>=0){
      response = "Tell me more about your pets.";
    }else if(findKeyword(statement, "Zeller", 0)>=0) {
      response = "I love that guy!";
    }else if(statement.length() == 0){
      response = "Please enter something.";
    }else if(findKeyword(statement, "Jeffrey Jigglemeister", 0)>=0){
      response = "The undisputed goat!";
    }else if(findKeyword(statement, "You are a bot", 0)>=0){
      response = "So what?";
    }else if(findKeyword(statement, "Bee movie?", 0)>=0){
      response = "According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway.";
    }else if(findKeyword(statement, "I want to", 0)>=0){
      response = transformIWantToStatement(statement);
    }else if(findKeyword(statement, "you", 0)>=0 && findKeyword(statement, "me", 0) > findKeyword(statement, "you", 0)){
      response = transformYouMeStatement(statement);
    }else if(findKeyword(statement, "I want", 0)>=0){
      response = transformIWantStatement(statement);
    }else if(findKeyword(statement, "I", 0)>=0 && findKeyword(statement, "you", 0) > findKeyword(statement, "I", 0)){
      response = transformIYouStatement(statement);
    }else{
      response = getRandomResponse();
    }
    return response;
  }

  /*
  * Pick a default response to use if nothing else fits.
  * @return a non-commital string
  */
  private String getRandomResponse(){
    int numberOfResponses = 6;
    double r = Math.random();
    int whichResponse = (int)(r*numberOfResponses);
    String response = "";

    if(whichResponse==0){
      response = "Interesting, tell me more.";
    }else if(whichResponse==1){
      response = "Hmmm.";
    }else if(whichResponse==2){
      response = "Do you really think so?";
    }else if(whichResponse==3){
      response = "You don't say.";
    } else if(whichResponse==4){
      response = "That's craaazy.";
    } else if(whichResponse==5){
      response = "No way, man.";
    }
    return response;
  }

  /*
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
  private int findKeyword(String statement, String goal, int startPos){
    String phrase = statement.trim().toLowerCase();
    goal = goal.toLowerCase();

    int psn = phrase.indexOf(goal, startPos);

    while(psn>=0){
      String before = " ";
      String after = " ";
      if(psn>0){
        before = phrase.substring(psn-1, psn);
      }
      if(psn+goal.length()<phrase.length()){
        after = phrase.substring(psn+goal.length(), psn+goal.length()+1);
      }
      if(((before.compareTo("a")<0) || (before.compareTo("z")>0)) && ((after.compareTo("a")<0) || (after.compareTo("z")>0))){
        return psn;
      }

      psn = phrase.indexOf(goal, psn+1);
    }

    return -1;
  }

  /**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no"). The search
	 * begins at the beginning of the string.
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal){
		return findKeyword(statement, goal, 0);
	}

  /*
	 * Take a statement with "I want to <something>." and transform it into
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement){
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

  /*
	 * Take a statement with "you <something> me" and transform it into
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement){
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}

		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);

		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
  private String transformIWantStatement(String statement){
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}
    int psnOfIWant = findKeyword(statement, "I want", 0);
    String restOfStatement = statement.substring(psnOfIWant + 6, statement.length());
    restOfStatement = restOfStatement.trim();
		  return "Would you really be happy if you had " + restOfStatement + "?";
  }
  private String transformIYouStatement(String statement){
    statement = statement.trim();
     String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}
    int psnOfI = findKeyword(statement, "I", 0);
    int psnOfYou = findKeyword(statement, "you", findKeyword(statement, "I", 0));
    String restOfStatement = statement.substring(psnOfI+1, psnOfYou);
      return "Why do you" + restOfStatement + "me?";
  }
}
