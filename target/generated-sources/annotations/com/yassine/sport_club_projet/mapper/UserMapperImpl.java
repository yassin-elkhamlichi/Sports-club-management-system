package com.yassine.sport_club_projet.mapper;

import com.yassine.sport_club_projet.dto.UpdateUserCoachRequestDto;
import com.yassine.sport_club_projet.dto.UpdateUserPlayerRequestDto;
import com.yassine.sport_club_projet.dto.UpdateUserRequestDto;
import com.yassine.sport_club_projet.dto.UserCoachRequestDto;
import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.entites.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T01:10:21+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void update(UpdateUserRequestDto updateUserRequestDto, User user) {
        if ( updateUserRequestDto == null ) {
            return;
        }

        user.setEmail( updateUserRequestDto.getEmail() );
        user.setFirstname( updateUserRequestDto.getFirstname() );
        user.setLastname( updateUserRequestDto.getLastname() );
        user.setPhone( updateUserRequestDto.getPhone() );
    }

    @Override
    public UpdateUserRequestDto toDto(UpdateUserPlayerRequestDto updateUserPlayerRequestDto) {
        if ( updateUserPlayerRequestDto == null ) {
            return null;
        }

        String email = null;
        String firstname = null;
        String lastname = null;
        String phone = null;

        email = updateUserPlayerRequestDto.getEmail();
        firstname = updateUserPlayerRequestDto.getFirstname();
        lastname = updateUserPlayerRequestDto.getLastname();
        phone = updateUserPlayerRequestDto.getPhone();

        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto( email, firstname, lastname, phone );

        return updateUserRequestDto;
    }

    @Override
    public User toEntity(UserCoachRequestDto userCoachRequestDto) {
        if ( userCoachRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userCoachRequestDto.getEmail() );
        user.setPassword( userCoachRequestDto.getPassword() );
        user.setFirstname( userCoachRequestDto.getFirstname() );
        user.setLastname( userCoachRequestDto.getLastname() );
        user.setPhone( userCoachRequestDto.getPhone() );
        if ( userCoachRequestDto.getRole() != null ) {
            user.setRole( Enum.valueOf( Role.class, userCoachRequestDto.getRole() ) );
        }

        return user;
    }

    @Override
    public UpdateUserRequestDto toDto(UpdateUserCoachRequestDto updateUserCoachRequestDto) {
        if ( updateUserCoachRequestDto == null ) {
            return null;
        }

        String email = null;
        String firstname = null;
        String lastname = null;
        String phone = null;

        email = updateUserCoachRequestDto.getEmail();
        firstname = updateUserCoachRequestDto.getFirstname();
        lastname = updateUserCoachRequestDto.getLastname();
        phone = updateUserCoachRequestDto.getPhone();

        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto( email, firstname, lastname, phone );

        return updateUserRequestDto;
    }
}
