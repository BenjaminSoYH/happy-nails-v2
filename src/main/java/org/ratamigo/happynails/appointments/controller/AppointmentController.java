package org.ratamigo.happynails.appointments.controller;
import org.ratamigo.happynails.appointments.dto.AppointmentDTO;
import org.ratamigo.happynails.appointments.dto.AppointmentGetAllResponse;
import org.ratamigo.happynails.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<AppointmentGetAllResponse> getAppointments(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        AppointmentGetAllResponse appointments = appointmentService.getAppointments(pageNo, pageSize);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("id") int id){
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDto){
        return new ResponseEntity<>(appointmentService.createAppointment(appointmentDto),
                                    HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentDTO> updateAppointment(
                @PathVariable("id") int id, 
                @RequestBody AppointmentDTO appointmentDto){
        return new ResponseEntity<>(appointmentService.updateAppointment(id, appointmentDto),
                                    HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") int id){
        appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>("Appointment deleted", HttpStatus.OK);
    }
}
