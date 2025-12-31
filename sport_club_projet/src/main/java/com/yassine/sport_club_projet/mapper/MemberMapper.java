package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.MemberResponseDto;
import com.yassine.sport_club_projet.dto.UpdateMemberRequestDto;
import com.yassine.sport_club_projet.dto.UpdateUserRequestDto;
import com.yassine.sport_club_projet.dto.UserMemberRequestDto;
import com.yassine.sport_club_projet.entites.Member;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(target = "user" , source="user.id")
    MemberResponseDto toDto(Member member);

    Member toEntity(UserMemberRequestDto userMemberRequestDto);

    @Mapping(target = "id", ignore = true)
    void update(UpdateMemberRequestDto updateUserRequestDto , @MappingTarget Member member);
}
