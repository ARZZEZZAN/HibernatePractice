package edu.school21.hibernate.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoomId")
    private Long id;
    @Column
    private String chatRoomName;
    @Column
    private String chatRoomOwner;

    public ChatRoom(Long id, String chatRoomName, String chatRoomOwner) {
        this.id = id;
        this.chatRoomName = chatRoomName;
        this.chatRoomOwner = chatRoomOwner;
    }

    public ChatRoom() {

    }
}
