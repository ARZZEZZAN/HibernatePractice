package edu.school21.hibernate.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @OneToMany
    @JoinTable(
            name = "chatroom",
            joinColumns = @JoinColumn(name = "chatroomowner"),
            inverseJoinColumns = @JoinColumn(name = "chatroomId")
    )
    private List<ChatRoom> roomCreated;
    @ManyToMany
    @JoinTable(
            name = "user_chatrooms",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "chatroomId")
    )
    private List<ChatRoom> roomsSocial;
    @OneToMany
    @JoinTable(
            name = "message",
            joinColumns = @JoinColumn(name = "messageauthor"),
            inverseJoinColumns = @JoinColumn(name = "messageid")
    )
    private List<Message> messages;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roomCreated=" + roomCreated +
                ", roomsSocial=" + roomsSocial +
                ", messages=" + messages +
                '}';
    }
}
