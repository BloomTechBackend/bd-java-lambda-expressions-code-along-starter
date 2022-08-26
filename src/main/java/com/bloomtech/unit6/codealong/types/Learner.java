package com.bloomtech.unit6.codealong.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
/********************************************************************************************
 * Represent a basic Learner object with an id
 ********************************************************************************************/
public class Learner implements Cloneable {
//*******************************************************************************
// Instance Variables
//*******************************************************************************
        private int           id;
        private String        track;
        private String        name;
        private List<Integer> scores;
        private int           totalScore;
        private double        averageScore;

//*******************************************************************************
// Constructors
//*******************************************************************************
        // default ctor in case Java needs one
        public Learner() {}

        public Learner(String track, int id, String name, List<Integer> scores) {
                this.track  = track;
                this.id     = id;
                this.name   = name;
                this.scores = new ArrayList(scores);

                // TODO: Code-ALong - calculate totalScore a Lambda Expression
                totalScore = 0;

                averageScore = totalScore / scores.size();
        }
//*******************************************************************************
// getters/setters
//*******************************************************************************
        public String getTrack() { return track; }
        public void   setTrack(String track) { this.track = track; }
        public int    getId()       { return id;   }
        public void   setId(int id) { this.id = id;}
        public String getName()     { return name; }
        public void   setName(String name) { this.name = name;    }
        public List<Integer> getScores()   { return new ArrayList(scores); }
        public int    getTotalScore()      { return totalScore;   }
        public double getAverageScore()    { return averageScore; }
        public void   setScores(List<Integer> scores) { this.scores = new ArrayList(scores); }

//*******************************************************************************
// Method Overrides
//*******************************************************************************
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Learner)) return false;
                Learner learner = (Learner) o;
                return getId() == learner.getId() && getTotalScore() == learner.getTotalScore() && Double.compare(learner.getAverageScore(), getAverageScore()) == 0 && getTrack().equals(learner.getTrack()) && getName().equals(learner.getName()) && getScores().equals(learner.getScores());
        }
        @Override
        public int hashCode() {
                return Objects.hash(getId(), getTrack(), getName(), getScores(), getTotalScore(), getAverageScore());
        }
        @Override
        public String toString() {
                return "Learner{" +
                        "id=" + id +
                        ", track='" + track + '\'' +
                        ", name='" + name + '\'' +
                        ", scores=" + scores +
                        ", totalScore=" + totalScore +
                        ", averageScore=" + averageScore +
                        '}';
        }
        @Override
        public Learner clone() throws CloneNotSupportedException {
                Learner newLearner = (Learner) super.clone();  // Shallow copy object
                newLearner.setScores(this.getScores());        // Deep copy reference variables
                return newLearner;
        }

//*******************************************************************************
// Helper and Compare methods
//*******************************************************************************
        public String getScoresAsString() {
                return getScores().toString();
        }

        //**************************************************************************
        // methods to return how values in one Learner compare to values in another
        // static methods are used so they can be invoked without objects:
        //          Learner::methodName()
        //**************************************************************************
        public static int compareByName(Learner learner1, Learner learner2) {
                return learner1.name.compareTo(learner2.name);
        }
        public static int compareById(Learner learner1, Learner learner2) {
                return learner1.id - learner2.id;
        }
        public static int compareByTrack(Learner learner1, Learner learner2) {
                return learner1.track.compareTo(learner2.track);
        }
        public static int compareByTrackAndName(Learner learner1, Learner learner2) {
                if (learner1.track.compareTo(learner2.track) != 0) {     // If tracks are not the same, no need to compare name
                        return learner1.track.compareTo(learner2.track); // return how the tracks compare
                }
                return learner1.name.compareTo(learner2.name);  // If tracks are the same, return how names compare
        }
        public static int compareByTotalScore(Learner learner1, Learner learner2) {
                return learner1.totalScore - learner2.totalScore;
        }
        public static int compareByAvgScore(Learner learner1, Learner learner2) {
                // Multiply by 10000 to make double value a meaningful int
                // so, .123, when cast to an int, is 1230 instead of 0
                return (int) learner1.averageScore * 10000 - (int) learner2.averageScore * 10000;
        }
}
