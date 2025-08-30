package org.ratamigo.happynails.appointments.service.impl;

import org.ratamigo.happynails.appointments.dto.AppointmentDTO;
import org.ratamigo.happynails.appointments.dto.AppointmentGetAllResponse;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.appointments.repository.AppointmentRepository;
import org.ratamigo.happynails.appointments.service.AppointmentService;
import org.ratamigo.happynails.customers.model.Customer;
import org.ratamigo.happynails.customers.repository.CustomerRepository;
import org.ratamigo.happynails.exceptions.AppointmentNotFoundException;
import org.ratamigo.happynails.exceptions.CustomerNotFoundException;
import org.ratamigo.happynails.exceptions.NailTechNotFoundException;
import org.ratamigo.happynails.exceptions.ServiceTypeNotFoundException;
import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.ratamigo.happynails.nailtechs.repository.NailTechRepository;
import org.ratamigo.happynails.servicetypes.model.ServiceType;
import org.ratamigo.happynails.servicetypes.repository.ServiceTypeRepository;
import org.ratamigo.happynails.shared.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


// ENSURE ALL HTTP CODES ARE CORRECT. WE ARE CONFUSING CREATED VS OK. ALSO PUT SHOULD BE OK.
// CHECK HTTP CODES AGAIN EVERYTHING EVERYTHING 
// add the jsonbackreference shi wherever needed everywhere!!!!!!!!
// check over the relationships between entities (ex. get all availabilities for each nailtech, get 
// all appointments for each customer)

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private CustomerRepository customerRepository;
    private NailTechRepository nailTechRepository;
    private ServiceTypeRepository serviceTypeRepository;

    // Implement mappers
    public AppointmentDTO mapToDto(Appointment appointment){
        // Create new DTO
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());

        // Time slot
        TimeSlot timeslot = appointment.getTimeSlot();
        dto.setStartTime(timeslot.getStartTime()); // Get the start time from timeSlot
        dto.setEndTime(timeslot.getEndTime()); // Get the end time from timeSlot

        // Customer information
        dto.setCustomer_id(appointment.getCustomer().getId());
        dto.setCustomer_name(appointment.getCustomer().getName());

        // Nail tech information
        dto.setTech_id(appointment.getNailTech().getId());
        dto.setTech_name(appointment.getNailTech().getName());

        // Service information
        dto.setService_id(appointment.getService().getId());
        dto.setService_name(appointment.getService().getName());

        // Appointment status
        dto.setApptStatus(appointment.getStatus());
        return dto;
    }

    public Appointment mapToEntity(AppointmentDTO dto){
        // Create new entity
        Appointment appointment = new Appointment();

        // Time slot
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(dto.getStartTime());
        timeSlot.setEndTime(dto.getEndTime());
        appointment.setTimeSlot(timeSlot);

        // Customer information
        appointment.setCustomer(customerRepository.findById(dto.getCustomer_id()).orElseThrow());

        // Nail tech information
        appointment.setNailTech(nailTechRepository.findById(dto.getTech_id()).orElseThrow());

        // Service information
        appointment.setService(serviceTypeRepository.findById(dto.getService_id()).orElseThrow());

        // Appointment status
        appointment.setStatus(dto.getApptStatus());
        return appointment;
    }

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  CustomerRepository customerRepository,
                                  NailTechRepository nailTechRepository,
                                  ServiceTypeRepository serviceTypeRepository){
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.nailTechRepository = nailTechRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }


    @Override
    public AppointmentGetAllResponse getAppointments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Appointment> appointments = appointmentRepository.findAll(pageable);
        List<Appointment> listOfAppointments = appointments.getContent();
        List<AppointmentDTO> content = listOfAppointments.stream().map(
                a -> mapToDto(a)).collect(Collectors.toList());
        AppointmentGetAllResponse appointmentResponse = new AppointmentGetAllResponse();
        appointmentResponse.setContent(content);
        appointmentResponse.setPageNo(appointments.getNumber());
        appointmentResponse.setPageSize(appointments.getSize());
        appointmentResponse.setTotalElements(appointments.getTotalElements());
        appointmentResponse.setTotalPages(appointments.getTotalPages());
        appointmentResponse.setLast(appointments.isLast());

        return appointmentResponse;
    }

    @Override
    public AppointmentDTO getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()
                    -> new AppointmentNotFoundException("Appointment could not be found"));
        return mapToDto(appointment);
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment appointment = mapToEntity(dto);
        Appointment saved = appointmentRepository.save(appointment);
        return mapToDto(saved);
    }

    @Override
    public AppointmentDTO updateAppointment(int id, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()
                    -> new AppointmentNotFoundException("Appointment could not be updated"));
        
        // Update timeSlot if provided
        if (dto.getStartTime() != null && dto.getEndTime() != null) {
            TimeSlot timeSlot = new TimeSlot(dto.getStartTime(), dto.getEndTime());
            appointment.setTimeSlot(timeSlot);
        }

        // Update customer if provided
        if (dto.getCustomer_id() != 0) { 
            Customer customer = customerRepository.findById(dto.getCustomer_id())
                    .orElseThrow(() -> new CustomerNotFoundException(
                            "Corresponding customer could not be found"));
            appointment.setCustomer(customer);
        }

        // Update nail tech if provided
        if (dto.getTech_id() != 0) {
            NailTech tech = nailTechRepository.findById(dto.getTech_id())
                    .orElseThrow(() -> new NailTechNotFoundException(
                            "Corresponding nail tech could not be found"));
            appointment.setNailTech(tech);
        }

        // Update service if provided
        if (dto.getService_id() != 0) {
            ServiceType service = serviceTypeRepository.findById(dto.getService_id())
                    .orElseThrow(() -> new ServiceTypeNotFoundException(
                            "Corresponding service could not be found"));
            appointment.setService(service);
        }

        // Update status if provided
        if (dto.getApptStatus() != null) {
            appointment.setStatus(dto.getApptStatus());
        }

        Appointment saved = appointmentRepository.save(appointment);
        return mapToDto(saved);
    }

    @Override
    public void deleteAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()
                -> new AppointmentNotFoundException("Appointment could not be deleted"));
        appointmentRepository.delete(appointment);
    }

    @Override
    public void deleteAppointmentsByCustomerName(String name) {
        appointmentRepository.deleteByCustomerNameIgnoreCase(name);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByCustomerId(int id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByCustomerId(id);
        return appointments.stream().map(a -> mapToDto(a)).collect(Collectors.toList());
    }


}
