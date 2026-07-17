package dev.ia;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class ChatMemoryConfig {

    // This method produces a ChatMemory bean that can be injected into other components of the application.
    @Produces
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(20) // Keep the last 20 messages in memory
                .chatMemoryStore(new InMemoryChatMemoryStore()) // Use in-memory store for chat memory
                .build();
    }
}
