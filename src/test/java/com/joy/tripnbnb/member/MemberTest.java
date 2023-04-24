package com.joy.tripnbnb.member;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Order(1)
    @Test
    @DisplayName("회원 생성 테스트")
    public void create_member() {
        // given
        Member member = Member.builder()
                .memberName("tester")
                .email("tester@gmail.com")
                .profileImg("")
                .build();

        // when
        Member dbMember = memberRepository.save(member);

        // then
        assertThat(dbMember.getMemberName()).isEqualTo("tester");
        assertThat(dbMember.getEmail()).isEqualTo("tester@gmail.com");
        assertThat(dbMember.getProfileImg()).isEqualTo("");
    }

    @Order(2)
    @Test
    @DisplayName("회원 조회 테스트")
    public void get_member() {
        // given
        Long id = 1L;

        // when
        Optional<Member> dbMember = memberRepository.findById(id);

        // then
        dbMember.ifPresent(selectMember -> {
            System.out.println("member: " + selectMember);
        });
    }

    @Order(3)
    @Test
    @DisplayName("회원정보 수정 테스트")
    public void update_member() {
        // given
        Long id = 1L;
        Optional<Member> member = memberRepository.findById(id);

        String memberName = "tester2";

        // when
        // then
        member.ifPresent(selectMember -> {
            selectMember.setMemberName(memberName);
            Member newMember = memberRepository.save(selectMember);
            System.out.println("member: " + selectMember);
        });
    }

    @Order(4)
    @Test
    @DisplayName("회원 삭제 테스트")
    public void delete_member() {
        // given
        Long id = 1L;
        Optional<Member> dbMember = memberRepository.findById(id);

        // when
        dbMember.ifPresent(selectMember -> {
            memberRepository.delete(selectMember);
        });

        //then
        Assert.assertFalse(memberRepository.findById(id).isPresent());
    }
}
