package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.AttendanceDto;
import com.yassine.sport_club_projet.entites.*;
import com.yassine.sport_club_projet.exceptions.*;
import com.yassine.sport_club_projet.mapper.AttendanceMapper;
import com.yassine.sport_club_projet.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;
    @Mock
    private AttendanceMapper attendanceMapper;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private CoachRepository coachRepository;
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    // --- TEST 1: SUCCESS SCENARIO ---
    @Test
    void registerAttendance_Success() throws Exception, CoachNotResponseForThisPlayerException {
        // Arrange
        Long coachId = 1L;
        Long playerId = 2L;
        Long matchId = 3L;

        Coach mockCoach = mock(Coach.class);
        Player mockPlayer = new Player();
        Match mockMatch = new Match();

        when(mockCoach.findPlayer(mockPlayer)).thenReturn(true);
        // Mocking the coach behavior (finding the player)
        // Note: You might need to mock coach.findPlayer(mockPlayer) if it's a real logic check
        // For this example, I'll assume it returns true.

        when(coachRepository.findById(coachId)).thenReturn(Optional.of(mockCoach));
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(mockPlayer));
        when(matchRepository.findById(matchId)).thenReturn(Optional.of(mockMatch));

        // Mocking mapper and repository save
        when(attendanceRepository.save(any(Attendance.class))).thenAnswer(i -> i.getArguments()[0]);
        when(attendanceMapper.toDto(any(Attendance.class))).thenReturn(new AttendanceDto());

        // Act
        AttendanceDto result = attendanceService.registerAttendance(coachId, playerId, matchId);

        // Assert
        assertNotNull(result);
        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

    // --- TEST 2: EXCEPTION SCENARIO (MATCH NOT FOUND) ---
    @Test
    void getAttendanceForMatch_MatchNotFound_ThrowsException() {
        // Arrange
        Long invalidMatchId = 99L;
        when(matchRepository.findById(invalidMatchId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MatchNotFoundException.class, () -> {
            attendanceService.getAttendanceForMatch(invalidMatchId);
        });

        // Verify that the code stopped at match check and never looked for attendance
        verify(attendanceRepository, never()).findByMatchId(any());
    }

    // --- TEST 3: DELETE SCENARIO ---
    @Test
    void deleteAttendance_ExistingId_DeletesSuccessfully() throws AttendanceNotFoundException {
        // Arrange
        Long id = 1L;
        when(attendanceRepository.existsById(id)).thenReturn(true);

        // Act
        attendanceService.deleteAttendance(id);

        // Assert
        verify(attendanceRepository, times(1)).deleteById(id);
    }
}