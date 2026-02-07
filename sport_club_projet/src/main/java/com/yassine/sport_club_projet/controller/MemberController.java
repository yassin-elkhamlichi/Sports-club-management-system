package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.exceptions.MemberNotFoundException;
import com.yassine.sport_club_projet.exceptions.UserAlreadyExistException;
import com.yassine.sport_club_projet.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    public final MemberService memberService;

    @GetMapping()
    public ResponseEntity<List<MemberResponseDto>> GetAllMembers(){
        return ResponseEntity.ok(memberService.GetAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> GetMember(
            @PathVariable Long id
    ) throws MemberNotFoundException {
        return ResponseEntity.ok(memberService.GetMember(id));
    }

    @PostMapping()
    public MemberResponseDto addMember(
            @RequestBody UserMemberRequestDto userMemberRequestDto
            ) throws UserAlreadyExistException {
        return memberService.AddMember(userMemberRequestDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long id,
            @RequestBody UpdateUserMemberRequestDto updateUserRequestDto
    ) throws MemberNotFoundException {
        var member = memberService.updateMember(id, updateUserRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable Long id
    ) throws MemberNotFoundException {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }



}
