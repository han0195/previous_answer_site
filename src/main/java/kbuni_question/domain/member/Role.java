package kbuni_question.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    MEMBER("ROLE_MEMBER","회원"),
    ADMIN("ROLE_ADMIN" , "관리자");

    // 열거형 들어가는 필드 항목들
    private final String key;
    private final String title;

}
