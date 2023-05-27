package edu.school21.hibernate.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NamedEntityGraph(
        name = "User.withCollections",
        attributeNodes = {
                @NamedAttributeNode("roomCreated"),
                @NamedAttributeNode("roomsSocial"),
                @NamedAttributeNode("messages")
        }
)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;
    @Column
    private String login;
    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "chatroom",
            joinColumns = @JoinColumn(name = "chatroomowner"),
            inverseJoinColumns = @JoinColumn(name = "chatroomId")
    )
    private List<ChatRoom> roomCreated = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_chatrooms",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "chatroomId")
    )
    private List<ChatRoom> roomsSocial = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "message",
            joinColumns = @JoinColumn(name = "messageauthor"),
            inverseJoinColumns = @JoinColumn(name = "messageid")
    )
    private List<Message> messages = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }
}
