package dev.ia;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookingTools {

    @Inject
    BookingService bookingService;

    @Tool("Obtem detalhes completos de uma reserva com base em seu número de identificação(bookingId).")
    public String getBookingDetails(Long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com  ID: " + bookingId + " não encontrada.");
    }

    @Tool("""
                Cancela uma reserva existente. 
                Para confirmar o cancelamento, é necessário fornecer o ID da reserva (bookingId)
                e o último nome do cliente (customerLastName).
            """)
    public String cancelBooking(Long bookingId, String customerLastName) {
        return bookingService.cancelBooking(bookingId, customerLastName)
                .map(Booking::toString)
                .orElse("Não foi possível cancelar a reserva com ID: " + bookingId + ". Verifique se o ID e o último nome do cliente estão corretos.");
    }

    @Tool("Listar os pacotes de viagem disponíveis para uma determinada categoria (Adventure, Beach, Cultural).")
    public String listPackagesByCategory(String category) {
        List<Booking> packages = bookingService.findPackagesByCategory(Category.valueOf(category.toUpperCase()));
        if (packages.isEmpty()) {
            return "Nenhum pacote de viagem encontrado para a categoria: " + category;
        }
        return "Pacotes de viagem disponíveis para a categoria '" + category + "': " +
                packages.stream()
                        .map(Booking::destination)
                        .toList().toString();
    }
}
