package com.yassine.sport_club_projet.services;


import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.UserAlreadyExistException;
import com.yassine.sport_club_projet.mapper.MemberMapper;
import com.yassine.sport_club_projet.mapper.UserMapper;

import com.yassine.sport_club_projet.repositories.MemberRepository;
import com.yassine.sport_club_projet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;
    public final MemberMapper memberMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<MemberResponseDto> GetAllMembers(){
      return memberRepository.findAll().stream()
              .map(memberMapper::toDto)
              .toList();
  }
  public MemberResponseDto GetMember(Long id) throws MemberNotFoundException {
      var member = memberRepository.findById(id).orElse(null);
      if(member == null)
          throw new MemberNotFoundException();
      return memberMapper.toDto(member);
  }
  public MemberResponseDto AddMember(UserMemberRequestDto userMemberRequestDto) throws UserAlreadyExistException {
        if(userRepository.findByEmail(userMemberRequestDto.getEmail()).isPresent())
            throw new UserAlreadyExistException();
        User user = new User();
        user.addUser(userMemberRequestDto.getEmail() , userMemberRequestDto.getPassword() , userMemberRequestDto.getFirstname() , userMemberRequestDto.getLastname() ,userMemberRequestDto.getPhone() , userMemberRequestDto.getRole());
        var member = memberMapper.toEntity(userMemberRequestDto);
        member.setUser(user);
        user.setRole("Member");
        memberRepository.save(member);
        return memberMapper.toDto(member);
  }

    public void deleteMember(Long id) throws MemberNotFoundException {
        var member = memberRepository.findById(id).orElse(null);
        if(member == null)
            throw new MemberNotFoundException();
        memberRepository.deleteById(id);
    }


    public MemberResponseDto updateMember(Long id, UpdateUserMemberRequestDto updateUserRequestDto) throws MemberNotFoundException {
        var member = memberRepository.findById(id).orElse(null);
        if(member == null)
            throw new MemberNotFoundException();
        UpdateUserRequestDto updateUser = new UpdateUserRequestDto(updateUserRequestDto.getEmail(), updateUserRequestDto.getFirstname(), updateUserRequestDto.getLastname(), updateUserRequestDto.getPhone());
        UpdateMemberRequestDto updateMember = new UpdateMemberRequestDto(updateUserRequestDto.getBirthDate());


        // Apply updates
        memberMapper.update(updateMember, member);
        userMapper.update(updateUser, member.getUser());

        // Force save of user to ensure update SQL is emitted (the entity should be managed and flushed on transaction commit,
        // but saving explicitly guarantees the update when running into edge cases with proxies/lazy loading)
        if(member.getUser() != null) {
            userRepository.save(member.getUser());
        }

        // Save member as well (optional when managed) to be explicit
        memberRepository.save(member);

        // Log after values
        System.out.println("[updateMember] after -> member.birthDate=" + member.getBirthDate() + ", user.email=" + (member.getUser()!=null?member.getUser().getEmail():"null") + ", firstname=" + (member.getUser()!=null?member.getUser().getFirstname():"null"));

        member = memberRepository.findById(id).orElse(null);
        return memberMapper.toDto(member);
    }
}
