package au.com.sports.mate.test.general;

import java.io.Serializable;
import java.util.ArrayList;

public class Player {

    private String firstName;
    private String lastName;
    private Integer number;
    private Integer playerId;
    private Integer teamId;
    private Integer rating;
    private double value;

    public Player(String firstName, String lastName, Integer number, Integer playerId, Integer teamId, Integer rating, double value) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.playerId = playerId;
        this.teamId = teamId;
        this.rating = rating;
        this.value = value;
    }


    public static class LiveMatchLineUpsStatsTablePlayer implements Serializable {
        private int playerId;
        private int rating;
        private String teamId;
        private int number;

        private String firstName;
        private String lastName;

        Boolean isFavorite;

        private ArrayList<String> valuesFull;

        public LiveMatchLineUpsStatsTablePlayer(String firstName, String lastName, int number, int playerId, int rating, ArrayList<String> valuesFull, Boolean isFavorite) {

            this.playerId = playerId;
            this.valuesFull = valuesFull;
            this.rating = rating;
            this.firstName = firstName;
            this.lastName = lastName;
            this.isFavorite = isFavorite;
        }

        public int getPlayerId() {
            return playerId;
        }

        public void setPlayerId(int playerId) {
            this.playerId = playerId;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public ArrayList<String> getValuesFull() {
            return valuesFull;
        }

        public void setValuesFull(ArrayList<String> valuesFull) {
            this.valuesFull = valuesFull;
        }

        public Boolean getFavorite() {
            return isFavorite;
        }

        public void setFavorite(Boolean favorite) {
            isFavorite = favorite;
        }
    }
}
