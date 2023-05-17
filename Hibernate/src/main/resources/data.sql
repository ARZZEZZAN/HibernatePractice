INSERT INTO Chat.User(login, password) VALUES
    ('Armen', '15061999'),
    ('Leva', '11456345534'),
    ('Temirkan' , '6416576613'),
    ('Nikita1', '97593763542'),
    ('Nikita2', '5671726264'),
    ('Konstantin', '526652652652');

INSERT INTO Chat.chatroom(chatroomname, chatroomowner) VALUES
    (1, (SELECT UserId FROM chat.User WHERE login = 'Armen')),
    (2, (SELECT UserId FROM chat.User WHERE login = 'Leva')),
    (3, (SELECT UserId FROM chat.User WHERE login = 'Temirkan')),
    (4, (SELECT UserId FROM chat.User WHERE login = 'Nikita1')),
    (5, (SELECT UserId FROM chat.User WHERE login = 'Armen'));

INSERT INTO Chat.message(messageauthor, messageroom, messagetext) VALUES
    ((SELECT UserId FROM chat.user WHERE login = 'Armen'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1'), 'Всем привет! Я Армен'),
    ((SELECT UserId FROM chat.user WHERE login = 'Temirkan'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1'), 'Здарова'),
    ((SELECT UserId FROM chat.user WHERE login = 'Leva'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1'), 'А я люблю gpt'),
    ((SELECT UserId FROM chat.user WHERE login = 'Nikita1'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'), 'Привет!'),
    ((SELECT UserId FROM chat.user WHERE login = 'Nikita2'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'), 'ХА-ХА-ХА'),
    ((SELECT UserId FROM chat.user WHERE login = 'Konstantin'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'), 'Чего смеешься?!?');

INSERT INTO Chat.user_chatrooms(userid, chatroomid) VALUES
    ((SELECT UserId FROM chat.user WHERE login = 'Armen'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1')),
    ((SELECT UserId FROM chat.user WHERE login = 'Temirkan'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1')),
    ((SELECT UserId FROM chat.user WHERE login = 'Temirkan'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '3')),
    ((SELECT UserId FROM chat.user WHERE login = 'Leva'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1')),
    ((SELECT UserId FROM chat.user WHERE login = 'Leva'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '2')),
    ((SELECT UserId FROM chat.user WHERE login = 'Nikita1'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4')),
    ((SELECT UserId FROM chat.user WHERE login = 'Nikita2'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4')),
    ((SELECT UserId FROM chat.user WHERE login = 'Konstantin'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4')),
    ((SELECT UserId FROM chat.user WHERE login = 'Armen'),
    (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '5'));