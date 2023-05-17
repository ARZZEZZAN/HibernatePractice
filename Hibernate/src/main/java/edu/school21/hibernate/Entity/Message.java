package edu.school21.hibernate.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Message {
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

}
