package com.yassine.sport_club_projet.services;


import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.Member;
import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.UserAlreadyExistException;
import com.yassine.sport_club_projet.mapper.MemberMapper;
import com.yassine.sport_club_projet.mapper.UserMapper;

import com.yassine.sport_club_projet.repositories.MemberRepository;
import com.yassine.sport_club_projet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;
    public final MemberMapper memberMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    public List<MemberResponseDto> GetAllMembers(){
      return memberRepository.findAll().stream()
              .map(memberMapper::toDto)
              .toList();
  }
  public MemberResponseDto GetMember(Long id) throws MemberNotFoundException, AccessDeniedException {

      var member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
      final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      var user = userRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
      assert user != null;

      if(!Objects.equals(user.getId(), id))
          throw new AccessDeniedException("You can only view your own profile");

      return memberMapper.toDto(member);
  }

  public MemberResponseDto AddMember(UserMemberRequestDto userMemberRequestDto) throws UserAlreadyExistException {
        if(userRepository.findByEmail(userMemberRequestDto.getEmail()).isPresent())
            throw new UserAlreadyExistException();
        User user = new User();
        String password = passwordEncoder.encode(userMemberRequestDto.getPassword());
        user.addUser(userMemberRequestDto.getEmail() , password , userMemberRequestDto.getFirstname() , userMemberRequestDto.getLastname() ,userMemberRequestDto.getPhone() , Role.MEMBER);
        var member = memberMapper.toEntity(userMemberRequestDto);
        member.setUser(user);
        memberRepository.save(member);
        return memberMapper.toDto(member);
  }

    public void deleteMember(Long id) throws MemberNotFoundException {
        var member = memberRepository.findById(id).orElse(null);
        if(member == null)
            throw new MemberNotFoundException();
        memberRepository.deleteById(id);
    }


    public MemberResponseDto updateMember(Long id, UpdateUserMemberRequestDto updateUserRequestDto) throws MemberNotFoundException, AccessDeniedException {
        var member = memberRepository.findById(id).orElse(null);
        if(member == null)
            throw new MemberNotFoundException();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
        assert user != null;

        if(!Objects.equals(user.getId(), id))
            throw new AccessDeniedException("You can only view your own profile");

        UpdateUserRequestDto updateUser = new UpdateUserRequestDto(updateUserRequestDto.getEmail(), updateUserRequestDto.getFirstname(), updateUserRequestDto.getLastname(), updateUserRequestDto.getPhone());
        UpdateMemberRequestDto updateMember = new UpdateMemberRequestDto(updateUserRequestDto.getBirthDate());


        // Apply updates
        memberMapper.update(updateMember, member);
        userMapper.update(updateUser, member.getUser());


        if(member.getUser() != null) {
            userRepository.save(member.getUser());
        }

        memberRepository.save(member);

        member = memberRepository.findById(id).orElse(null);
        return memberMapper.toDto(member);
    }

}
