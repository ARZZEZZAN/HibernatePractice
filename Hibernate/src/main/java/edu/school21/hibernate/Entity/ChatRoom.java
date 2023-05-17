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
public class ChatRoom {
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
