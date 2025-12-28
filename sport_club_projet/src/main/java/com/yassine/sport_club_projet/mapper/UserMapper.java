package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.UpdateUserRequestDto;
import com.yassine.sport_club_projet.entites.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void update(UpdateUserRequestDto updateUserRequestDto , @MappingTarget User user);
}
