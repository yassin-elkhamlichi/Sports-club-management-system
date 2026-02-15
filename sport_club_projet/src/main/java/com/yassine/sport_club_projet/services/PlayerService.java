package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.Player;
import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.entites.Team;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.exceptions.PlayerNotFoundException;
import com.yassine.sport_club_projet.exceptions.TeamNotFoundException;
import com.yassine.sport_club_projet.exceptions.UserAlreadyExistException;
import com.yassine.sport_club_projet.mapper.PlayerMapper;
import com.yassine.sport_club_projet.mapper.UserMapper;
import com.yassine.sport_club_projet.repositories.PlayerRepository;
import com.yassine.sport_club_projet.repositories.TeamRepository;
import com.yassine.sport_club_projet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    public List<PlayerResponseDto> GetAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    public PlayerResponseDto GetPlayer(Long id) throws PlayerNotFoundException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
        assert user != null;

        if (!user.getRole().equals(Role.ADMIN) && !Objects.equals(user.getId(), id)) {
            throw new AccessDeniedException("You can only view your own profile");
        }

        var player = playerRepository.findById(id).orElse(null);
        if (player == null)
            throw new PlayerNotFoundException();
        return playerMapper.toDto(player);
    }

    public PlayerResponseDto AddPlayer(UserPlayerRequestDto userPlayerRequestDto) throws UserAlreadyExistException {
        if (userRepository.findByEmail(userPlayerRequestDto.getEmail()).isPresent())
            throw new UserAlreadyExistException();
        User user = new User();
        String password = passwordEncoder.encode(userPlayerRequestDto.getPassword());

        user.addUser(userPlayerRequestDto.getEmail(), password, userPlayerRequestDto.getFirstname(), userPlayerRequestDto.getLastname(), userPlayerRequestDto.getPhone(), Role.PLAYER);
        Player player = playerMapper.toEntity(userPlayerRequestDto);
        player.setUser(user);
        playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    public PlayerResponseDto updatePlayer(Long id, UpdateUserPlayerRequestDto updateUserPlayerRequestDto) throws PlayerNotFoundException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
        assert user != null;

        if (!user.getRole().equals(Role.ADMIN) && !Objects.equals(user.getId(), id)) {
            throw new AccessDeniedException("You can only update your own profile");
        }

        var player = playerRepository.findById(id).orElse(null);
        if (player == null)
            throw new PlayerNotFoundException();

        UpdatePlayerRequestDto updatePlayer = playerMapper.toDto(updateUserPlayerRequestDto);
        UpdateUserRequestDto updateUser = userMapper.toDto(updateUserPlayerRequestDto);

        // Apply updates
        playerMapper.update(updatePlayer, player);
        userMapper.update(updateUser, player.getUser());
        var team = teamRepository.findById(updatePlayer.getTeamId()).orElse(null);
        player.setTeam(team);
        playerRepository.save(player);
        // Refresh player data
        player = playerRepository.findById(id).orElse(null);
        return playerMapper.toDto(player);

    }

    public void deletePlayer(Long id) throws PlayerNotFoundException {
        var player = playerRepository.findById(id).orElse(null);
        if (player == null)
            throw new PlayerNotFoundException();
        playerRepository.deleteById(id);
    }

    @Transactional
    public Team assignPlayerToTeam(
            Long TeamId,
            Long PlayerId
    ) throws PlayerNotFoundException, TeamNotFoundException {
        Player player = playerRepository.findById(PlayerId).orElse(null);
        if (player == null)
            throw new PlayerNotFoundException();
        System.out.println(player.getId());
        var team = teamRepository.findById(TeamId).orElse(null);
        if (team == null)
            throw new TeamNotFoundException();
        player.setTeam(team);
        team.getPlayers().add(player);
        playerRepository.save(player);
        return team;
    }
}
