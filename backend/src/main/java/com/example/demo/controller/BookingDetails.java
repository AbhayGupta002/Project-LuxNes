    package com.example.demo.controller;//package com.example.demo.controller;

    import com.example.demo.dto.ErrorDetails;
    import com.example.demo.dto.Response;
    import com.example.demo.dto.UserDto;
    import com.example.demo.entity.BookingDetails;
    import com.example.demo.entity.HotelDetails;
    import com.example.demo.entity.UserDetails;
    import com.example.demo.repository.BookingDetailsRepository;
    import com.example.demo.repository.HotelDetailsRepository;
    import com.example.demo.repository.UserDetailsRepository;
    import com.example.demo.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;

    import java.util.Optional;

    public class BookingController {

        @Autowired
        private UserDetailsRepository userRepo;

        @Autowired
        private HotelDetailsRepository hotelRepo;

        @Autowired
        private BookingDetailsRepository bookingRepo;

        @PostMapping
        public ResponseEntity<?> HotelBooking(@RequestBody UserDto userDto){
            HotelDetails hotelDetails = new HotelDetails();
            Response response = new Response();
            BookingDetails bookingDetails = new BookingDetails();

            Optional<UserDetails> existingUser = userRepo.findById(userDto.getUserId());
            if (!existingUser.isPresent()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,
                        "User not found with this id: " + userDto.getUserId());
                response.setError(errorDetails);
                return ResponseEntity.ofNullable(response);
            }
            Optional<HotelDetails> existingHotel = hotelRepo.findById(hotelDetails.getHotelId());
            if (!existingHotel.isPresent()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,
                        "hotel not found with this id: " + userDto.getUserId());
                response.setError(errorDetails);
                return ResponseEntity.ofNullable(response);
            }
                UserService userService = new UserService();
                bookingDetails.setBookingId(userService.idGenerater().toString());
                bookingDetails.setUserid(userDto.getUserId());
                hotelDetails.setHotelId(userDto.getHotelId);
                bookingDetails.setStatus("BOOKED"); // example value
                bookingRepo.save(bookingDetails);

                BookingDetails bookingDetails1 = new BookingDetails();
                userDto.setBookingId(bookingDetails1.getBookingId());
                userDto.setGetHotelId(bookingDetails1.getHotelDetails().getHotelId());
                userDto.setUserId(bookingDetails1.getUserid());
                response.setData(userDto);
                return ResponseEntity.ok(response);
        }

        @DeleteMapping(value = "cancelBooking")
        public ResponseEntity<?> cancelBooking(@PathVariable String bookingId) {
            Response response = new Response();

            Optional<BookingDetails> bookingOptional = bookingRepo.findById(bookingId);
            if (!bookingOptional.isPresent()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,
                        "Booking with ID " + bookingId + " not found");
                response.setError(errorDetails);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Delete the booking
            bookingRepo.deleteById(bookingId);
            response.setData("Booking with ID " + bookingId + " cancelled successfully");
            return ResponseEntity.ok(response);
        }


    }
