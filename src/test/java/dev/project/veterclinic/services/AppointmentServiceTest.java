package dev.project.veterclinic.services;

import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.models.Pet;
import dev.project.veterclinic.repositories.AppointmentRepository;
import dev.project.veterclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.enums.AppointmentStatus;
import dev.project.veterclinic.enums.AppointmentType;
import dev.project.veterclinic.enums.PetType;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(MockitoExtension.class)  // Ensures proper initialization of mocks
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private Pet mockPet;
    private Owner mockOwner;
    private Appointment mockAppointment;
    private AppointmentDto mockAppointmentDto;

    @BeforeEach
    void setUp() {
        // Create a mock Owner
        mockOwner = new Owner("John", "Doe", "12345", "555-5555");

        // Create a mock Pet with an associated Owner
        mockPet = new Pet("Max", LocalDateTime.now(), PetType.DOG, "Male", null, mockOwner);

        // Create a mock Appointment
        mockAppointment = new Appointment(LocalDateTime.now(), AppointmentType.ORDINARY, "Checkup", AppointmentStatus.PENDING, mockPet, mockOwner);

        // Create a mock AppointmentDto
        mockAppointmentDto = new AppointmentDto(LocalDateTime.now(), AppointmentType.ORDINARY, "Checkup", AppointmentStatus.PENDING, 1, 12345);
    }

    @Test
    void testDeleteAppointmentByOwnerDniAndappointmentId() {
        int ownerDni = 12345;
        int appointmentId = 1;
        when(appointmentRepository.findByOwnerDniAndAppointmentId(ownerDni, appointmentId)).thenReturn(mockAppointment);

        appointmentService.deleteAppointmentByOwnerDniAndappointmentId(ownerDni, appointmentId);

        verify(appointmentRepository, times(1)).delete(mockAppointment);
    }
}
