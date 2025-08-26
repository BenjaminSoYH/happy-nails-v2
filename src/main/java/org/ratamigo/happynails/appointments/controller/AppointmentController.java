package org.ratamigo.happynails.appointments.controller;
import org.ratamigo.happynails.appointments.dto.AppointmentDTO;
import org.ratamigo.happynails.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAppointments(){
        List<AppointmentDTO> appointments = appointmentService.getAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable int id){
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDto){
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable int id, @RequestBody AppointmentDTO appointmentDto){
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id){
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok("Appointment deleted!");
    }


}
