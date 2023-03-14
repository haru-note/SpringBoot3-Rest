insert into my_note (id, content, created_at, done, title)
values (1, '첮번째 노트 내용이 들어갑니다.', now(), false, '첫번째 노트 제목이 들어갑니다.');

insert into my_note (id, content, created_at, done, title)
values (2, '두번째 노트 내용이 들어갑니다.', now(), false, '두번째 제목이 들어갑니다.');


insert into tag (id, deleted, my_tag_name, my_note_id)
values (1, false, 'JAVA', 1);
insert into tag (id, deleted, my_tag_name, my_note_id)
values (2, false, 'Spring', 1);
insert into tag (id, deleted, my_tag_name, my_note_id)
values (3, false, 'k8s', 1);

