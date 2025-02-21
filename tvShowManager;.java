import java.util.Scanner;

// Record holding information for mountains
class Episode {
    final int MAX_EPISODES = 1000;

    int[] season = new int[MAX_EPISODES];
    String[] title = new String[MAX_EPISODES];

    int episodesCounter = 0;


}//END class Episode


class tvShowManager {

    //Main method where other methods will be called
    public static void main(String[] a){

        Episode newEpisode = new Episode();

        menu(newEpisode);

        return;
    }//END main



    //Handles user inputs
    public static String inputs(String message) {
        String userInput;
        final Scanner scanner = new Scanner(System.in);
        print(message);
        userInput = scanner.nextLine();

        return userInput;
    }//END inputs



    //Shorter outputting method
    public static void print(String message) {
        System.out.println(message);
        return;
    }//END print



    //Retrieves the season of the episode
    public static Episode setEpisodeSeason(Episode newEpisode, int season) {
        int counter = getEpisodeCounter(newEpisode);

        newEpisode.season[counter] = season;
        return newEpisode;
    }//END setEpisodeSeason



    // Retried the title of the episode
    public static Episode setEpisodeTitle(Episode newEpisode, String title) {
        int counter = getEpisodeCounter(newEpisode);

        newEpisode.title[counter] = title;
        return newEpisode;
    }//END setEpisodeTitle


    // Updates the episode counter to increment the number of episodes recorded
    public static Episode setEpisodeCounter(Episode newEpisode, int episodes) {
        newEpisode.episodesCounter = episodes;
        return newEpisode;
    }//END setEpisodeCounter



    // Retrieves the number of episodes recorded
    public static int getEpisodeCounter(Episode newEpisode) {

        return newEpisode.episodesCounter;
    }//END getEpisodeCounter



    // Retrieves the season number of a specific episode
    public static int getEpisodeSeason(Episode newEpisode, int episodeIndex) {
        return newEpisode.season[episodeIndex];
    }//END getEpisodeSeason



    // Retrieves the title  of a specific episode
    public static String getEpisodeTitle(Episode newEpisode, int episodeIndex) {
        return newEpisode.title[episodeIndex];
    }//END getEpisodeTitle


    //Retrieves the max number of episodes that can be stored
    public static int getMaxEpisodes(Episode newEpisode){

        return newEpisode.MAX_EPISODES;
    }//END getMaxEpisodes



    //Checks if user input is a String
    public static boolean isStringValue(String input){

        return (input.matches("[a-zA-Z\\s]+"));
    }//END isStringValue



    //Checks if user input is a String
    public static boolean isPosIntegerValue(String input){

        if (input.matches("\\d+")){
            return Integer.parseInt(input)>=0;
        }else{
            return false;
        }
    }//END isPosIntegerValue



    // Asks the user for the episode's season then saves it in record
    public static void askingEpisodeSeason(Episode newEpisode) {
        String season = inputs("Season number? ");

        while(!isPosIntegerValue(season)){
            season = inputs("Invalid input. Please only enter a positive integer. /n");

        }
        setEpisodeSeason(newEpisode, Integer.parseInt(season));

        return;
    } //END askingEpisodeSeason




    // Asks the user for episode's title then saves it in record
    public static void askingEpisodeTitle(Episode newEpisode) {
        String title = inputs("Title ? ");

        while(!isStringValue(title)){
            title = inputs("Invalid input. Please only enter characters. /n");

        }
        setEpisodeTitle(newEpisode, title);

        return;
    } //END askingEpisodeTitle



    // Allows user to enter details for episode
    public static void addingEpisodeDetails(Episode newEpisode){
        int counter = getEpisodeCounter(newEpisode);
        int maxEpisodes = getMaxEpisodes(newEpisode);

        if (counter < maxEpisodes) {
            askingEpisodeSeason(newEpisode);
            askingEpisodeTitle(newEpisode);

            counter++;
            setEpisodeCounter(newEpisode, counter);
        } else {
            print("Maximum episodes reached.");
        }

        return;
    }//addingEpisodeDetails



    // Prints the season where a title shows up
    public static void printTitles(Episode newEpisode) {
        int episodeCounter = getEpisodeCounter(newEpisode);
        String title = inputs("Title? ");
        boolean hasMatches = false;

        for (int episode = 0; episode < episodeCounter; episode++) {
            if (getEpisodeTitle(newEpisode, episode).equalsIgnoreCase(title)) {
                if (!hasMatches) {
                    hasMatches = true;
                }
                print("Episode \"" + title + "\" was in season " + getEpisodeSeason(newEpisode, episode) + ".");
            }
        }

        if (!hasMatches) {
            print("No episodes recorded for \"" + title + "\".");
        }
    } // END printTitles



    //Lists the titles of a specific seaSon
    public static void listingEpisodes(Episode newEpisode){
        int episodeCounter = getEpisodeCounter(newEpisode);
        int season = Integer.parseInt(inputs("Season number to list? "));
        int matchesFound = 0;
        boolean hasMatches = false;

        for (int episode = 0; episode < episodeCounter; episode++) {
            if (getEpisodeSeason(newEpisode, episode) == season) {
                if (!hasMatches) {
                    hasMatches = true;
                }
                print("\"" + getEpisodeTitle(newEpisode, episode) + "\" ");
                matchesFound++;
            }
        }

        if (!hasMatches) {
            print("No episodes recorded for season " + season);
        } else{
            print("Total episodes in season " + season + " is " + matchesFound);
        }
    }// END listingEpisodes




    // Main menu loop
    public static void menu(Episode newEpisode) {
        boolean exit = false;
        String input;
        
        print("(1) Add episode (2) Search for title (3) List season (4) Exit");
        while (!exit) {

            input = inputs("Enter options [1-4] ");

            if (input.equals("1")) {
                addingEpisodeDetails(newEpisode);
            } else if (input.equals("2")) {
                printTitles(newEpisode);
            } else if (input.equals("3")) {
                listingEpisodes(newEpisode);
            } else if (input.equals("4")) {
                exit = true;

            } else {
                print("Invalid input. Please enter '1', '2', '3', or '4'.");
            }
        }
    }//END menu


}//END class tvShowManager

