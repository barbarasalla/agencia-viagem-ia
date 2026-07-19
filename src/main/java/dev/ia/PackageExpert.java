package dev.ia;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.guardrail.OutputGuardrails;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;

/**
 * This interface defines the contract for the PackageExpert service, which is an AI-powered virtual assistant for 'Mundo Viagens'.
 * The assistant is designed to provide accurate and friendly responses to customer inquiries about travel packages.
 * It relies solely on the information contained in the provided documents (RAG) and available tools for interacting with the booking system.
 */
@RegisterAiService(
        retrievalAugmentor = RagRetrievalAugmentorSupplier.class
)
public interface PackageExpert {

    @SystemMessage("""
        Você é um assistente virtual da 'Mundo Viagens', um especialista em nossos pacotes de viagem.
        Sua principal responsabilidade é responder às perguntas dos clientes de forma amigável e precisa,
        baseando-se exclusivamente nas informações contidas nos documentos que lhe foram fornecidos (RAG) 
        ou utilizando as ferramentas disponíveis para interagir com o sistema de reservas.
        Nunca invente informações ou use conhecimento externo.
        Se a resposta para uma pergunta não estiver nos documentos e nenhuma ferramenta puder ajudar, 
        você deve responder educadamente:
        'Desculpe, mas não tenho informações sobre isso. Posso ajudar com mais alguma dúvida sobre nossos pacotes?'
        """)
    @InputGuardrails(InjectionGuard.class)
    @OutputGuardrails(ToneGuardrail.class)
    @McpToolBox("booking-server")
    String chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
