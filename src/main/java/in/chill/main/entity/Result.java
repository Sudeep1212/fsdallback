package in.chill.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "result")
public class Result {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;
    
    @Column(name = "rank")
    private int rank;
    
    @Column(name = "score")
    private float score;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Events event;
    
    @OneToOne
    @JoinColumn(name = "participant_id")
    private Participation participation;
    
    // Default constructor
    public Result() {}
    
    // Constructor with parameters
    public Result(int rank, float score, Events event, Participation participation) {
        this.rank = rank;
        this.score = score;
        this.event = event;
        this.participation = participation;
    }
    
    // Getters and setters
    public int getResultId() {
        return resultId;
    }
    
    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public float getScore() {
        return score;
    }
    
    public void setScore(float score) {
        this.score = score;
    }
    
    public Events getEvent() {
        return event;
    }
    
    public void setEvent(Events event) {
        this.event = event;
    }
    
    public Participation getParticipation() {
        return participation;
    }
    
    public void setParticipation(Participation participation) {
        this.participation = participation;
    }
    
    @Override
    public String toString() {
        return "Result{" +
                "resultId=" + resultId +
                ", rank=" + rank +
                ", score=" + score +
                ", event=" + (event != null ? event.getEvent_id() : null) +
                ", participation=" + (participation != null ? participation.getParticipationId() : null) +
                '}';
    }
}
