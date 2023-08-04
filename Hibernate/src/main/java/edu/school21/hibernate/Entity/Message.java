package edu.school21.hibernate.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid")
    private Long id;
    @Column
    private Long messageAuthor;
    @Column
    private Long messageRoom;
    @Column
    private String messageText;
    @Column
    private String MessageDate;

    public Message(Long id, Long messageAuthor, Long messageRoom, String messageText, String messageDate) {
        this.id = id;
        this.messageAuthor = messageAuthor;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        MessageDate = messageDate;
    }

    public Message() {

    }
}
