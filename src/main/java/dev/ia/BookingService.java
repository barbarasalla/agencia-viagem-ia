package dev.ia;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class BookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();

    public BookingService() {
        bookings.put(1L, new Booking(1L, "Alice", "Aventura Amazônia", LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(2).plusDays(5), BookingStatus.CONFIRMED, Category.ADVENTURE));
        bookings.put(2L, new Booking(2L, "Bob", "Praias do Nordeste", LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(1).plusDays(7), BookingStatus.PENDING, Category.BEACH));
        bookings.put(3L, new Booking(3L, "Charlie", "Serra Gaúcha ", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(10), BookingStatus.PENDING, Category.CULTURAL));
    }

    public Optional<Booking> getBookingDetails(long bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public Optional<Booking> cancelBooking(Long bookingId, String customerLastName) {
        if (bookings.containsKey(bookingId)) {
            Booking booking = bookings.get(bookingId);
            if (booking.customerName().endsWith(customerLastName)) {
                Booking cancelledBooking = new Booking(
                        booking.id(),
                        booking.customerName(),
                        booking.destination(),
                        booking.startDate(),
                        booking.endDate(),
                        BookingStatus.CANCELLED,
                        booking.category()
                );
                bookings.put(bookingId, cancelledBooking);
                return Optional.of(cancelledBooking);
            }
        }
        return Optional.empty();
    }

    public List<Booking> findPackagesByCategory(Category category) {
        return bookings.values().stream()
                .filter(booking -> booking.category() == category)
                .toList();
    }
}
